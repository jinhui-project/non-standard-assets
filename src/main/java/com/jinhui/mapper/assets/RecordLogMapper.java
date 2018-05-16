package com.jinhui.mapper.assets;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.RecordLog;


public interface RecordLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecordLog record);

    int insertSelective(RecordLog record);

    RecordLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecordLog record);

    int updateByPrimaryKey(RecordLog record);
    /**
     * 查询资产在某个步骤的协议文档
     * @param assetId  资产id
     * @param stepId  流程步骤id
     * @return
     */
    RecordLog queryRecordLog(@Param("assetId") String assetId,@Param("stepId") String stepId);
}