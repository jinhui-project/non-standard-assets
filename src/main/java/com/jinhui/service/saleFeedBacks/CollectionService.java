package com.jinhui.service.saleFeedBacks;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.jinhui.model.Assets;
import com.jinhui.model.Collection;
import com.jinhui.model.PlatFeedbacks;
import com.jinhui.model.SaleFeedbacks;

/**
 * 收藏服务层接口
 * @author wsc
 *
 */
public interface CollectionService {
	
	/**
	 * 收藏
	 * @param 
	 */
	public int collect(Collection collect);
	
	/**
	 * 取消收藏
	 * @param feedbacksId
	 */
	public int cancelCollect(Integer productId,String gid,String feedType);
	/**
	 * 查询存在记录数
	 * @param feedbacksId
	 */
	public int countBySelective(Integer productId,String gid,String feedType);
	
    public  PageInfo<Collection>  getCollectionAssetList(Integer pageNum, Integer pageSize,Assets asset);
	
	public  Collection  getCollectionAsset(Assets asset);
}
