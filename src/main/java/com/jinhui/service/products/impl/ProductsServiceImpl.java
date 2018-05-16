package com.jinhui.service.products.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinhui.mapper.products.ProductsMapper;
import com.jinhui.model.Products;
import com.jinhui.service.products.ProductsService;

/**
 *  产品服务层实现类
 * @author wsc
 *
 */
@Service("productsService")
public class ProductsServiceImpl implements ProductsService {

	@Resource
	private ProductsMapper productsMapper;

	public Products getProductsById(int productId,String gid) {
		if("".equals(gid) || gid == null || "null".equals(gid)){
			gid = null;
		}
		return this.productsMapper.selectByPrimaryKey(productId,gid);
	}
    
	/**
	 * 分页查询我的工作台--》产品列表
	 * @param pageNo
	 * @param pageSize
	 * @param roleType  角色类型    1 备案机构   2 资金方(我关注的) 3资金方(我销售的)  4 资产方 5 其他角色 
	 * @param orgId   机构代码
	 * @return
	 */
	public PageInfo<Products> getProductList(Integer pageNum, Integer pageSize,String roleType,String orgId) {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNum, pageSize);
		
		List<Products> list = null;
		if("1".equals(roleType)){
			list = productsMapper.getRecordProductList(orgId);   //查询我的工作台--》产品列表(备案机构方)
		}else if("2".equals(roleType)){
			list = productsMapper.getFeedBackProductList(orgId);   //查询我的工作台--》产品列表(资金方_我反馈的)
		}else if("3".equals(roleType)){
			list = productsMapper.getSaleProductList(orgId);   //查询我的工作台--》产品列表(资金方_我关注的)
		}else if("4".equals(roleType)){
			list = productsMapper.getPropertyProductList(orgId);  //查询我的工作台--》产品列表(资产方)
		}else{
			list = new ArrayList();
		}
		return new PageInfo<Products>(list);
	}
	
	public PageInfo<Products> getCollectProducts(Integer pageNum, Integer pageSize,String orgId) {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNum, pageSize);
		
		List<Products> list = productsMapper.getCollectProducts(orgId);
		return new PageInfo<Products>(list);
	}

	public int addProduct(Products product) {
		return this.productsMapper.insertSelective(product);
	}

	public int updateProductBySelective(Products product) {
		return this.productsMapper.updateByPrimaryKeySelective(product);
	}

	public int deleteProduct(Integer id) {
		return this.productsMapper.deleteProduct(id);
	}

	public List<Products> getProductsForIncomeQuartz() {
		return productsMapper.getProductsForIncomeQuartz();
	}
	
	public List<Products> getProductsForOverQuartz() {
		return productsMapper.getProductsForOverQuartz();
	}
	
	public List<Products> getProductsForOpenQuartz() {
		return productsMapper.getProductsForOpenQuartz();
	}

	@Override
	public PageInfo<Products> queryAllProductList(Integer pageNum,
			Integer pageSize, String termDay, String beginAmount,
			String incomeWay,Integer status,Double yieldRateBegin, Double yieldRateEnd) {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if("".equals(termDay) || termDay == null || "null".equals(termDay)){
			termDay = null;
		}
		if("".equals(beginAmount) || beginAmount == null || "null".equals(beginAmount)){
			beginAmount = null;
		}
		if("".equals(incomeWay) || incomeWay == null || "null".equals(incomeWay)){
			incomeWay = null;
		}
		if("".equals(yieldRateBegin) || yieldRateBegin == null || "null".equals(yieldRateBegin)){
			yieldRateBegin = null;
		}
		if("".equals(yieldRateEnd) || yieldRateEnd == null || "null".equals(yieldRateEnd)){
			yieldRateEnd = null;
		}
		List<Products> list = productsMapper.getAllProductList(termDay,beginAmount,incomeWay,status,yieldRateBegin,yieldRateEnd);
		return new PageInfo<Products>(list);
	}

	@Override
	public PageInfo<Products> getProductsByAssetId(Integer pageNum, Integer pageSize,Integer assetId) {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNum, pageSize);
		List<Products> list = productsMapper.getProductsByAssetId(assetId);
		return new PageInfo<Products>(list);
	}

	/**
	 * 定时任务修改产品状态
	 */
	public int updateProductStatusForQuartz(Products product) {
		return productsMapper.updateProductStatusForQuartz(product);
	}

}
