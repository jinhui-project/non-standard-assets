package com.jinhui.service.saleFeedBacks.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinhui.mapper.saleFeedBacks.CollectionMapper;
import com.jinhui.model.Assets;
import com.jinhui.model.Collection;
import com.jinhui.model.SaleFeedbacks;
import com.jinhui.service.saleFeedBacks.CollectionService;

/**
 * 销售意向及反馈服务层实现 
 * @author wsc
 *
 */

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {
	
	@Resource
	private CollectionMapper collectionMapper;
	
	public int collect(Collection collect) {
		return collectionMapper.insertSelective(collect);
	}
	
	public int cancelCollect(Integer productId, String gid,String feedType) {
		return collectionMapper.cancelCollect(productId,gid,feedType);
	}
	
	public int countBySelective(Integer productId, String gid,String feedType) {
		return collectionMapper.countBySelective(productId,gid,feedType);
	}

	public  Collection  getCollectionAsset(Assets asset){
		return collectionMapper.getCollectionAsset(asset);	
	}
	
	public PageInfo<Collection>  getCollectionAssetList(Integer pageNum, Integer pageSize,Assets asset){
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNum, pageSize);
		List<Collection> list = collectionMapper.getCollectionAssetList(asset);
		return new PageInfo<Collection>(list);		
	}

}
