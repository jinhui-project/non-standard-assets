package com.jinhui.controller.products;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.github.pagehelper.PageInfo;
import com.jinhui.model.Products;
import com.jinhui.service.products.ProductsService;
import com.jinhui.util.FormatReturn;

@Controller
@RequestMapping("/products")
public class ProductsController {
	private static Logger logger = LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	private ProductsService productsService;
	
	/**
	 * 查询单个产品详情
	 * @param productId
	 * @return
	 */
	@RequestMapping(value="/queryProductInfo",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryProductInfo(@RequestParam("productId") int productId,@RequestParam("gid") String gid){
		logger.info("输入：  "+ "productId="+productId+" gid="+gid);
		Products products = productsService.getProductsById(productId,gid);
		logger.info("输出：   "+JSON.toJSONString(products));
		return JSON.toJSONString(products,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 分页查询当前用户的产品
	 * @param pageNo  当前页
	 * @param pageSize  每页记录数
	 * @return
	 */
	@RequestMapping(value="/queryProductList",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryProductList(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize,
			@RequestParam("roleType") String roleType,@RequestParam("orgId") String orgId){
		logger.info("输入：  "+ "pageNum="+pageNum+"  pageSize="+pageSize+" roleType(1 备案机构   2 资金方(我关注的) 3资金方(我销售的)  4 资产方 5 其他角色)="+roleType+" orgId="+orgId);
		PageInfo<Products> productsList = productsService.getProductList(pageNum,pageSize,roleType,orgId);
		logger.info("输出：   "+JSON.toJSONString(productsList));
		return JSON.toJSONString(productsList,SerializerFeature.WriteMapNullValue);
	}
	
	 /**
		 * 分页查询产品专场所有产品
		 * @param pageNum
		 * @param pageSize
		 * @param termDay  投资期限
		 * @param beginAmount  起投金额
		 * @param incomeWay  收益方式
		 * @return
		 */
	@RequestMapping(value="/queryAllProductList",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryAllProductList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize, @RequestParam("status") Integer status,
			@RequestParam("termDay") String termDay,@RequestParam("beginAmount") String beginAmount,@RequestParam("incomeWay") String incomeWay, 
			@RequestParam("yieldRateBegin") String yieldRateBegin,@RequestParam("yieldRateEnd") String yieldRateEnd){
		logger.info("输入：  "+ "pageNum="+pageNum+"  pageSize="+pageSize+" termDay="+termDay+" beginAmount="+beginAmount+" incomeWay="+incomeWay+" status="+status + " yieldRateBegin="+yieldRateBegin +" yieldRateEnd="+yieldRateEnd);
		Double yieldRateBeginD = null;
		Double yieldRateEndD = null;
		if(StringUtils.isNotEmpty(yieldRateBegin)){
			yieldRateBeginD = Double.parseDouble(yieldRateBegin);
		}
		if(StringUtils.isNotEmpty(yieldRateEnd)){
			yieldRateEndD = Double.parseDouble(yieldRateEnd);
		}
		PageInfo<Products> productsList = productsService.queryAllProductList(pageNum,pageSize,termDay,beginAmount,incomeWay,status,yieldRateBeginD,yieldRateEndD);
		logger.info("输出：   "+JSON.toJSONString(productsList));
		return JSON.toJSONString(productsList,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 查询资产生成的产品列表
	 * @param assetId
	 * @return
	 */
	@RequestMapping(value="/queryProductsByAssetId",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryProductInfo(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("assetId") int assetId){
		logger.info("输入：  "+ "pageNum="+pageNum+"  pageSize="+pageSize+ " productId="+assetId);
		PageInfo<Products> productsList = productsService.getProductsByAssetId(pageNum,pageSize,assetId);
		logger.info("输出：   "+JSON.toJSONString(productsList));
		return JSON.toJSONString(productsList,SerializerFeature.WriteMapNullValue);
	}
	
}
