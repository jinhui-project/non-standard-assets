package com.jinhui.mapper.abs;

import com.jinhui.model.abs.PeBackpaymentRecordAll;

public interface PeBackpaymentRecordAllMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PeBackpaymentRecordAll record);

    int insertSelective(PeBackpaymentRecordAll record);

    PeBackpaymentRecordAll selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PeBackpaymentRecordAll record);

    int updateByPrimaryKey(PeBackpaymentRecordAll record);
}