package com.jinhui.mapper.assets;

import com.jinhui.model.SupplyChainAsset;



public interface SupplyChainAssetMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(SupplyChainAsset record);

    int insertSelective(SupplyChainAsset record);

    SupplyChainAsset selectByPrimaryKey(Integer aid);
    
    SupplyChainAsset selectByAssetId(Integer assetId);

    int updateByPrimaryKeySelective(SupplyChainAsset record);
    
    int updateByAssetsidSelective(SupplyChainAsset record);

    int updateByPrimaryKey(SupplyChainAsset record);
}