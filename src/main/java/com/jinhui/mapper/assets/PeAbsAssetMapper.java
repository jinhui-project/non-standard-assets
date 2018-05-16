package com.jinhui.mapper.assets;

import com.jinhui.model.PeAbsAsset;



public interface PeAbsAssetMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(PeAbsAsset record);

    int insertSelective(PeAbsAsset record);

    PeAbsAsset selectByPrimaryKey(Integer aid);
    
    PeAbsAsset selectByAssetId(Integer assetId);

    int updateByPrimaryKeySelective(PeAbsAsset record);
    
    int updateByAssetsidSelective(PeAbsAsset record);

    int updateByPrimaryKey(PeAbsAsset record);
}