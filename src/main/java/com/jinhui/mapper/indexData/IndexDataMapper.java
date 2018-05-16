package com.jinhui.mapper.indexData;

import com.jinhui.model.IndexData;


public interface IndexDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IndexData record);

    int insertSelective(IndexData record);

    IndexData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndexData record);

    int updateByPrimaryKey(IndexData record);
    
    /**
     * 查询首页最新数据
     * @return
     */
    IndexData getIndexData();
}