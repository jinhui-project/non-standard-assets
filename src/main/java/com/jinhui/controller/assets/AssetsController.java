package com.jinhui.controller.assets;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.jinhui.mapper.user.UserMapper;
import com.jinhui.model.Assets;
import com.jinhui.model.AssetsDetialInfo;
import com.jinhui.model.IpoAsset;
import com.jinhui.model.PeAbsAsset;
import com.jinhui.model.RecordLog;
import com.jinhui.model.SupplyChainAsset;
import com.jinhui.model.UsufructTransferAsset;
import com.jinhui.service.assets.AssetsService;
import com.jinhui.util.ExcelImportUtil2;
import com.jinhui.util.ExcelToAssetsDetialInfoImpl;
import com.jinhui.util.FormatReturn;
import com.jinhui.util.TokenUtil;




@Controller
@RequestMapping("/assets")
public class AssetsController {
	
	private static Logger logger = LoggerFactory.getLogger(AssetsController.class);

	@Autowired
	private AssetsService assetsService;
	

	@Autowired
	private  UserMapper userMapper;
	
	/**
	 * 获取单个主资产信息
	 * @param assetId
	 * @return
	 */
	@RequestMapping(value="/queryAssetInfo",method=RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryAssetsDetailInfo(int assetId){
		logger.info("输入：  "+ "assetId="+assetId);
		Assets asset = assetsService.getAssetsById(assetId);
		logger.info("输出：  "+ JSON.toJSONString(asset,SerializerFeature.WriteMapNullValue));
		return JSON.toJSONString(asset,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 分页查询所有资产
	 * @param pageNo  当前页
	 * @param pageSize  每页记录数
	 * @return
	 */
	@RequestMapping(value="/queryAllAssets",method=RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryAllAssets(int pageNum,int pageSize){
		logger.info("输入：  "+ "pageNum="+pageNum+ "  pageSize="+pageSize);
		PageInfo<Assets> assetsLis = assetsService.getAllAssets(pageNum,pageSize);
		logger.info("输出：   "+JSON.toJSONString(assetsLis,SerializerFeature.WriteMapNullValue));
		return JSON.toJSONString(assetsLis,SerializerFeature.WriteMapNullValue);
	}
	
	
	/**
	 * 条件查询所有资产
	 * @param pageNo  当前页
	 * @param pageSize  每页记录数
	 * @param assetsInfo  查询条件
	 * @return
	 */
	@RequestMapping(value="/getAssets",method=RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String getAssets(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize,@RequestParam("status") Integer status,
			@RequestParam("assetType") Integer assetType,@RequestParam("tenorRoleType") Integer tenorRoleType,@RequestParam("scaleType") Integer scaleType,@RequestParam("financingCostStart") BigDecimal financingCostStart,@RequestParam("financingCostEnd") BigDecimal financingCostEnd){
		logger.info("输入：  "+ "pageNum="+pageNum+ "  pageSize="+pageSize+ "assetType="+assetType+ "tenorRoleType="+tenorRoleType+ "scaleType="+scaleType+ "financingCostStart="+financingCostStart+ "financingCostEnd="+financingCostEnd);
		Assets assetsInfo = new Assets();
		assetsInfo.setStatus(status);
		assetsInfo.setAssetType(assetType);
		assetsInfo.setScaleType(scaleType);
		assetsInfo.setTenorRoleType(tenorRoleType);
		assetsInfo.setFinancingCostStart(financingCostStart);
		assetsInfo.setFinancingCostEnd(financingCostEnd);
		if(tenorRoleType == null || tenorRoleType == 0){
			assetsInfo.setTenorType(null);
		}
		if(tenorRoleType != null && tenorRoleType == 1){
			assetsInfo.setTenorDayStart(0);
			assetsInfo.setTenorDayEnd(180);
		}
        if(tenorRoleType != null && tenorRoleType == 2){
        	assetsInfo.setTenorDayStart(181);
			assetsInfo.setTenorDayEnd(365);
		}
        if(tenorRoleType != null && tenorRoleType == 3){
        	assetsInfo.setTenorDayStart(366);
        	assetsInfo.setTenorDayEnd(1095);
        } 
        if(tenorRoleType != null && tenorRoleType == 4){
        	assetsInfo.setTenorDayStart(1096);
        }  
        if(scaleType == null || scaleType == 0){
			assetsInfo.setScaleType(null);
		}
        if(scaleType != null && scaleType == 1){
			assetsInfo.setScaleStart(new BigDecimal(0.00));
			assetsInfo.setScaleEnd(new BigDecimal(9999999));			
		}
        if(scaleType != null && scaleType == 2){
			assetsInfo.setScaleStart(new BigDecimal(10000000));
			assetsInfo.setScaleEnd(new BigDecimal(49999999));
		}
        if(scaleType != null && scaleType == 3){
			assetsInfo.setScaleStart(new BigDecimal(50000000));
			assetsInfo.setScaleEnd(new BigDecimal(99999999));
		}
        if(scaleType != null && scaleType == 4){
			assetsInfo.setScaleStart(new BigDecimal(100000000));
		}	
		PageInfo<Assets> assetsLis = assetsService.getAssets(pageNum,pageSize,assetsInfo);
		logger.info("输出：   "+JSON.toJSONString(assetsLis,SerializerFeature.WriteMapNullValue));
		return JSON.toJSONString(assetsLis,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 查询我的所有资产
	 * @param pageNum  当前页
	 * @param pageSize  每页记录数
	 * @return
	 */
	@RequestMapping(value="/getMyAssets",method=RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String getMyAssets(int pageNum,int pageSize, int orgId, String roleType){
		logger.info("输入：  "+ "pageNum="+pageNum+ "  pageSize="+pageSize+ "orgId="+orgId+"roleType="+roleType);
		PageInfo<Assets> assetsLis = assetsService.getMyAssets(pageNum,pageSize,orgId,roleType);
		logger.info("输出：   "+JSON.toJSONString(assetsLis,SerializerFeature.WriteMapNullValue));
		return JSON.toJSONString(assetsLis,SerializerFeature.WriteMapNullValue);
	}
	
	
	/**
	 * 资产录入
	 * @param assetsDetialInfo
	 * @return
	 
	@RequestMapping(value="/addAsset",method=RequestMethod.POST)
	@ResponseBody
	public String addAsset(AssetsDetialInfo assetsDetialInfo){
		int flag = assetsService.addAsset(assetsDetialInfo);
		return JSON.toJSONString(flag+"");
	}
	*/
	
	/**
	 * 私募ABS产品录入
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/addPeAbsAsset",method=RequestMethod.POST)
	@ResponseBody
	public String addPeAbsAsset(HttpServletRequest request,AssetsDetialInfo assetsDetialInfo) throws Exception{	
		logger.info("输入：  "+  "assetsDetialInfo="+JSON.toJSONString(assetsDetialInfo));
		TokenUtil.tokenOperate(request,userMapper);
		int flag;
		int falseflag;
		/*if(assetsDetialInfo.getAssetType() == null || assetsDetialInfo.getAssetType() != 4){
			return JSON.toJSONString(0);
		}*/
		if(assetsDetialInfo.getIssueDate() == null || "".equals(assetsDetialInfo.getIssueDate())){
			assetsDetialInfo.setIssueDate(null);
		}
		if(assetsDetialInfo.getAid() != null && !"".equals(assetsDetialInfo.getAid())){//修改
			flag = assetsService.editPeAbsAsset(assetsDetialInfo);
			if(flag == 1){
				return JSON.toJSONString(assetsDetialInfo.getAid());
			}else{
				falseflag = 0;
				return JSON.toJSONString(falseflag);
			}
		}else{//添加
			flag = assetsService.addPeAbsAsset(assetsDetialInfo);
			if(flag == 1){
				return JSON.toJSONString(assetsDetialInfo.getAid());
			}else{
				falseflag = 0;
				return JSON.toJSONString(falseflag);
			}
		}
	}
	
	
	
	/**
	 * 城投类录入
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/addStateInvestmentAsset",method=RequestMethod.POST)
	@ResponseBody
	public String addStateInvestmentAsset(HttpServletRequest request,AssetsDetialInfo assetsDetialInfo) throws Exception{
		logger.info("输入：  "+  "assetsDetialInfo="+JSON.toJSONString(assetsDetialInfo,SerializerFeature.WriteMapNullValue));
		TokenUtil.tokenOperate(request,userMapper);
		int flag;
		int falseflag;		
		/*if(assetsDetialInfo.getAssetType() == null || assetsDetialInfo.getAssetType() != 1){
			return JSON.toJSONString(0);
		}*/
		if(assetsDetialInfo.getCompleteDate() == null || "".equals(assetsDetialInfo.getCompleteDate())){
			assetsDetialInfo.setCompleteDate(null);
		}
		if(assetsDetialInfo.getAid() != null && !"".equals(assetsDetialInfo.getAid())){//修改
			flag = assetsService.editStateInvestmentAsset(assetsDetialInfo);
			if(flag == 1){
				return JSON.toJSONString(assetsDetialInfo.getAid());
			}else{
				falseflag = 0;
				return JSON.toJSONString(falseflag);
			}
		}else{//添加
			flag = assetsService.addStateInvestmentAsset(assetsDetialInfo);
			if(flag == 1){
				return JSON.toJSONString(assetsDetialInfo.getAid());
			}else{
				falseflag = 0;
				return JSON.toJSONString(falseflag);
			}
		}
	}
	
					
	/**
	 * 上市公司资产录入
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/addIpoAsset",method=RequestMethod.POST)
	@ResponseBody
	public String addIpoAsset(HttpServletRequest request,AssetsDetialInfo assetsDetialInfo) throws Exception{
		logger.info("输入：  "+  "assetsDetialInfo="+JSON.toJSONString(assetsDetialInfo));
		TokenUtil.tokenOperate(request,userMapper);
		int flag;
		int falseflag;
		/*if(assetsDetialInfo.getAssetType() == null || assetsDetialInfo.getAssetType() != 2){
			return JSON.toJSONString(0);
		}*/
		if(assetsDetialInfo.getAid() != null && !"".equals(assetsDetialInfo.getAid())){//修改
			flag = assetsService.editIpoAsset(assetsDetialInfo);
			if(flag == 1){
				return JSON.toJSONString(assetsDetialInfo.getAid());
			}else{
				falseflag = 0;
				return JSON.toJSONString(falseflag);
			}
		}else{//添加
			flag = assetsService.addIpoAsset(assetsDetialInfo);
			if(flag == 1){
				return JSON.toJSONString(assetsDetialInfo.getAid());
			}else{
				falseflag = 0;
				return JSON.toJSONString(falseflag);
			}
		}
		
	}
	
	/**
	 * 供应链资产录入
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/addSupplyChainAsset",method=RequestMethod.POST)
	@ResponseBody
	public String addSupplyChainAsset(HttpServletRequest request,AssetsDetialInfo assetsDetialInfo) throws Exception{
		logger.info("输入：  "+  "assetsDetialInfo="+JSON.toJSONString(assetsDetialInfo));
		TokenUtil.tokenOperate(request,userMapper);
		int flag;
		int falseflag;
		/*if(assetsDetialInfo.getAssetType() == null || assetsDetialInfo.getAssetType() != 3){
			return JSON.toJSONString(0);
		}*/
		if(assetsDetialInfo.getAid() != null && !"".equals(assetsDetialInfo.getAid())){//修改
			flag = assetsService.editSupplyChainAsset(assetsDetialInfo);
			if(flag == 1){
				return JSON.toJSONString(assetsDetialInfo.getAid());
			}else{
				falseflag = 0;
				return JSON.toJSONString(falseflag);
			}
		}else{//添加
			flag = assetsService.addSupplyChainAsset(assetsDetialInfo);
			if(flag == 1){
				return JSON.toJSONString(assetsDetialInfo.getAid());
			}else{
				falseflag = 0;
				return JSON.toJSONString(falseflag);
			}
		}
		
		
	}
	
	/**
	 * 收益权转让产品录入
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/addUsufructTransferAsset",method=RequestMethod.POST)
	@ResponseBody
	public String addUsufructTransferAsset(HttpServletRequest request,AssetsDetialInfo assetsDetialInfo) throws Exception{
		logger.info("输入：  "+  "assetsDetialInfo="+JSON.toJSONString(assetsDetialInfo));
		TokenUtil.tokenOperate(request,userMapper);
		int flag;
		int falseflag;
		/*if(assetsDetialInfo.getAssetType() == null || assetsDetialInfo.getAssetType() != 5){
			return JSON.toJSONString(0);
		}*/
		if(assetsDetialInfo.getAid() != null && !"".equals(assetsDetialInfo.getAid())){//修改
			flag = assetsService.editUsufructTransferAsset(assetsDetialInfo);
			if(flag == 1){
				return JSON.toJSONString(assetsDetialInfo.getAid());
			}else{
				falseflag = 0;
				return JSON.toJSONString(falseflag);
			}
		}else{//添加
			flag = assetsService.addUsufructTransferAsset(assetsDetialInfo);
			if(flag == 1){
				return JSON.toJSONString(assetsDetialInfo.getAid());
			}else{
				falseflag = 0;
				return JSON.toJSONString(falseflag);
			}
		}
	}
	
	/**
	 * 其他工商类资产录入
	 * @param request
	 * @param assetsDetialInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addIndustryCommunityAsset",method=RequestMethod.POST)
	@ResponseBody
	public String addIndustryCommunityAsset(HttpServletRequest request,AssetsDetialInfo assetsDetialInfo) throws Exception{
		logger.info("输入：  "+  "assetsDetialInfo="+JSON.toJSONString(assetsDetialInfo,SerializerFeature.WriteMapNullValue));
		TokenUtil.tokenOperate(request,userMapper);
		int flag;
		int falseflag;		
		if(assetsDetialInfo.getCompleteDate() == null || "".equals(assetsDetialInfo.getCompleteDate())){
			assetsDetialInfo.setCompleteDate(null);
		}		
		if("null".equals(assetsDetialInfo.getGuarantorBackground()) || "".equals(assetsDetialInfo.getGuarantorBackground())){
			assetsDetialInfo.setGuarantorBackground(null);
		}
		if(assetsDetialInfo.getAid() != null && !"".equals(assetsDetialInfo.getAid())){//修改
			flag = assetsService.editIndustryCommunityAsset(assetsDetialInfo);
			if(flag == 1){
				return JSON.toJSONString(assetsDetialInfo.getAid());
			}else{
				falseflag = 0;
				return JSON.toJSONString(falseflag);
			}
		}else{//添加
			flag = assetsService.addIndustryCommunityAsset(assetsDetialInfo);
			if(flag == 1){
				return JSON.toJSONString(assetsDetialInfo.getAid());
			}else{
				falseflag = 0;
				return JSON.toJSONString(falseflag);
			}
		}
	}
	
	
	/**
	 * 获取单个子资产信息
	 * @param assetId assetType
	 * @return
	 */
	@RequestMapping(value="/querySubAssetInfo",method=RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String querySubAssetInfo(int assetId,int assetType){
		logger.info("输入：  "+  "assetId="+assetId+"assetType="+assetType);
		AssetsDetialInfo subAssetsInfo = assetsService.getSubAssetInfoById(assetId,assetType);	
		logger.info("输出：   "+JSON.toJSONString(subAssetsInfo,SerializerFeature.WriteMapNullValue));
		return JSON.toJSONString(subAssetsInfo,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 资产立项
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/assetToProject",method=RequestMethod.POST)
	@ResponseBody
	public String  assetToProject(HttpServletRequest request,Assets assetProjectInfo) throws Exception{
		logger.info("输入：  "+  "assetProjectInfo="+JSON.toJSONString(assetProjectInfo));
		TokenUtil.tokenOperate(request,userMapper);
		assetProjectInfo.setWfStatus(1);
		assetProjectInfo.setStatus(3);
		int flag = assetsService.assetToProject(assetProjectInfo);
		return JSON.toJSONString(flag+"");
	}
	
	
	/**
	 * 备案过程
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/putOnRecord",method=RequestMethod.POST)
	@ResponseBody
	public String  putOnRecord(HttpServletRequest request,RecordLog recordLogInfo) throws Exception{
		logger.info("输入：  "+  "recordLogInfo="+JSON.toJSONString(recordLogInfo));
		TokenUtil.tokenOperate(request,userMapper);
		int flag = assetsService.putOnRecordStep(recordLogInfo);		
		return JSON.toJSONString(FormatReturn.format(flag));
	}
	
	
	/**
	 * 平台审核备案
	 * @param checkRecordInfo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/checkRecord",method=RequestMethod.POST)
	@ResponseBody
	public String  checkRecord(HttpServletRequest request,Assets checkRecordInfo) throws Exception{
		logger.info("输入：  "+  "checkRecordInfo="+JSON.toJSONString(checkRecordInfo));
		TokenUtil.tokenOperate(request,userMapper);
		int flag = assetsService.checkRecord(checkRecordInfo);
		return JSON.toJSONString(flag+"");
	}
	
	/**
	 * 资产excel导入
	 * 
	 */
	@RequestMapping(value="/assetsExeclImport",method=RequestMethod.POST)
	@ResponseBody
	public String assetsExeclImport(@RequestParam("excelFile") CommonsMultipartFile excelFile, HttpServletRequest request){
		String message = null;
		if (null == excelFile) {
			message = "导入的excel为空";
			return JSON.toJSONString(message+"");
		}
		List<AssetsDetialInfo> assetsDetialInfos = new ArrayList<AssetsDetialInfo>();

		new ExcelImportUtil2<AssetsDetialInfo>().parseExcel(excelFile, request, assetsDetialInfos, new ExcelToAssetsDetialInfoImpl());
		int flag = 0;
		for (AssetsDetialInfo assetsDetialInfo:assetsDetialInfos){
			//flag = assetsService.addAsset(assetsDetialInfo);
			if(flag==0){
				message = "导入有异常";
			}
		}		
		return JSON.toJSONString(message+"");

	}
	
	/**
     * 查询资产在某个步骤的协议文档
     * @param assetId  资产id
     * @param stepId  流程步骤id
     * @return
     */
	@RequestMapping(value="/queryRecordLogs",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String queryRecordLogs(@RequestParam("assetId") String assetId,@RequestParam("stepId") String stepId){
		logger.info("输入：  "+ "assetId="+assetId+ "  stepId="+stepId);
		RecordLog recordLogsList = assetsService.queryRecordLog(assetId, stepId);
		logger.info("输出：   "+JSON.toJSONString(recordLogsList));
		return JSON.toJSONString(recordLogsList,SerializerFeature.WriteMapNullValue);
	}

}
