package com.jinhui.mapper.abs;

import com.jinhui.model.abs.BillQueryParam;
import com.jinhui.model.abs.PeAccountsReceivable;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PeAccountsReceivableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PeAccountsReceivable record);

    int insertSelective(PeAccountsReceivable record);

    PeAccountsReceivable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PeAccountsReceivable record);

    int updateByPrimaryKey(PeAccountsReceivable record);


    /**
     *根据开票号码和开票代码查询非冲销状态的基础资产（用于检查待入的普通资产是否有重复）
     */
    PeAccountsReceivable selectByInvoiceNoAndInvoiceCode(@Param("invoiceNo") String invoiceNo, @Param("invoiceCode") String invoiceCode);

    /**
     * 条件查询
     */
    List<PeAccountsReceivable> selectByQueryParam(BillQueryParam param);


    /**
     *根据开票号码,开票代码和开票金额查询非冲销状态的基础资产(用于检测待入的冲销资产是否合法)
     */
    PeAccountsReceivable selectByInvoiceNoAndBillAmount(@Param("invoiceNo") String invoiceNo,@Param("invoiceCode") String invoiceCode,@Param("billAmount") BigDecimal billAmount);


    int insertList(List<PeAccountsReceivable> recordList);

    /**
     * 根据资产ID查询资产包中的基础资产(应收账款)
     * @return
     */
    List<PeAccountsReceivable> selectAssetByAids(List<PeAccountsReceivable> list);

    /**
     * 查询应收债务人的基础资产
     * @return
     */
    List<PeAccountsReceivable> selectAssetByDebtor(@Param("debtorName") String debtorName,@Param("originalHolder") String originalHolder);
    
    /**
     * 部分回款
     * @return
     */
    int partialPayment(PeAccountsReceivable record);
    
    /**
     * 完全回款
     * @return
     */
    int perfectPayment(PeAccountsReceivable record);


    /**
     * 查询原始权益人的基础资产(除了冲销状态外)
     * @param originalHolder
     * @return
     */
    List<PeAccountsReceivable>  selectByOriginalExcludeWriteOff(@Param("originalHolder") String originalHolder);
   


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
     * 查询资产包那个逾期的基础资产
     * @return
     */
    List<PeAccountsReceivable> selectOverdueBaseAssets();


    /**
     * 用来替换的基础资产
     * @param record
     * @return
     */
    int toReplace(PeAccountsReceivable record);

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





}