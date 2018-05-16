package com.jinhui.service.abs.impl;


import com.jinhui.constant.AbsConstant;
import com.jinhui.exception.AbsException;
import com.jinhui.mapper.abs.*;
import com.jinhui.model.Assets;
import com.jinhui.model.abs.*;
import com.jinhui.service.abs.InvoiceService;
import com.jinhui.util.UserUtils;
import com.jinhui.util.excel.Excel2List;
import com.jinhui.util.excel.ExcelException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

@Service("InvoiceService")
public class InvoiceServiceImpl implements InvoiceService {

    private static Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    @Autowired
    private PeWhiteListMapper peWhiteListMapper;

    @Autowired
    private PeAccountsReceivableMapper peAccountsReceivableMapper;


    @Autowired
    private PeBackpaymentRecordMapper peBackpaymentRecordMapper;

    @Autowired
    private PeAccountsReceivableAllMapper peAccountsReceivableAllMapper;


    @Autowired
    private PeAssetChangeHistoryMapper peAssetChangeHistoryMapper;


    @Autowired
    private PeFundChangeHistoryMapper peFundChangeHistoryMapper;

    @Override
    public BillDetailsFilter importInvoices(InputStream in) throws AbsException {

        //解析excel文件流
        Excel2List<BillDetails> resolve = new Excel2List<>(BillDetails.class, 0, 2);


        List<BillDetails> billList = null;
        try {
            billList = resolve.resolve2List(in);
        } catch (ExcelException e) {
            throw new AbsException(e.getMessage(), 500, e);
        } finally {
            IOUtils.closeQuietly(in);
        }


        //查询白名单
        String gname = UserUtils.getUser().getGname();

        List<PeWhiteList> whiteLists = peWhiteListMapper.selectByOriginalHolder(gname);


        BillDetailsFilter filterResult = new BillDetailsFilter(billList, whiteLists, peAccountsReceivableMapper);

        //检查数据，格式是否正确，是否有重复数据，冲销账单是否合法，根据白名单筛选数据,计算匹配率
        filterResult.checkBills();


        return filterResult;

    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public List<PeAccountsReceivable> saveMatchBills(List<BillDetails> matchList) throws AbsException {


        String gName = UserUtils.getUser().getGname();//获取当前用户的机构名

        //“待收”类型的账单，填充账单的“原始权益人”和“应收账期”后，直接入库
        List<BillDetails> receivingBills = getReceivingBills(matchList);

        List<PeWhiteList> whiteLists = peWhiteListMapper.selectByOriginalHolder(gName);
        for (BillDetails receivingBill : receivingBills) {

            Optional<PeWhiteList> first = whiteLists.stream().filter(white -> white.getReceivableDebtor().equals(receivingBill.getDebtorName())).findFirst();
            Integer receivablePeriod = first.get().getReceivablePeriod();
            PeAccountsReceivable peAccountsReceivable = receivingBill.convertToPeAccountsReceivable(gName, receivablePeriod);

            peAccountsReceivableMapper.insert(peAccountsReceivable);

        }


        /**
         * 冲销"类型的账单走冲销流程：
         *1.如果“被冲销的资产”没有被回款，也没有被资产包关联，直接把该笔资产变为冲销
         *2.如果“被冲销的资产”已经回款，那么除了把该笔资产变为冲销外，还要形成一笔新的回款信息补充到回款表中
         *4.如果“被冲销的资产”已经和资产包关联了，要记录它在资产包里的资产变得和资金变动
         */
        List<BillDetails> writeOffBills = getWriteOffBills(matchList);

        for (BillDetails writeOffBill : writeOffBills) {

            //根据原发票号找到存量数据
            PeAccountsReceivable accountsReceivable = peAccountsReceivableMapper.selectByInvoiceNoAndBillAmount(writeOffBill.getOriginInvoiceNo(), writeOffBill.getOriginInvoiceCode(), writeOffBill.getBillBalance().abs());


            //回款金额等于回款余额表示没有被回款
            boolean notPayment = accountsReceivable.getBillBalance().compareTo(accountsReceivable.getBillAmount()) == 0;

            //inAssetPackage为true表示资产已经和资产包关联
            boolean inAssetPackage = accountsReceivable.getAid() != null;
            Integer aid = accountsReceivable.getAid();


            accountsReceivable.setStatus(AbsConstant.WRITE_OFF);//把存量资产状态改为“冲销”
            accountsReceivable.setAid(null);//解除基础资产与资产包的关系
            peAccountsReceivableMapper.updateByPrimaryKey(accountsReceivable);//更新基本资产的状态

            //已经被回过款
            if (!notPayment) {
                /**把该笔资产转变为“回款信息(冲销类型)”，插入回款表中
                 转换规则:
                 1.如果该笔存量资产余额为零，表示该资产是“已收”或“逾期已收”状态,回款金额=账单金额
                 2.如果该笔存量资产余额不为零，表示该资产是“待收”，“坏账”或“逾期”状态，回款金额=账单金额减去余额
                 */
                PeBackpaymentRecord peBackpaymentRecord = accountsReceivable.covertToPeBackpaymentRecord(AbsConstant.BACKPAYMENT_WRITE_OFF);
                peBackpaymentRecordMapper.insert(peBackpaymentRecord);
            }


            /**被冲销的资产”已经和资产包关联了，则要记录它在资产包里的资产变动和资金变动
             * 1.没还过款：资产全额改变，资金没变
             * 2.还部分款：资产全额改变，资金变动（等于已回款金额）
             * 3.还全额款：资产全额改变，资金变动（等于全回款金额）
             */
            if (inAssetPackage) {
                //记录资产变动
                PeAssetChangeHistory bacv = new PeAssetChangeHistory();
                bacv.setBaseAssetId(accountsReceivable.getId());
                bacv.setAid(aid);
                bacv.setChangeType("10");
                bacv.setCreateName(UserUtils.getUser().getName());
                bacv.setAssetAmount(accountsReceivable.getBillAmount());
                bacv.setReceivableDebtor(accountsReceivable.getDebtorName());
                bacv.setInvoiceCode(accountsReceivable.getInvoiceCode());
                bacv.setInvoiceNo(accountsReceivable.getInvoiceNo());
                peAssetChangeHistoryMapper.insertSelective(bacv);

                //在已还款的情况下，才会记录资金变动
                if (!notPayment) {
                    PeFundChangeHistory peFundChangeHistory = new PeFundChangeHistory();
                    peFundChangeHistory.setAid(aid);
                    peFundChangeHistory.setBaseAssetId(accountsReceivable.getId());
                    peFundChangeHistory.setCreateName(UserUtils.getUser().getName());
                    peFundChangeHistory.setChangeType("4"); //记录操作类别为冲销

                    //资产变动：出款金额=冲销金额，余额=资产包余额+冲销金额
                    BigDecimal billBalance = accountsReceivable.getBillBalance();
                    BigDecimal billAmount = accountsReceivable.getBillAmount();
                    PeFundChangeHistory pfh = peFundChangeHistoryMapper.selectNewestRecord(aid);
                    if (billBalance.doubleValue() == 0) {
                        peFundChangeHistory.setPaytoAmount(billAmount);
                        peFundChangeHistory.setBalanceAmount(pfh.getBalanceAmount().add(billAmount));
                    } else {
                        BigDecimal balance = billAmount.subtract(billBalance);
                        peFundChangeHistory.setPaytoAmount(balance);
                        peFundChangeHistory.setBalanceAmount(pfh.getBalanceAmount().add(balance));
                    }

                    peFundChangeHistoryMapper.insertSelective(peFundChangeHistory);
                }
            }


        }


        return null;
    }

    @Override
    public List<PeAccountsReceivable> saveAllBills(BillDetailsResult billDetailsResult) throws AbsException {

        List<BillDetails> list = billDetailsResult.getMatchList();

        list.addAll(billDetailsResult.getErrorList());

        String userName = UserUtils.getUserName();

        for (BillDetails billDetails : list) {

            PeAccountsReceivableAll peAccountsReceivableAll = billDetails.convertToPeAccountsReceivableAll(billDetailsResult.getFileName(), billDetailsResult.getFileId(), userName);

            peAccountsReceivableAllMapper.insert(peAccountsReceivableAll);

        }


        return null;
    }


    @Override
    public List<BillDetails> getReceivingBills(List<BillDetails> matchList) {
        List<BillDetails> receivingBills = matchList.stream().filter(bill -> bill.checkType().equals(AbsConstant.RECEIVING)).collect(Collectors.toList());

        return receivingBills;
    }

    @Override
    public List<BillDetails> getWriteOffBills(List<BillDetails> matchList) {
        List<BillDetails> writeOffBills = matchList.stream().filter(bill -> bill.checkType().equals(AbsConstant.WRITE_OFF)).collect(Collectors.toList());
        return writeOffBills;
    }


    /**
     * 根据资产ID查询资产包中的基础资产(应收账款)
     *
     * @return
     */
    public List<PeAccountsReceivable> selectAssetByAids(List<Assets> assetsList, String debtorName, String originalHolder) {
        List<PeAccountsReceivable> list = new ArrayList<PeAccountsReceivable>();
        for (Assets asset : assetsList) {
            PeAccountsReceivable par = new PeAccountsReceivable();
            par.setAid(asset.getId());
            par.setDebtorName(debtorName);
            par.setOriginalHolder(originalHolder);
            list.add(par);
        }

        return peAccountsReceivableMapper.selectAssetByAids(list);
    }

    /**
     * 查询应收债务人的基础资产
     *
     * @return
     */
    public List<PeAccountsReceivable> selectAssetByDebtor(String debtorName, String originalHolder) {
        return peAccountsReceivableMapper.selectAssetByDebtor(debtorName, originalHolder);
    }


    /**
     * 部分回款
     *
     * @param id
     * @param amount
     * @return
     */
    public int partialPayment(Integer id, BigDecimal amount, Integer backpaymentID) {
        PeAccountsReceivable par = new PeAccountsReceivable();
        par.setId(id);
        par.setBillBalance(amount);
        par.setBackpaymentID(backpaymentID);
        return peAccountsReceivableMapper.partialPayment(par);
    }

    /**
     * 完全回款
     *
     * @param id
     * @return
     */
    public int perfectPayment(Integer id, Integer backpaymentID) {
        PeAccountsReceivable par = new PeAccountsReceivable();
        par.setId(id);
        par.setBackpaymentID(backpaymentID);
        return peAccountsReceivableMapper.perfectPayment(par);
    }

    /**
     * 查询资产包中已回款需要被替换的基础资产
     *
     * @return
     */
    public List<PeAccountsReceivable> selectHavePayed() {
        return peAccountsReceivableMapper.selectHavePayed();
    }


    /**
     * 查询可以用来替换的基础资产
     *
     * @return
     */
    public List<PeAccountsReceivable> selectCouldReplace() {
        return peAccountsReceivableMapper.selectCouldReplace();
    }

    /**
     * 用来替换的基础资产
     *
     * @return
     */
    public int toReplace(Integer id, Integer aid) {
        PeAccountsReceivable par = new PeAccountsReceivable();
        par.setId(id);
        par.setAid(aid);

        return peAccountsReceivableMapper.toReplace(par);
    }

    /**
     * 被替换的基础资产
     *
     * @return
     */
    public int beReplaced(Integer id) {
        return peAccountsReceivableMapper.beReplaced(id);
    }

    /**
     * 逾期
     *
     * @return
     */
    public int overdue(Integer id) {
        return peAccountsReceivableMapper.overdue(id);
    }

    /**
     * 逾期移除资产
     * @return
     */
    public int overdueToRemove(Integer id){
        return peAccountsReceivableMapper.overdueToRemove(id);
    }

    /**
     * 查询资产包那个逾期的基础资产
     *
     * @return
     */
    public List<PeAccountsReceivable> selectOverdueBaseAssets() {
        return peAccountsReceivableMapper.selectOverdueBaseAssets();
    }


}
