package com.jinhui.mapper.abs;


import com.jinhui.model.abs.PeAssetChangeHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PeAssetChangeHistoryMapper {

    int insertSelective(PeAssetChangeHistory record);

    PeAssetChangeHistory selectByPrimaryKey(Integer id);


    /**
     * 批量保存
     * @param list
     * @return
     */
    int insertBatchInit(List list);

    /**
     * 查询是否已记录逾期资产记录
     * @param aid
     * @return
     */
    int selectHaveSaveRecord(@Param("aid") Integer aid);
}