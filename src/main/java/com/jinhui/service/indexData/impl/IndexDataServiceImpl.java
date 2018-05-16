package com.jinhui.service.indexData.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jinhui.mapper.indexData.IndexDataMapper;
import com.jinhui.model.IndexData;
import com.jinhui.service.indexData.IndexDataService;

/**
 * 首页数据服务层实现 
 * @author wsc
 *
 */
@Service("indexDataService")
public class IndexDataServiceImpl implements IndexDataService {
	@Resource
	private IndexDataMapper indexDataMapper;
	
	/**
	 * 查询首页最新数据
	 */
	public IndexData getIndexData() {
		return this.indexDataMapper.getIndexData();
	}

    /**
     * 新增首页最新数据
     */
	public int addIndexData(IndexData indexData) {
		return this.indexDataMapper.insertSelective(indexData);
	}

}
