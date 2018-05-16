package com.jinhui.controller.saleFeedBacks;

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
import com.github.pagehelper.PageInfo;
import com.jinhui.controller.products.ProductsController;
import com.jinhui.mapper.user.UserMapper;
import com.jinhui.model.Assets;
import com.jinhui.model.Collection;
import com.jinhui.model.PlatFeedbacks;
import com.jinhui.model.Products;
import com.jinhui.model.SaleFeedbacks;
import com.jinhui.service.products.ProductsService;
import com.jinhui.service.saleFeedBacks.CollectionService;
import com.jinhui.service.saleFeedBacks.SaleFeedBacksService;
import com.jinhui.util.FormatReturn;
import com.jinhui.util.TokenUtil;

@Controller
@RequestMapping("/saleFeedbacks")
public class SaleFeedbacksController {
	private static Logger logger = LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	private SaleFeedBacksService saleFeedBacksService;
	
	@Autowired
	private  UserMapper userMapper;
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private CollectionService collectionService;

	
	
	/**
	 * 查询资产平台的反馈
	 * @param assetId
	 * @return
	 */
	@RequestMapping(value="/queryPlatbacksListByAssetId",method=RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryPlatbacksListByAssetId(int assetId){
		logger.info("输入：  "+ "assetId="+assetId);
		List<PlatFeedbacks> platFeedbacksList = saleFeedBacksService.getPlatbacksListByAssetId(assetId);
		logger.info("输出：   "+JSON.toJSONString(platFeedbacksList));
		return JSON.toJSONString(platFeedbacksList,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 查询 是否已收藏
	 * @param assetId
	 * @param feedType  2 资产   3 产品
	 * @return
	 */
	@RequestMapping(value="/isCollectOrNot",method=RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryCollectionAsset(int assetId,int gid,int feedType){
		logger.info("输入：  "+ " assetId="+assetId+" gid="+gid+" feedType="+feedType);
		Assets asset = new Assets();
		asset.setAssetId(assetId);
		asset.setGid(gid);
		asset.setFeedType(feedType);
		Collection collection = collectionService.getCollectionAsset(asset);
		String errorCode;
		if(collection == null){
			errorCode="0";
		}else{
			errorCode="1";
		}
		logger.info("输出：   "+JSON.toJSONString(errorCode));
		return JSON.toJSONString(errorCode,SerializerFeature.WriteMapNullValue);
	}
	
	
	/**
	 * 查询机构 收藏的资产集合
	 * @param assetId
	 * @return
	 */
	@RequestMapping(value="/queryCollectionAssetList",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryCollectionAssetList(Integer pageNum, Integer pageSize,int gid){
		logger.info("输入：  "+ "pageNum="+pageNum+"pageSize="+ pageSize+"gid="+gid);
		Assets Asset = new Assets();
		Asset.setGid(gid);		
		PageInfo<Collection> collectionList = collectionService.getCollectionAssetList(pageNum,pageSize,Asset);
		logger.info("输出：   "+JSON.toJSONString(collectionList));
		return JSON.toJSONString(collectionList,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 我的收藏夹-收藏的产品 
	 * @param pageNo  当前页
	 * @param pageSize  每页记录数
	 * @return
	 */
	@RequestMapping(value="/getCollectProducts",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String getCollectProducts(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize,@RequestParam("orgId") String orgId){
		logger.info("输入：  "+ "pageNum="+pageNum+"  pageSize="+pageSize+" orgId="+orgId);
		PageInfo<Products> productsList = productsService.getCollectProducts(pageNum,pageSize,orgId);
		logger.info("输出：   "+JSON.toJSONString(productsList));
		return JSON.toJSONString(productsList,SerializerFeature.WriteMapNullValue);
	}
	/**
	 * 查询单个资产的销售意向及反馈列表
	 * @param assetId
	 * @return
	 */
	@RequestMapping(value="/queryFeedbacksListByAssetId",method=RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryFeedbacksListByAssetId(int assetId,@RequestParam("gid") String gid){
		logger.info("输入：  "+ " assetId="+assetId+" gid="+gid);
		List<SaleFeedbacks> saleFeedbacksList = saleFeedBacksService.getFeedbacksListByProductId(assetId,gid);
		logger.info("输出：   "+JSON.toJSONString(saleFeedbacksList));
		return JSON.toJSONString(saleFeedbacksList,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 查询单个产品的销售意向及反馈列表
	 * @param productId
	 * @param gid 机构id  为null查询所有的反馈 
	 * @return
	 */
	@RequestMapping(value="/queryFeedbacksListByProductId",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryFeedbacksListByProductId(@RequestParam("productId") int productId,@RequestParam("gid") String gid){
		logger.info("输入：  "+ "productId="+productId+" gid="+gid);
		List<SaleFeedbacks> saleFeedbacksList = saleFeedBacksService.getFeedbacksListByProductId(productId,gid);
		logger.info("输出：   "+JSON.toJSONString(saleFeedbacksList));
		return JSON.toJSONString(saleFeedbacksList,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 添加销售意向及反馈
	 * @param saleInfo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/addSaleFeedbacks",method=RequestMethod.POST)
	@ResponseBody
	public String addSaleFeedbacks(HttpServletRequest request,SaleFeedbacks saleFeedbacksInfo) throws Exception{
		logger.info("输入：  "+ "saleFeedbacksInfo="+saleFeedbacksInfo);
		TokenUtil.tokenOperate(request,userMapper);
		int flag = saleFeedBacksService.addSaleFeedbacks(saleFeedbacksInfo);
		logger.info("输出：   "+JSON.toJSONString(FormatReturn.format(flag)));
		return JSON.toJSONString(FormatReturn.format(flag));
	}
	
	/**
	 * 收藏产品或资产
	 * @param saleInfo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/collect",method=RequestMethod.POST)
	@ResponseBody
	public String collectSaleFeedbacks(HttpServletRequest request,Collection collect) throws Exception{
		logger.info("输入：  "+ "saleFeedbacksInfo="+collect);
		TokenUtil.tokenOperate(request,userMapper);
		int count = collectionService.countBySelective(collect.getAssetId(), collect.getOrgId(), collect.getFeedType());
		int flag = 0;
		if(count == 0){  //不存在收藏记录
			flag = collectionService.collect(collect);
		}
		logger.info("输出：   "+JSON.toJSONString(FormatReturn.format(flag)));
		return JSON.toJSONString(FormatReturn.format(flag));
	}
	
	/**
	 * 取消收藏产品或资产
	 * @param 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/cancelCollect",method=RequestMethod.POST)
	@ResponseBody
	public String cancelCollect(HttpServletRequest request,@RequestParam("assetId") int assetId,@RequestParam("gid") String gid,@RequestParam("feedType") String feedType) throws Exception{
		logger.info("输入：  "+ "productId="+assetId+" gid="+gid+" feedType="+feedType);
		TokenUtil.tokenOperate(request,userMapper);
		int flag = collectionService.cancelCollect(assetId,gid,feedType);
		logger.info("输出：   "+JSON.toJSONString(FormatReturn.format(flag)));
		return JSON.toJSONString(FormatReturn.format(flag));
	}
	
	/**
	 * 修改销售意向及反馈
	 * @param saleFeedbacksInfo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/updateSaleFeedbacks",method=RequestMethod.POST)
	@ResponseBody
	public String updateSale(HttpServletRequest request,SaleFeedbacks saleFeedbacksInfo) throws Exception{
		logger.info("输入：  "+ "saleFeedbacksInfo="+saleFeedbacksInfo);
		TokenUtil.tokenOperate(request,userMapper);
		int flag = saleFeedBacksService.updateFeedbacksBySelective(saleFeedbacksInfo);
		logger.info("输出：   "+JSON.toJSONString(FormatReturn.format(flag)));
		return JSON.toJSONString(FormatReturn.format(flag));
	}
	
	/**
	 * 删除销售意向及反馈
	 * @param feedbacksId 销售意向及反馈Id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/deleteSaleFeedbacks",method=RequestMethod.POST)
	@ResponseBody
	public String deleteSaleFeedbacks(HttpServletRequest request,@RequestParam("feedbacksId") int feedbacksId) throws Exception{
		logger.info("输入：  "+ "feedbacksId="+feedbacksId);
		TokenUtil.tokenOperate(request,userMapper);
		int flag = saleFeedBacksService.deleteSaleFeedbacks(feedbacksId);
		logger.info("输出：   "+JSON.toJSONString(FormatReturn.format(flag)));
		return JSON.toJSONString(FormatReturn.format(flag));
	}
	
}
