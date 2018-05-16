package com.jinhui.mapper.products;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.Products;


/**
 * 产品DAO层
 * @author wsc
 *
 */
public interface ProductsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Products record);

    int insertSelective(Products record);

    Products selectByPrimaryKey(@Param("id") Integer id,@Param("gid") String gid);
    
   
    int updateByPrimaryKeySelective(Products record);
    
    /**
     * 定时任务修改产品状态
     * @param record
     * @return
     */
    int updateProductStatusForQuartz(Products record);

    int updateByPrimaryKey(Products record);
    
    /**
	 * 分页查询我的工作台--》产品列表(备案机构方)
	 * @param orgId 机构代码
	 * @return
	 */
    List<Products> getRecordProductList(@Param("orgId") String orgId);
    
    /**
	 * 分页查询我的工作台--》产品列表(资金方_我关注的)
	 * @param orgId 机构代码
	 * @return
	 */
    List<Products> getFeedBackProductList(@Param("orgId") String orgId);
    /**
   	 * 我的收藏夹-收藏的产品 
   	 * @param orgId 机构代码
   	 * @return
   	 */
       List<Products> getCollectProducts(@Param("orgId") String orgId);
    
    /**
	 * 分页查询我的工作台--》产品列表(资金方_我销售的)
	 * @param orgId 机构代码
	 * @return
	 */
    List<Products> getSaleProductList(@Param("orgId") String orgId);
    
    /**
	 * 分页查询我的工作台--》产品列表(资产方)
	 * @param orgId 机构代码
	 * @return
	 */
    List<Products> getPropertyProductList(@Param("orgId") String orgId);
    
    /**
	 * 分页查询产品专场所有产品
	 * @param pageNum
	 * @param pageSize
	 * @param termDay  投资期限
	 * @param beginAmount  起投金额
	 * @param incomeWay  收益方式
	 * @return
	 */
    List<Products> getAllProductList(@Param("termDay")String termDay,@Param("beginAmount")String beginAmount,
    		@Param("incomeWay")String incomeWay,@Param("status")Integer status,@Param("yieldRateBegin")Double yieldRateBegin,
    		@Param("yieldRateEnd")Double yieldRateEnd);
    
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
     * 冻结解冻产品
     * @param id
     * @return
     */
    int deleteProduct(@Param("id")Integer id);
    
    /**
     * 查询资产生成的产品列表
     * @return
     */
    List<Products> getProductsByAssetId(@Param("assetId")Integer assetId);
    

}