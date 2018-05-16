package com.jinhui.controller.indexData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jinhui.model.IndexData;
import com.jinhui.model.IndexDataJson;
import com.jinhui.model.Products;
import com.jinhui.service.indexData.IndexDataService;
import com.jinhui.util.FormatReturn;

/**
 * 首页数据控制层
 * @author wsc
 *
 */
@Controller
@RequestMapping("/indexData")
public class IndexDataController {
	private static Logger logger = LoggerFactory.getLogger(IndexDataController.class);

	@Autowired
	private IndexDataService indexDataService;
	
	/**
	 * 查询首页数据
	 * @return
	 */
	@RequestMapping(value="/queryIndexData",method=RequestMethod.GET)
	@ResponseBody
	public String queryIndexData(){
		logger.info("输入：  "+"");
		IndexData indexData = indexDataService.getIndexData();
		IndexDataJson indexDataJson = JSON.parseObject(indexData.getDatas(),IndexDataJson.class);
		indexData.setAsset(indexDataJson.getAsset());
		indexData.setHaveProducts(indexDataJson.getHave_products());
		indexData.setServices(indexDataJson.getServices());
		indexData.setAverageRate(indexDataJson.getAverage_rate());
		indexData.setFunds(indexDataJson.getFunds());
		indexData.setProducts(indexDataJson.getProducts());
		logger.info("输出：   "+JSON.toJSONString(indexData));
		return JSON.toJSONString(indexData);
	}
	
	/**
	 * 添加首页数据
	 * @param indexData
	 * @return
	 */
	@RequestMapping(value="/addIndexData",method=RequestMethod.POST)
	@ResponseBody
	public String addIndexData(IndexData indexData){
		logger.info("输入：  "+indexData.toString());
		int flag = indexDataService.addIndexData(indexData);
		logger.info("输出：   "+JSON.toJSONString(FormatReturn.format(flag)));
		return JSON.toJSONString(FormatReturn.format(flag));
	}
	
}
