package com.jinhui.mapper.assets;

import com.jinhui.model.IndustryCommunityAsset;
import com.jinhui.model.UsufructTransferAsset;

/**
 * 其他工商类
 * @time 2017-3-28 上午10:15:20  
 * @author wsc
 * @desc
 *
 */
public interface IndustryCommunityAssetMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insertSelective(IndustryCommunityAsset record);

    IndustryCommunityAsset selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndustryCommunityAsset record);
    
    int updateByAssetsidSelective(IndustryCommunityAsset record);
    
    IndustryCommunityAsset selectByAssetId(Integer assetId);

}