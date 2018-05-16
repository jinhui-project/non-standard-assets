package com.jinhui.controller.sales;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jinhui.controller.products.ProductsController;
import com.jinhui.mapper.user.UserMapper;
import com.jinhui.model.Sales;
import com.jinhui.service.sales.SalesService;
import com.jinhui.util.FormatReturn;
import com.jinhui.util.TokenUtil;

@Controller
@RequestMapping("/sales")
public class SalesController {
	private static Logger logger = LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	private SalesService salesService;
	
	@Autowired
	private  UserMapper userMapper;
	
	/**
	 * 查询单个产品的销售计划列表
	 * @param productId
	 * @return
	 */
	@RequestMapping(value="/querySalesListByProductId",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String querySalesListByProductId(@RequestParam("productId") int productId,@RequestParam("gid") String gid){
		logger.info("输入：  "+ "productId="+productId+ " gid="+gid);
		List<Sales> saleList = salesService.getSalesListByProductId(productId,gid);
		logger.info("输出：   "+JSON.toJSONString(saleList));
		return JSON.toJSONString(saleList,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 添加销售计划
	 * @param saleInfo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/addSale",method=RequestMethod.POST)
	@ResponseBody
	public String addSale(HttpServletRequest request,Sales saleInfo) throws Exception{
		logger.info("输入：  "+ "saleInfo="+saleInfo);
		TokenUtil.tokenOperate(request,userMapper);
		int flag = salesService.addSales(saleInfo);
		logger.info("输出：   "+JSON.toJSONString(FormatReturn.format(flag)));
		return JSON.toJSONString(FormatReturn.format(flag));
	}
	
	/**
	 * 修改销售计划
	 * @param saleInfo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/updateSale",method=RequestMethod.POST)
	@ResponseBody
	public String updateSale(HttpServletRequest request,Sales saleInfo) throws Exception{
		logger.info("输入：  "+ "saleInfo="+saleInfo);
		TokenUtil.tokenOperate(request,userMapper);
		int flag = salesService.updateSalesBySelective(saleInfo);
		logger.info("输出：   "+JSON.toJSONString(FormatReturn.format(flag)));
		return JSON.toJSONString(FormatReturn.format(flag));
	}
	
	/**
	 * 删除销售计划
	 * @param salesId 销售计划Id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/deleteSale",method=RequestMethod.POST)
	@ResponseBody
	public String deleteSale(HttpServletRequest request,@RequestParam("salesId") int salesId) throws Exception{
		logger.info("输入：  "+ "salesId="+salesId);
		TokenUtil.tokenOperate(request,userMapper);
		int flag = salesService.deleteSales(salesId);
		logger.info("输出：   "+JSON.toJSONString(FormatReturn.format(flag)));
		return JSON.toJSONString(FormatReturn.format(flag));
	}
	
}
