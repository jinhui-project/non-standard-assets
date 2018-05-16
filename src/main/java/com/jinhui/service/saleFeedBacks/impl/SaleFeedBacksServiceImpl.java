package com.jinhui.service.saleFeedBacks.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinhui.mapper.saleFeedBacks.PlatFeedbacksMapper;
import com.jinhui.mapper.saleFeedBacks.SaleFeedbacksMapper;
import com.jinhui.model.Assets;
import com.jinhui.model.PlatFeedbacks;
import com.jinhui.model.Products;
import com.jinhui.model.SaleFeedbacks;
import com.jinhui.service.saleFeedBacks.SaleFeedBacksService;

/**
 * 销售意向及反馈服务层实现 
 * @author wsc
 *
 */

@Service("saleFeedBacksService")
public class SaleFeedBacksServiceImpl implements SaleFeedBacksService {
	
	@Resource
	private SaleFeedbacksMapper saleFeedbacksMapper;
	
	@Resource
	private PlatFeedbacksMapper platFeedbacksMapper;
	
	public  SaleFeedbacks  getCollectionAsset(Assets asset){
		return saleFeedbacksMapper.getCollectionAsset(asset);		
	}
	
	public PageInfo<SaleFeedbacks>  getCollectionAssetList(Integer pageNum, Integer pageSize,Assets asset){
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNum, pageSize);
		List<SaleFeedbacks> list = saleFeedbacksMapper.getCollectionAssetList(asset);
		return new PageInfo<SaleFeedbacks>(list);		
	}
	
	
	
	
	public  List<PlatFeedbacks> getPlatbacksListByAssetId(Integer assetId) {
		return platFeedbacksMapper.getPlatbacksListByAssetId(assetId);
	}

	public List<SaleFeedbacks> getFeedbacksListByProductId(Integer productId,String gid) {
		if("".equals(gid) || gid == null || "null".equals(gid)){
			gid = null;
		}
		return saleFeedbacksMapper.getFeedbacksListByProductId(productId,gid);
	}

	public int addSaleFeedbacks(SaleFeedbacks saleFeedbacks) {
		return saleFeedbacksMapper.insertSelective(saleFeedbacks);
	}

	public int updateFeedbacksBySelective(SaleFeedbacks saleFeedbacks) {
		return saleFeedbacksMapper.updateByPrimaryKeySelective(saleFeedbacks);
	}

	public int deleteSaleFeedbacks(Integer feedbacksId) {
		return saleFeedbacksMapper.deleteByPrimaryKey(feedbacksId);
	}

	public int cancelCollect(Integer productId, String gid,String feedType) {
		return saleFeedbacksMapper.cancelCollect(productId,gid,feedType);
	}
	
	public int countBySelective(Integer productId, String gid,String feedType) {
		return saleFeedbacksMapper.countBySelective(productId,gid,feedType);
	}

}
