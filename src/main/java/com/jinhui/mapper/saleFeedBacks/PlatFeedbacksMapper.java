package com.jinhui.mapper.saleFeedBacks;

import java.util.List;

import com.jinhui.model.PlatFeedbacks;



public interface PlatFeedbacksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlatFeedbacks record);

    int insertSelective(PlatFeedbacks record);

    PlatFeedbacks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlatFeedbacks record);

    int updateByPrimaryKey(PlatFeedbacks record);
    
    List<PlatFeedbacks> getPlatbacksListByAssetId(Integer assetId);
}