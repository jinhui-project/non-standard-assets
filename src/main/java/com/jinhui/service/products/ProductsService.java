package com.jinhui.service.products;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.jinhui.model.Products;

/**
 * 产品服务层接口
 * @author wsc
 *
 */
public interface ProductsService {
    /**
     * 查询单个产品详情
     * @param productId
     * @return
     */
	public Products getProductsById(int productId,String gid);
	
	/**
	 * 分页查询我的工作台--》产品列表
	 * @param pageNo
	 * @param pageSize
	 * @param roleType  角色类型    1 备案机构   2 资金方(我反馈的) 3资金方(我销售的)  4 资产方 5 其他角色 
	 * @param orgId   机构代码
	 * @return
	 */
	public PageInfo<Products> getProductList(Integer pageNum,Integer pageSize,String roleType,String orgId);
	/**
	 * 我的收藏夹-收藏的产品 
	 * @param pageNum
	 * @param pageSize
	 * @param orgId
	 * @return
	 */
	public PageInfo<Products> getCollectProducts(Integer pageNum,Integer pageSize,String orgId);
	
	/**
	 * 分页查询产品专场所有产品
	 * @param pageNum
	 * @param pageSize
	 * @param termDay  投资期限
	 * @param beginAmount  起投金额
	 * @param incomeWay  收益方式
	 * @return
	 */
	public PageInfo<Products> queryAllProductList(Integer pageNum,Integer pageSize,String termDay,String beginAmount,String incomeWay,Integer status,Double yieldRateBegin, Double yieldRateEnd);
	
	/**
     * 查询“销售结束”状态的产品，用于“收益中”的定时任务
     * @return
     */
    List<Products> getProductsForIncomeQuartz();
    
    /**
     * 查询“收益中”状态的产品，用于“已兑付”的定时任务
     * @return
     */
    List<Products> getProductsForOverQuartz();
    /**
     * 查询“开发销售”状态的产品，用于“强制结束产品”定时任务
     * @return
     */
    List<Products> getProductsForOpenQuartz();
	
	/**
	 * 添加产品
	 * @param product
	 */
	public int addProduct(Products product);
	
	/**
	 * 修改产品
	 * @param product
	 * @return
	 */
	public int updateProductBySelective(Products product);
	
	/**
     * 删除 产品
     * @param id
     * @return
     */
	public int deleteProduct(Integer id);
	
	/**
     * 查询资产生成的产品列表
     * @return
     */
	PageInfo<Products> getProductsByAssetId(Integer pageNum, Integer pageSize,Integer assetId);
	/**
	 * 定时任务修改产品状态
	 * @param product
	 * @return
	 */
	public int updateProductStatusForQuartz(Products product);


}
