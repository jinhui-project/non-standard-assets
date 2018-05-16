package com.jinhui.mapper.assets;

import com.jinhui.model.IpoAsset;



public interface IpoAssetMapper {
    int deleteByPrimaryKey(Integer aid);

    int insertSelective(IpoAsset record);

    IpoAsset selectByPrimaryKey(Integer aid);
   
    IpoAsset selectByAssetId(Integer assetId);

    int updateByPrimaryKeySelective(IpoAsset record);

    int updateByPrimaryKey(IpoAsset record);
    
    int updateByAssetsidSelective(IpoAsset record);
}