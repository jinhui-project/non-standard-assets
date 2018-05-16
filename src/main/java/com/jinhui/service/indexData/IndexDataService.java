package com.jinhui.service.indexData;

import com.jinhui.model.IndexData;

/**
 * 首页数据服务层接口
 * @author wsc
 *
 */
public interface IndexDataService {
	/**
     * 查询首页最新数据
     * @return
     */
	public IndexData getIndexData();
	
	/**
     * 新增首页最新数据
     * @return
     */
	public int addIndexData(IndexData indexData);
}
