package com.jinhui.service.sales.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jinhui.mapper.sales.SalesMapper;
import com.jinhui.model.Sales;
import com.jinhui.service.sales.SalesService;

/**
 * 销售计划服务层实现类
 * @author wsc
 *
 */
@Service("salesService")
public class SalesServiceImpl implements SalesService {
	
	@Resource
	private SalesMapper salesMapper;

	public List<Sales> getSalesListByProductId(Integer productId,String gid) {
		if("".equals(gid) || gid == null || "null".equals(gid)){
			gid = null;
		}
		return this.salesMapper.getSalesListByProductId(productId,gid);
	}

	public int addSales(Sales sales) {
		return this.salesMapper.insertSelective(sales);
	}

	public int updateSalesBySelective(Sales sales) {
		return this.salesMapper.updateByPrimaryKeySelective(sales);
	}

	public int deleteSales(Integer salesId) {
		return this.salesMapper.deleteByPrimaryKey(salesId);
	}

}
