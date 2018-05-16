package com.jinhui.model.abs;

import com.jinhui.constant.AbsConstant;
import com.jinhui.mapper.abs.PeAccountsReceivableMapper;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * billList是解析excel得到的列表,构造时注入
 * 1.检查billList中数据合法性，不合法的放到errorList中，剩下的放到matchList
 * Created by Administrator on 2017/10/12 0012.
 */

public class BillDetailsFilter {

    private List<BillDetails> matchList = new ArrayList<>();//匹配白名单的数据

    private String matchRate;//匹配率 = 匹配数/全部数据数

    private List<BillDetails> errorList;//格式不合法的数据

    private List<BillDetails> billList;

    private List<PeWhiteList> whiteList;


    private PeAccountsReceivableMapper peAccountsReceivableMapper;


    public BillDetailsFilter(List<BillDetails> billList, List<PeWhiteList> whiteList, PeAccountsReceivableMapper peAccountsReceivableMapper) {
        this.billList = billList;
        this.whiteList = whiteList;
        this.peAccountsReceivableMapper = peAccountsReceivableMapper;
    }

    /**
     * 检查数据,分“待收类型”和“冲销类型”检测
     */
    public void checkBills() {


        //检查“待收状态”的数据
        List<BillDetails> receivingBills = billList.stream().filter(bill -> bill.checkType().equals(AbsConstant.RECEIVING)).collect(Collectors.toList());
        for (BillDetails billDetails : receivingBills) {


            //检查必输项是否为空值
            checkIsNull(billDetails);


            //如果debtorName不为空,检测是不是在白名单内
            if (StringUtils.isNotBlank(billDetails.getDebtorName())) {

                boolean match = whiteList.stream().anyMatch(white -> white.getReceivableDebtor().equals(billDetails.getDebtorName()));
                if (!match) {
                    billDetails.setCorrect(false);
                    billDetails.appendErrorMessage("应收债务人未匹配白名单");
                }

                //如果debtorName在白名单内且invoicedDate不为空,检查是否逾期（待还日期<当前日期）
                if (match&&null != billDetails.getInvoicedDate()) {

                    Optional<PeWhiteList> first = whiteList.stream().filter(white -> white.getReceivableDebtor().equals(billDetails.getDebtorName())).findFirst();
                    Integer period = first.get().getReceivablePeriod();
                    Date invoicedDate = billDetails.getInvoicedDate();
                    DateTime dt = new DateTime(invoicedDate);

                    //待还日期=开票日期+白名单的账期
                    DateTime returnDays = dt.plusDays(period);

                    if (returnDays.compareTo(DateTime.now()) < 0) {
                        billDetails.setCorrect(false);
                        billDetails.appendErrorMessage("该笔账单已经逾期");
                    }

                }

            }

            //如果invoice_no ,invoice_code不为空
            if (StringUtils.isNotBlank(billDetails.getInvoiceNo()) && StringUtils.isNotBlank(billDetails.getInvoiceCode())) {
                //检测数据中是否存在重复
                long count = billList.stream().filter(bill -> StringUtils.isNotBlank(bill.getInvoiceNo()) && StringUtils.isNotBlank(bill.getInvoiceCode()))
                        .filter(bill -> bill.getInvoiceNo().equals(billDetails.getInvoiceNo()) && bill.getInvoiceCode().equals(billDetails.getInvoiceCode())).count();

                if (count > 1) {
                    billDetails.setCorrect(false);
                    billDetails.appendErrorMessage("该条数据是重复数据，请检查发票号码和发票代码");
                }

                /**
                 检查数据库中是否有重复数据
                 ps：一般而言，每一笔基础资产的invoice_no+invoice_code都不一致
                 但是，有一种冲销的特殊情况：基础资产A被基础资产A1冲销掉后，又插入了一笔修正后的基础资产A，A与修正后的A可能有相同的invoice_no+invoice_code
                 ，所以数据库中可能有两条invoice_no+invoice_code一致的数据，但其中一条会是冲销状态,另一台条是非冲销状态；
                 **/
                PeAccountsReceivable peAccountsReceivable = peAccountsReceivableMapper.selectByInvoiceNoAndInvoiceCode(billDetails.getInvoiceNo(), billDetails.getInvoiceCode());
                if (peAccountsReceivable != null) {
                    billDetails.appendErrorMessage("该条数据系统已经录入，不能重复上传");
                    billDetails.setCorrect(false);
                }
            }

        }


        /**
         * 检查冲销类型的账单是否合法:
         * 1.原发票号在数据库中有对应
         * 2.价税合计金额和冲销的账单相等
         */
        List<BillDetails> list = billList.stream().filter(bill -> bill.checkType().equals(AbsConstant.WRITE_OFF))
                .collect(Collectors.toList());

        for (
                BillDetails billDetails : list)

        {

            if (StringUtils.isBlank(billDetails.getOriginInvoiceNo())) {
                billDetails.setCorrect(false);
                billDetails.setErrorMessage("冲销账单的原发票号码不能为空");
            }
            if (StringUtils.isBlank(billDetails.getOriginInvoiceCode())) {
                billDetails.setCorrect(false);
                billDetails.appendErrorMessage("冲销账单的原发票代码不能为空");
            }
            if (null == billDetails.getBillBalance()) {
                billDetails.setCorrect(false);
                billDetails.appendErrorMessage("冲销账单的价税合计不能为空");
            }

            if (null != billDetails.getBillBalance() && StringUtils.isNotBlank(billDetails.getOriginInvoiceNo()) && StringUtils.isNotBlank(billDetails.getOriginInvoiceCode())) {
                PeAccountsReceivable peAccountsReceivable = peAccountsReceivableMapper
                        .selectByInvoiceNoAndBillAmount(billDetails.getOriginInvoiceNo(), billDetails.getOriginInvoiceCode(), billDetails.getBillBalance().abs());
                if (null == peAccountsReceivable) {
                    billDetails.setCorrect(false);
                    billDetails.setErrorMessage("系统找不到可以冲销的数据");
                }
            }

        }

        errorList = billList.stream().

                filter(bill -> !bill.isCorrect()).

                collect(Collectors.toList());

        matchList = billList.stream().

                filter(bill -> bill.isCorrect()).

                collect(Collectors.toList());

        matchRate =

                calcMatchRate(matchList.size(), billList.

                        size());

    }

    private void checkIsNull(BillDetails billDetails) {


        checkNullAndLength(billDetails, billDetails.getDebtorName(), 20, "应收债务人");

        checkNullAndLength(billDetails, billDetails.getInvoiceNo(), 30, "发票号码");

        checkNullAndLength(billDetails, billDetails.getInvoiceCode(), 30, "发票代码");

        checkNullAndLength(billDetails, billDetails.getCustomerName(), 50, "客户名称");

        checkNullAndLength(billDetails, billDetails.getCustomerNumber(), 20, "客户识别号");


        if (null == billDetails.getInvoicedAmount()) {
            billDetails.appendErrorMessage("开票金额不可为空");
            billDetails.setCorrect(false);
        }

        if (null == billDetails.getBillBalance()) {
            billDetails.appendErrorMessage("价税不可为空");
            billDetails.setCorrect(false);
        }

        if (null == billDetails.getInvoicedDate()) {
            billDetails.appendErrorMessage("开票日期不可为空");
            billDetails.setCorrect(false);
        }


    }


    private void checkNullAndLength(BillDetails billDetails, String checkMsg, int length, String tip) {

        if (StringUtils.isBlank(checkMsg)) {
            billDetails.setCorrect(false);
            billDetails.appendErrorMessage(tip + "不能为空");
        } else {
            if (checkMsg.length() > length) {
                billDetails.setCorrect(false);
                billDetails.appendErrorMessage(tip + "长度过长");
            }
        }

    }


    /**
     * 计算匹配率
     *
     * @param matchNum
     * @param totalNum
     * @return
     */
    public String calcMatchRate(int matchNum, int totalNum) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (matchNum == 0 || totalNum == 0) {
            return "0%";
        }
        double rate = (double) matchNum / (double) totalNum * 100;

        return df.format(rate) + "%";


    }

    public List<BillDetails> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<BillDetails> matchList) {
        this.matchList = matchList;
    }

    public String getMatchRate() {
        return matchRate;
    }

    public void setMatchRate(String matchRate) {
        this.matchRate = matchRate;
    }

    public List<BillDetails> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<BillDetails> errorList) {
        this.errorList = errorList;
    }

    public List<BillDetails> getBillList() {
        return billList;
    }

    public void setBillList(List<BillDetails> billList) {
        this.billList = billList;
    }


    @Override
    public String toString() {
        return "BillDetailsFilter{" +
                "matchList=" + matchList +
                ", matchRate='" + matchRate + '\'' +
                ", errorList=" + errorList +
                ", billList=" + billList +
                '}';
    }
}
