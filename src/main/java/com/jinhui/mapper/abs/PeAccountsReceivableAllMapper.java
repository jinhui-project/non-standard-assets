package com.jinhui.mapper.abs;

import com.jinhui.model.abs.PeAccountsReceivableAll;

public interface PeAccountsReceivableAllMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PeAccountsReceivableAll record);

    int insertSelective(PeAccountsReceivableAll record);

    PeAccountsReceivableAll selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PeAccountsReceivableAll record);

    int updateByPrimaryKey(PeAccountsReceivableAll record);
}