package com.jinhui.controller.address;

import java.util.List;

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
import com.jinhui.mapper.address.ChnCityMapper;
import com.jinhui.mapper.address.ChnProvinceMapper;
import com.jinhui.model.ChnCity;
import com.jinhui.model.ChnProvince;

/**
 * 省市区
 * @time 2017-4-11 下午4:01:12  
 * @author wsc
 * @desc
 *
 */
@Controller
@RequestMapping("/address")
public class AddressController {
	private static Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired
	private ChnProvinceMapper chnProvinceMapper;
	
	@Autowired
	private ChnCityMapper chnCityMapper;
	
	/**
	 * 查询所有省份
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/queryProvinceList",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryProvinceList(){
		List<ChnProvince> provinceList = chnProvinceMapper.selectProvinceList();
		logger.info("输出：   "+JSON.toJSONString(provinceList));
		return JSON.toJSONString(provinceList,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 根据省份编码查询城市
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/queryCityListByProCode",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryCityListByProCode(@RequestParam("provinceCode") String provinceCode){
		logger.info("输入：  "+ "provinceCode="+provinceCode);
		List<ChnCity> cityList = chnCityMapper.selectCityListByProvinceCode(provinceCode);
		logger.info("输出：   "+JSON.toJSONString(cityList));
		return JSON.toJSONString(cityList,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 根据省份编码查询省份名称
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/queryProvinceInfo",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryProvinceInfo(@RequestParam("provinceCode") String provinceCode){
		logger.info("输入：  "+ "provinceCode="+provinceCode);
		ChnProvince province = chnProvinceMapper.selectByProvinceCode(provinceCode);
		logger.info("输出：   "+JSON.toJSONString(province));
		return JSON.toJSONString(province,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 根据城市编码查询城市名称
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/queryCityInfo",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryCityInfo(@RequestParam("cityCode") String cityCode){
		logger.info("输入：  "+ "cityCode="+cityCode);
		ChnCity city = chnCityMapper.selectByCityCode(cityCode);
		logger.info("输出：   "+JSON.toJSONString(city));
		return JSON.toJSONString(city,SerializerFeature.WriteMapNullValue);
	}
	
	
	
}
