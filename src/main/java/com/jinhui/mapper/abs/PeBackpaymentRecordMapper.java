package com.jinhui.mapper.abs;

import com.jinhui.model.abs.PaymentQueryParam;
import com.jinhui.model.abs.PeBackpaymentRecord;

import java.util.List;

public interface PeBackpaymentRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PeBackpaymentRecord record);

    int insertSelective(PeBackpaymentRecord record);

    PeBackpaymentRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PeBackpaymentRecord record);

    int updateByPrimaryKey(PeBackpaymentRecord record);


    /**
     * 根据交易时间戳查询
     */
    PeBackpaymentRecord selectByTradeTimestamp(String tradeTimestamp);



    /**
     * 条件查询出已经回款的记录
     */
    List<PeBackpaymentRecord> selectByQueryParam(PaymentQueryParam param);


    /**
     * 查询未处理的回款记录
     * @return
     */
    List<PeBackpaymentRecord> selectBackpayRecordList();

    /**
     * 部分匹配
     * @return
     */
    int partialMatch(PeBackpaymentRecord record);

    /**
     * 完全匹配
     * @return
     */
    int perfectMatch(Integer id);
}