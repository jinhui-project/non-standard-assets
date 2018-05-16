package com.jinhui.service.saleFeedBacks;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.jinhui.model.Assets;
import com.jinhui.model.PlatFeedbacks;
import com.jinhui.model.SaleFeedbacks;

/**
 * 销售意向及反馈服务层接口
 * @author wsc
 *
 */
public interface SaleFeedBacksService {
	
	public  PageInfo<SaleFeedbacks>  getCollectionAssetList(Integer pageNum, Integer pageSize,Assets asset);
	
	public  SaleFeedbacks  getCollectionAsset(Assets asset);
	
	public  List<PlatFeedbacks> getPlatbacksListByAssetId(Integer assetId);
	/**
	 * 查询单个产品的销售意向及反馈
	 * @return
	 */
	public List<SaleFeedbacks> getFeedbacksListByProductId(Integer productId,String gid);
	
	/**
	 * 添加销售意向及反馈
	 * @param saleFeedbacks
	 */
	public int addSaleFeedbacks(SaleFeedbacks saleFeedbacks);
	
	/**
	 * 修改销售意向及反馈
	 * @param saleFeedbacks
	 * @return
	 */
	public int updateFeedbacksBySelective(SaleFeedbacks saleFeedbacks);
	/**
	 * 删除销售意向及反馈
	 * @param feedbacksId
	 */
	public int deleteSaleFeedbacks(Integer feedbacksId);
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
}
