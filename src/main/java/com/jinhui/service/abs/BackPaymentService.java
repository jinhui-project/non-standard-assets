package com.jinhui.service.abs;

import com.jinhui.exception.AbsException;
import com.jinhui.model.abs.*;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/9/28 0028.
 */
public interface BackPaymentService {

    /**
     * 从“账单和回款模板”文件流中，导入回款明细
     *
     * @param inputStream
     * @return 返回白名单过滤后的结果
     */
    BackPaymentFilter importPayments(InputStream inputStream) throws AbsException;


    /**
     * 保存匹配的回款信息
     * @param matchList
     * @return
     */
    List<PeBackpaymentRecord> saveMatchPayments(List<BackPayment> matchList) throws AbsException;



    /**
     * 保存所有的回款信息（包括错误）
     * @return
     * @throws AbsException
     */
    List<PeAccountsReceivable> saveAllPayments(BackPaymentResult backPaymentResult) throws AbsException;


    /**
     * 查询未处理的回款记录
     * @return
     */
    List<PeBackpaymentRecord> selectBackpayRecordList();

    /**
     * 部分匹配
     * @param amount
     * @return
     */
    int partialMatch(Integer id,BigDecimal amount);

    /**
     * 完全匹配
     * @return
     */
    int perfectMatch(Integer id);

}
