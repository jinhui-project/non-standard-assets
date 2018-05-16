package com.jinhui.mapper.saleFeedBacks;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.Assets;
import com.jinhui.model.SaleFeedbacks;;

public interface SaleFeedbacksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaleFeedbacks record);

    int insertSelective(SaleFeedbacks record);

    SaleFeedbacks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleFeedbacks record);

    int updateByPrimaryKey(SaleFeedbacks record);
    
    List<SaleFeedbacks> getFeedbacksListByProductId(@Param("productId") Integer productId,@Param("gid") String gid);
    
    int countBySelective(@Param("productId") Integer productId,@Param("gid") String gid,@Param("feedType") String feedType);
    
    /**
     * 取消收藏
     * @param productId
     * @param gid
     * @return
     */
    int cancelCollect(@Param("productId") Integer productId,@Param("gid") String gid,@Param("feedType") String feedType);
    
    
    public  SaleFeedbacks  getCollectionAsset(Assets asset);
    
    public  List<SaleFeedbacks>  getCollectionAssetList(Assets asset);
}