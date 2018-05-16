package com.jinhui.mapper.saleFeedBacks;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.Assets;
import com.jinhui.model.Collection;
import com.jinhui.model.SaleFeedbacks;

public interface CollectionMapper {

	int insertSelective(Collection record);

    int countBySelective(@Param("productId") Integer productId,@Param("gid") String gid,@Param("feedType") String feedType);
    
    /**
     * 取消收藏
     * @param productId
     * @param gid
     * @return
     */
    int cancelCollect(@Param("productId") Integer productId,@Param("gid") String gid,@Param("feedType") String feedType);
    
    
    public  Collection  getCollectionAsset(Assets asset);
    
    public  List<Collection>  getCollectionAssetList(Assets asset);
}