package com.jinhui.service.sales;

import java.util.List;
import com.jinhui.model.Sales;

/**
 * 销售计划服务层接口
 * @author wsc
 *
 */
public interface SalesService {
	/**
	 * 查询单个产品的销售计划列表
	 * @return
	 */
	public List<Sales> getSalesListByProductId(Integer productId,String gid);
	
	/**
	 * 添加销售计划
	 * @param sales
	 */
	public int addSales(Sales sales);
	
	/**
	 * 修改销售计划
	 * @param sales
	 * @return
	 */
	public int updateSalesBySelective(Sales sales);
	/**
	 * 删除销售计划
	 * @param salesId
	 */
	public int deleteSales(Integer salesId);
}
