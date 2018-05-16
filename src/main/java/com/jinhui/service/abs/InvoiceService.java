package com.jinhui.service.abs;



import com.jinhui.exception.AbsException;
import com.jinhui.model.Assets;
import com.jinhui.model.abs.BillDetails;
import com.jinhui.model.abs.BillDetailsFilter;
import com.jinhui.model.abs.BillDetailsResult;
import com.jinhui.model.abs.PeAccountsReceivable;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**处理账单接口
 * @author luoyuanq on 2017/9/27 0027.
 */
public interface InvoiceService {

    /**
     * 从“账单和回款模板”文件流中，导入账单明细
     *
     * @param inputStream
     * @return 返回白名单过滤后的结果
     */
    BillDetailsFilter importInvoices(InputStream inputStream) throws AbsException;


    /**
     * 保存白名单匹配的账单
     * @param matchList
     * @return
     */
    List<PeAccountsReceivable> saveMatchBills(List<BillDetails> matchList) throws AbsException;


    /**
     * 保存所有的账单（包括错误）
     * @throws AbsException
     */
    List<PeAccountsReceivable> saveAllBills(BillDetailsResult billDetailsResult) throws AbsException;



    /**
     * 得到状态为 “待收” 账单
     * @return
     */
    List<BillDetails> getReceivingBills(List<BillDetails> matchList);

    /**
     * 得到状态为 “冲销” 账单
     * @return
     */
    List<BillDetails> getWriteOffBills(List<BillDetails> matchList);

    /**
     * 根据资产ID查询资产包中的基础资产(应收账款)
     * @return
     */
    List<PeAccountsReceivable> selectAssetByAids(List<Assets> assetsList, String debtorName,String originalHolder);

    /**
     * 查询应收债务人的基础资产
     * @return
     */
    List<PeAccountsReceivable> selectAssetByDebtor(String debtorName,String originalHolder);

    /**
     * 部分回款
     * @param id
     * @param amount
     * @return
     */
    int partialPayment(Integer id,BigDecimal amount,Integer backpaymentID);

    /**
     * 完全回款
     * @param id
     * @return
     */
    int perfectPayment(Integer id,Integer backpaymentID);

    /**
     * 查询资产包中已回款需要被替换的基础资产
     * @return
     */
    List<PeAccountsReceivable> selectHavePayed();


    /**
     * 查询可以用来替换的基础资产
     * @return
     */
    List<PeAccountsReceivable> selectCouldReplace();

    /**
     * 用来替换的基础资产
     * @return
     */
    int toReplace(Integer id,Integer aid);

    /**
     * 被替换的基础资产
     * @return
     */
    int beReplaced(Integer id);

    /**
     * 逾期
     * @return
     */
    int overdue(Integer id);

    /**
     * 逾期移除资产
     * @return
     */
    int overdueToRemove(Integer id);

    /**
     * 查询资产包那个逾期的基础资产
     * @return
     */
    List<PeAccountsReceivable> selectOverdueBaseAssets();


}
