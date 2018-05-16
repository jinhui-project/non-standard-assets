package com.jinhui.service.assets.impl;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.annotation.Resource;

import com.jinhui.mapper.abs.PeAccountsReceivableMapper;
import com.jinhui.mapper.abs.PeAssetChangeHistoryMapper;
import com.jinhui.mapper.abs.PeFundChangeHistoryMapper;
import com.jinhui.model.*;
import com.jinhui.model.abs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinhui.mapper.assets.AssetsMapper;
import com.jinhui.mapper.assets.IndustryCommunityAssetMapper;
import com.jinhui.mapper.assets.IpoAssetMapper;
import com.jinhui.mapper.assets.PeAbsAssetMapper;
import com.jinhui.mapper.assets.RecordLogMapper;
import com.jinhui.mapper.assets.StateInvestmentAssetMapper;
import com.jinhui.mapper.assets.SupplyChainAssetMapper;
import com.jinhui.mapper.assets.UsufructTransferAssetMapper;
import com.jinhui.service.assets.AssetsService;

@Service("assetsService")
public class AssetsServiceImpl implements AssetsService {
	private static Logger logger = LoggerFactory.getLogger(AssetsServiceImpl.class);

	@Resource
	private AssetsMapper assetsMapper;
	
	@Resource
	private IpoAssetMapper ipoAssetMapper;
	
	@Resource
	private PeAbsAssetMapper peAbsAssetMapper;
	
	@Resource
	private SupplyChainAssetMapper supplyChainAssetMapper;
	
	@Resource
	private StateInvestmentAssetMapper stateInvestmentAssetMapper;
	
	@Resource
	private UsufructTransferAssetMapper usufructTransferAssetMapper;
	
	@Resource
	private IndustryCommunityAssetMapper industryCommunityAssetMapper;
	

	@Resource
	private RecordLogMapper recordLogMapper;

	@Resource
	private PeAssetChangeHistoryMapper peAssetChangeHistoryMapper;

	@Resource
	private PeAccountsReceivableMapper peAccountsReceivableMapper;

	@Resource
	private PeFundChangeHistoryMapper peFundChangeHistoryMapper;
	
	
	/**
	 * 字符串转日期
	 * @param sdate
	 * @return
	 */
	public Date stringTranToDate(String sdate){
		if(sdate == null || "".equals(sdate)){
			return null;
		}else{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			Date date = null;
			try {
				date = sdf.parse(sdate);  
  
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}		
	}
	
	/**
	 * 日期转字符串
	 * @param date
	 * @return
	 */
	public String dateTranToString(Date date){
		if(date == null || "".equals(date)){
			return null;
		}else{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String str=sdf.format(date); 		
			return str;	
		}			
	}
	 
	/**
	 * 
	 * @param tenor
	 * @param tenorType
	 * @return
	 */
	public int getTenorDay(int tenor,int tenorType){
		int tenorDay = 0;
		if(tenorType==0){
			return 0;
		}
		if(tenorType==1){
			tenorDay = tenor * 1;
		}
        if(tenorType==2){
        	tenorDay = tenor * 30;
		}
        if(tenorType==3){
        	tenorDay = tenor * 365;
        }		
		return tenorDay;		
	}
	
	/**
	 * 资产立项
	 */
	@Override
	public int assetToProject(Assets asset){
		return assetsMapper.updateByPrimaryKeySelective(asset);				
	}
	
	/**
	 * 备案过程
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int putOnRecordStep(RecordLog recordLog){
		RecordLog record = recordLogMapper.queryRecordLog(recordLog.getAid()+"", recordLog.getStepId()+"");
		int flag = 0;
		if(record == null){  //如果不存在记录则新增
			flag = recordLogMapper.insert(recordLog);
		}else{   //如果存在记录则更新改记录
			record.setFileStr(recordLog.getFileStr());
			flag = recordLogMapper.updateByPrimaryKey(record);
		}
		/**
		 * 插入文件fileinfos表成功
		 * 更新备案状态
		 * 
		 */
		if(flag == 1 && "0".equals(recordLog.getFlag())){
			Assets asset = new Assets();
			asset.setId(recordLog.getAid());
			asset.setWfId(recordLog.getStepId());
			asset.setWfStatus(recordLog.getWfStatus());
			/*logger.info("wfStatus:  --------   "+ recordLog.getWfStatus());
			if((int)recordLog.getWfStatus() == 0){  //如果是备案最后一步“已过会”，则将资产状态改为“产品筹备”
				asset.setStatus(4);
			}*/
			asset.setMaterials(recordLog.getMaterials());
			asset.setDueReports(recordLog.getDueReports());
			asset.setPassResult(recordLog.getPassResult());
			asset.setPassContent(recordLog.getPassContent());
			flag = assetsMapper.updateByPrimaryKeySelective(asset);
		}		
		return flag;
	}
	
	
	
	/**
	 * 平台审核备案
	 */
	@Override
	public int checkRecord(Assets asset){
		return assetsMapper.updateByPrimaryKeySelective(asset);				
	}
	
	@Override
	public Assets getAssetsById(int assetId) {
		return assetsMapper.selectByPrimaryKey(assetId);
	}
	
	@Override
	public AssetsDetialInfo getSubAssetInfoById(int assetId,int assetType) {
		AssetsDetialInfo assetsDetialInfo = new AssetsDetialInfo();
		if(assetType == 1){
			StateInvestmentAsset stateInvestmentAsset = stateInvestmentAssetMapper.selectByAssetId(assetId);
			assetsDetialInfo = stateInvestmentAssetTrantoAssetsDetialInfo(stateInvestmentAsset);
		}
		if(assetType == 2){
			IpoAsset ipoAsset = ipoAssetMapper.selectByAssetId(assetId);
			assetsDetialInfo = ipoAssetTrantoAssetsDetialInfo(ipoAsset);
		}
		if(assetType == 3){
			SupplyChainAsset supplyChainAsset = supplyChainAssetMapper.selectByAssetId(assetId);
			assetsDetialInfo = supplyChainAssetTrantoAssetsDetialInfo(supplyChainAsset);
		}
		if(assetType == 4){
			PeAbsAsset peAbsAsset = peAbsAssetMapper.selectByAssetId(assetId);
			assetsDetialInfo = peAbsAssetTrantoAssetsDetialInfo(peAbsAsset);
		}
		if(assetType == 5){
			UsufructTransferAsset usufructTransferAsset = usufructTransferAssetMapper.selectByAssetId(assetId);
			assetsDetialInfo = usufructTransferAssetTrantoAssetsDetialInfo(usufructTransferAsset);
		}
		if(assetType == 6){
			IndustryCommunityAsset industryCommunityAsset = industryCommunityAssetMapper.selectByAssetId(assetId);
			assetsDetialInfo = industryCommunityAssetTrantoAssetsDetialInfo(industryCommunityAsset);
		}
		
		return assetsDetialInfo;
	}

	public PageInfo<Assets> getAllAssets(Integer pageNo, Integer pageSize) {		
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		
		List<Assets> list = assetsMapper.getAllAssets();
		return new PageInfo<Assets>(list);
	}
	
	
	public PageInfo<Assets> getAssets(Integer pageNo, Integer pageSize,Assets assets) {		
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		
		List<Assets> list = assetsMapper.getAssets(assets);
		return new PageInfo<Assets>(list);
	}

	public PageInfo<Assets> getMyAssets(Integer pageNum, Integer pageSize,Integer orgId,String roleType) {		
		    pageNum = pageNum == null ? 1 : pageNum;
			pageSize = pageSize == null ? 10 : pageSize;
			PageHelper.startPage(pageNum, pageSize);
			
			//List<Assets> list = assetsMapper.getMyAssets(assets);
			
			List<Assets> list = null;
			if("1".equals(roleType)){
				list = assetsMapper.getMyRecordAssetsList(orgId);   //查询我的资产-资产列表(备案机构方_我备案的)
			}else if("2".equals(roleType)){
				list = assetsMapper.getFeedBackRecordAssetsList(orgId);   //查询我的资产-资产列表(备案机构方_我反馈的)
			}else if("3".equals(roleType)){
				list = assetsMapper.getFeedBackFundsAssetsList(orgId);   //查询我的资产-资产列表(资金方_我反馈的)
			}else if("4".equals(roleType)){
				list = assetsMapper.getSaleFundsAssetsList(orgId);  //查询我的资产-资产列表(资金方_我销售的)
			}else if("5".equals(roleType)){
				//list = assetsMapper.getPropertyAssetsList(orgId);  //查询我的资产-资产列表(资产方)
				list = assetsMapper.getMyAssets(orgId);
			}else{
				list = assetsMapper.getMyAssets(orgId);
			}
			
			return new PageInfo<Assets>(list);
		}
	
	/**
	 * 
	 
	public int addAsset(AssetsDetialInfo assetsInfo) {
		Assets asset = assetsInfoInsertAsset(assetsInfo);
		int flagm = assetsMapper.insert(asset);
		int flags = 0;
		assetsInfo.setId(asset.getId());
		if(1==assetsInfo.getAssetType()){//城投类资产
			StateInvestmentAsset stateInvestmentAsset = assetsInfoInsertStateInvestmentAsset(assetsInfo);											
			flags = addStateInvestmentAsset(stateInvestmentAsset);
		}
		if(2==assetsInfo.getAssetType()){//上市公司资产
			IpoAsset ipoAsset = assetsInfoInsertIpoAsset(assetsInfo); 
			flags = addIpoAsset(ipoAsset);
		}		
		if(3==assetsInfo.getAssetType()){//供应链资产
			SupplyChainAsset supplyChainAsset = assetsInfoInsertSupplyChainAsset(assetsInfo);
			flags = addSupplyChainAsset(supplyChainAsset);
		}
		if(4==assetsInfo.getAssetType()){//ABS资产
			PeAbsAsset peAbsAsset = assetsInfoInsertPeAbsAsset(assetsInfo);
			flags = addPeAbsAsset(peAbsAsset);
		}
		if(5==assetsInfo.getAssetType()){//收益权转让资产
			UsufructTransferAsset usufructTransferAsset = assetsInfoInsertUsufructTransferAsset(assetsInfo);
			flags = addUsufructTransferAsset(usufructTransferAsset);
		}
		if(flagm == 1 && flags==1){
			return 1;
		}else{
			return 0;
		}
				
	}
     */
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int addIpoAsset(AssetsDetialInfo assetsDetialInfo) {
		IpoAsset ipoAsset = assetsInfoInsertIpoAsset(assetsDetialInfo);
		Assets asset = assetsInfoInsertAsset(assetsDetialInfo);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());// new Date()为获取当前系统时间
		asset.setCreateTime(createTime);
		asset.setEditTime(createTime);
		if(asset.getTenor() != null && asset.getTenorType() != null){
			int tenor = asset.getTenor();
			int tenorType = asset.getTenorType();
			asset.setTenorDay(getTenorDay(tenor,tenorType));
		}	
		int flagm = assetsMapper.insertSelective(asset);
		if(flagm != 1){
			return 0;
		}
		ipoAsset.setAid(asset.getId());
		assetsDetialInfo.setAid(asset.getId());
		return ipoAssetMapper.insertSelective(ipoAsset);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int editIpoAsset(AssetsDetialInfo assetsDetialInfo) {
		IpoAsset ipoAsset = assetsInfoInsertIpoAsset(assetsDetialInfo);
		Assets asset = assetsInfoInsertAsset(assetsDetialInfo);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String editTime = df.format(new Date());// new Date()为获取当前系统时间
		asset.setEditTime(editTime);
		if(asset.getTenor() != null && asset.getTenorType() != null){
			int tenor = asset.getTenor();
			int tenorType = asset.getTenorType();
			asset.setTenorDay(getTenorDay(tenor,tenorType));
		}	
		asset.setId(assetsDetialInfo.getAid());
		int flagm = assetsMapper.updateByPrimaryKeySelective(asset);
		if(flagm != 1){
			return 0;
		}
		assetsDetialInfo.setAid(asset.getId());	
		return ipoAssetMapper.updateByAssetsidSelective(ipoAsset);
	}
	

	@Transactional(propagation=Propagation.REQUIRED)
	public int addPeAbsAsset(AssetsDetialInfo assetsDetialInfo) {
		PeAbsAsset peAbsAsset = assetsInfoInsertPeAbsAsset(assetsDetialInfo);
		Assets asset = assetsInfoInsertAsset(assetsDetialInfo);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());// new Date()为获取当前系统时间
		asset.setCreateTime(createTime);
		asset.setEditTime(createTime);
		if(asset.getTenor() != null && asset.getTenorType() != null){
			int tenor = asset.getTenor();
			int tenorType = asset.getTenorType();
			asset.setTenorDay(getTenorDay(tenor,tenorType));
		}	
		int flagm = assetsMapper.insertSelective(asset);
		if(flagm != 1){
			return 0;
		}
		peAbsAsset.setAid(asset.getId());
		assetsDetialInfo.setAid(asset.getId());
		return peAbsAssetMapper.insertSelective(peAbsAsset);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int editPeAbsAsset(AssetsDetialInfo assetsDetialInfo) {
		PeAbsAsset peAbsAsset = assetsInfoInsertPeAbsAsset(assetsDetialInfo);
		Assets asset = assetsInfoInsertAsset(assetsDetialInfo);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String editTime = df.format(new Date());// new Date()为获取当前系统时间
		asset.setEditTime(editTime);
		if(asset.getTenor() != null && asset.getTenorType() != null){
			int tenor = asset.getTenor();
			int tenorType = asset.getTenorType();
			asset.setTenorDay(getTenorDay(tenor,tenorType));
		}	
		asset.setId(peAbsAsset.getAid());
		int flagm = assetsMapper.updateByPrimaryKeySelective(asset);
		if(flagm != 1){
			return 0;
		}
		assetsDetialInfo.setAid(asset.getId());
		return peAbsAssetMapper.updateByAssetsidSelective(peAbsAsset);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int addStateInvestmentAsset(AssetsDetialInfo assetsDetialInfo) {
		StateInvestmentAsset stateInvestmentAsset = assetsInfoInsertStateInvestmentAsset(assetsDetialInfo);
		Assets asset = assetsInfoInsertAsset(assetsDetialInfo);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());// new Date()为获取当前系统时间
		asset.setCreateTime(createTime);
		asset.setEditTime(createTime);
		if(asset.getTenor() != null && asset.getTenorType() != null){
			int tenor = asset.getTenor();
			int tenorType = asset.getTenorType();
			asset.setTenorDay(getTenorDay(tenor,tenorType));
		}		
		int flagm = assetsMapper.insertSelective(asset);
		if(flagm != 1){
			return 0;
		}
		stateInvestmentAsset.setAid(asset.getId());
		assetsDetialInfo.setAid(asset.getId());
		return stateInvestmentAssetMapper.insertSelective(stateInvestmentAsset);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int editStateInvestmentAsset(AssetsDetialInfo assetsDetialInfo) {		
		StateInvestmentAsset stateInvestmentAsset = assetsInfoInsertStateInvestmentAsset(assetsDetialInfo);
		Assets asset = assetsInfoInsertAsset(assetsDetialInfo);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String editTime = df.format(new Date());// new Date()为获取当前系统时间
		asset.setEditTime(editTime);
		if(asset.getTenor() != null && asset.getTenorType() != null){
			int tenor = asset.getTenor();
			int tenorType = asset.getTenorType();
			asset.setTenorDay(getTenorDay(tenor,tenorType));
		}	
		asset.setId(stateInvestmentAsset.getAid());
		int flagm = assetsMapper.updateByPrimaryKeySelective(asset);
		if(flagm != 1){
			return 0;
		}
		assetsDetialInfo.setAid(asset.getId());
		return stateInvestmentAssetMapper.updateByAssetsidSelective(stateInvestmentAsset);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int addSupplyChainAsset(AssetsDetialInfo assetsDetialInfo) {
		SupplyChainAsset supplyChainAsset = assetsInfoInsertSupplyChainAsset(assetsDetialInfo);
		Assets asset = assetsInfoInsertAsset(assetsDetialInfo);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());// new Date()为获取当前系统时间
		asset.setCreateTime(createTime);
		asset.setEditTime(createTime);
		if(asset.getTenor() != null && asset.getTenorType() != null){
			int tenor = asset.getTenor();
			int tenorType = asset.getTenorType();
			asset.setTenorDay(getTenorDay(tenor,tenorType));
		}	
		int flagm = assetsMapper.insertSelective(asset);
		if(flagm != 1){
			return 0;
		}
		supplyChainAsset.setAid(asset.getId());
		assetsDetialInfo.setAid(asset.getId());
		return supplyChainAssetMapper.insertSelective(supplyChainAsset);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int editSupplyChainAsset(AssetsDetialInfo assetsDetialInfo) {
		SupplyChainAsset supplyChainAsset = assetsInfoInsertSupplyChainAsset(assetsDetialInfo);
		Assets asset = assetsInfoInsertAsset(assetsDetialInfo);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String editTime = df.format(new Date());// new Date()为获取当前系统时间
		asset.setEditTime(editTime);
		if(asset.getTenor() != null && asset.getTenorType() != null){
			int tenor = asset.getTenor();
			int tenorType = asset.getTenorType();
			asset.setTenorDay(getTenorDay(tenor,tenorType));
		}	
		asset.setId(supplyChainAsset.getAid());
		int flagm = assetsMapper.updateByPrimaryKeySelective(asset);
		if(flagm != 1){
			return 0;
		}
		assetsDetialInfo.setAid(asset.getId());
		return supplyChainAssetMapper.updateByAssetsidSelective(supplyChainAsset);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int addUsufructTransferAsset(AssetsDetialInfo assetsDetialInfo) {
		UsufructTransferAsset usufructTransferAsset = assetsInfoInsertUsufructTransferAsset(assetsDetialInfo);
		Assets asset = assetsInfoInsertAsset(assetsDetialInfo);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());// new Date()为获取当前系统时间
		asset.setCreateTime(createTime);
		asset.setEditTime(createTime);
		if(asset.getTenor() != null && asset.getTenorType() != null){
			int tenor = asset.getTenor();
			int tenorType = asset.getTenorType();
			asset.setTenorDay(getTenorDay(tenor,tenorType));
		}	
		int flagm = assetsMapper.insertSelective(asset);
		if(flagm != 1){
			return 0;
		}
		usufructTransferAsset.setAid(asset.getId());
		assetsDetialInfo.setAid(asset.getId());
		return usufructTransferAssetMapper.insertSelective(usufructTransferAsset);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int editUsufructTransferAsset(AssetsDetialInfo assetsDetialInfo) {
		UsufructTransferAsset usufructTransferAsset = assetsInfoInsertUsufructTransferAsset(assetsDetialInfo);
		Assets asset = assetsInfoInsertAsset(assetsDetialInfo);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());// new Date()为获取当前系统时间
		asset.setCreateTime(createTime);
		asset.setEditTime(createTime);
		if(asset.getTenor() != null && asset.getTenorType() != null){
			int tenor = asset.getTenor();
			int tenorType = asset.getTenorType();
			asset.setTenorDay(getTenorDay(tenor,tenorType));
		}	
		asset.setId(assetsDetialInfo.getAid());
		int flagm = assetsMapper.updateByPrimaryKeySelective(asset);
		if(flagm != 1){
			return 0;
		}
		assetsDetialInfo.setAid(asset.getId());
		return usufructTransferAssetMapper.updateByAssetsidSelective(usufructTransferAsset);
	}
	
	public StateInvestmentAsset assetsInfoInsertStateInvestmentAsset(AssetsDetialInfo assetsInfo){
		StateInvestmentAsset stateInvestmentAsset = new StateInvestmentAsset();
		stateInvestmentAsset.setAid(assetsInfo.getAid());
		stateInvestmentAsset.setDataExpierationDate(assetsInfo.getDataExpierationDate());
		stateInvestmentAsset.setAssetType(assetsInfo.getAssetType());
		stateInvestmentAsset.setBorrower(assetsInfo.getBorrower());
		stateInvestmentAsset.setScale(assetsInfo.getScale());
		stateInvestmentAsset.setTenorType(assetsInfo.getTenorType());
		stateInvestmentAsset.setTenor(assetsInfo.getTenor());
		stateInvestmentAsset.setRepaymentType(assetsInfo.getRepaymentType());
		stateInvestmentAsset.setCompleteDate(assetsInfo.getCompleteDate());
		stateInvestmentAsset.setFinancingCost(assetsInfo.getFinancingCost());
		stateInvestmentAsset.setFinancingType(assetsInfo.getFinancingType());
		stateInvestmentAsset.setFinancingPurpose(assetsInfo.getFinancingPurpose());
		stateInvestmentAsset.setTimingRequirement(assetsInfo.getTimingRequirement());
		stateInvestmentAsset.setCompanyName(assetsInfo.getCompanyName());
		stateInvestmentAsset.setHolderBackground(assetsInfo.getHolderBackground());
		stateInvestmentAsset.setCapital(assetsInfo.getCapital());
		stateInvestmentAsset.setFoundedTime(stringTranToDate(assetsInfo.getFoundedTime()));
		stateInvestmentAsset.setMajorBusiness(assetsInfo.getMajorBusiness());
		stateInvestmentAsset.setTotalAsset(assetsInfo.getTotalAsset());
		stateInvestmentAsset.setNetAsset(assetsInfo.getNetAsset());
		stateInvestmentAsset.setMainIncome(assetsInfo.getMainIncome());
		stateInvestmentAsset.setAllowanceIncome(assetsInfo.getAllowanceIncome());
		stateInvestmentAsset.setNetProfit(assetsInfo.getNetProfit());
		stateInvestmentAsset.setAlRatio(assetsInfo.getAlRatio());
		stateInvestmentAsset.setRating(assetsInfo.getRating());
		stateInvestmentAsset.setForecast(assetsInfo.getForecast());
		stateInvestmentAsset.setPublicDebt(assetsInfo.getPublicDebt());
		stateInvestmentAsset.setMatureDate(stringTranToDate(assetsInfo.getMatureDate()));
		stateInvestmentAsset.setBankLine(assetsInfo.getBankLine());
		stateInvestmentAsset.setBadSuit(assetsInfo.getBadSuit());
		stateInvestmentAsset.setLawSuit(assetsInfo.getLawSuit());
		stateInvestmentAsset.setFinanceCooporation(assetsInfo.getFinanceCooporation());
		stateInvestmentAsset.setProjectType(assetsInfo.getProjectType());	
		stateInvestmentAsset.setProjectCompliance(assetsInfo.getProjectCompliance());	
		stateInvestmentAsset.setCompanyName(assetsInfo.getCompanyName());	
		stateInvestmentAsset.setSelfFunded(assetsInfo.getSelfFunded());	
		stateInvestmentAsset.setRepayArrangement(assetsInfo.getRepayArrangement());	
		stateInvestmentAsset.setOtherFinancing(assetsInfo.getOtherFinancing());	
		stateInvestmentAsset.setLandValue(assetsInfo.getLandValue());	
		stateInvestmentAsset.setProjectValue(assetsInfo.getProjectValue());	
		stateInvestmentAsset.setOtherCollateral(assetsInfo.getOtherCollateral());	
		stateInvestmentAsset.setReceivable(assetsInfo.getReceivable());	
		stateInvestmentAsset.setCounterParty(assetsInfo.getCounterParty());	
		stateInvestmentAsset.setRepayDate(stringTranToDate(assetsInfo.getRepayDate()));	
		stateInvestmentAsset.setPledgeStock(assetsInfo.getPledgeStock());	
		stateInvestmentAsset.setPledgeRatio(assetsInfo.getPledgeRatio());	
		stateInvestmentAsset.setOtherPledge(assetsInfo.getOtherPledge());	
		stateInvestmentAsset.setGuarantor(assetsInfo.getGuarantor());	
		stateInvestmentAsset.setGuarantorBackground(assetsInfo.getGuarantorBackground());	
		stateInvestmentAsset.setGuarantorRating(assetsInfo.getGuarantorRating());	
		stateInvestmentAsset.setGovDecision(assetsInfo.getGovDecision());	
		stateInvestmentAsset.setGovAgreement(assetsInfo.getGovAgreement());	
		stateInvestmentAsset.setFinanceArrangement(assetsInfo.getFinanceArrangement());	
		stateInvestmentAsset.setProvince(assetsInfo.getProvince());
		stateInvestmentAsset.setCity(assetsInfo.getCity());
		stateInvestmentAsset.setAdmGrade(assetsInfo.getAdmGrade());	
		stateInvestmentAsset.setGdp(assetsInfo.getGdp());	
		stateInvestmentAsset.setMainIndustry(assetsInfo.getMainIndustry());	
		stateInvestmentAsset.setBigThreeRatio(assetsInfo.getBigThreeRatio());	
		stateInvestmentAsset.setPopulation(assetsInfo.getPopulation());	
		stateInvestmentAsset.setGovTotalIncome(assetsInfo.getGovTotalIncome());	
		stateInvestmentAsset.setGeneralIncome(assetsInfo.getGeneralIncome());	
		stateInvestmentAsset.setTransferIncome(assetsInfo.getTransferIncome());	
		stateInvestmentAsset.setGovFundIncome(assetsInfo.getGovFundIncome());	
		stateInvestmentAsset.setGovDebt(assetsInfo.getGovDebt());	
		stateInvestmentAsset.setGovGuarantee(assetsInfo.getGovGuarantee());	
		stateInvestmentAsset.setLiabilitiesRatio(assetsInfo.getLiabilitiesRatio());	
		stateInvestmentAsset.setDebtRation(assetsInfo.getDebtRation());	
		stateInvestmentAsset.setLisence(assetsInfo.getLisence());	
		stateInvestmentAsset.setOrgInstCode(assetsInfo.getOrgInstCode());	
		stateInvestmentAsset.setTaxReg(assetsInfo.getTaxReg());	
		stateInvestmentAsset.setLeId(assetsInfo.getLeId());	
		stateInvestmentAsset.setBankCard(assetsInfo.getBankCard());	
		stateInvestmentAsset.setAcApproval(assetsInfo.getAcApproval());	
		stateInvestmentAsset.setArticle(assetsInfo.getArticle());	
		stateInvestmentAsset.setCaptialVer(assetsInfo.getCaptialVer());	
		stateInvestmentAsset.setCreditReport(assetsInfo.getCreditReport());	
		stateInvestmentAsset.setFeasibility(assetsInfo.getFeasibility());	
		stateInvestmentAsset.setAuditReport(assetsInfo.getAuditReport());	
		stateInvestmentAsset.setFinanceStatement(assetsInfo.getFinanceStatement());	
		stateInvestmentAsset.setFinanceDvalid(stringTranToDate(assetsInfo.getFinanceDvalid()));
		stateInvestmentAsset.setFinancialDvalid(stringTranToDate(assetsInfo.getFinancialDvalid()));
		stateInvestmentAsset.setOtherFile(assetsInfo.getOtherFile());
		
		stateInvestmentAsset.setIsGuarante(assetsInfo.getIsGuarante());
		stateInvestmentAsset.setGuarantorType(assetsInfo.getGuarantorType());
		return stateInvestmentAsset;		
	}
	
	public Assets assetsInfoInsertAsset(AssetsDetialInfo assetsInfo){		
		Assets asset = new Assets();
		asset.setName(assetsInfo.getName());
		asset.setShortName(assetsInfo.getShortName());
		asset.setSalesModel(assetsInfo.getSalesModel());
		asset.setAssetType(assetsInfo.getAssetType());
		asset.setBorrower(assetsInfo.getBorrower());
		asset.setTenorType(assetsInfo.getTenorType());
		asset.setTenor(assetsInfo.getTenor());
		asset.setScale(assetsInfo.getScale());
		asset.setFinancingCost(assetsInfo.getFinancingCost());
		asset.setRecordGid(assetsInfo.getRecordGid());
		asset.setWfType(assetsInfo.getWfType());
		asset.setWfId(assetsInfo.getWfId());
		asset.setWfStatus(assetsInfo.getWfStatus());
		asset.setMaterials(assetsInfo.getMaterials());
		asset.setDueReports(assetsInfo.getDueReports());
		asset.setPassResult(assetsInfo.getPassResult());
		asset.setPassContent(assetsInfo.getPassContent());
		asset.setBelongGid(assetsInfo.getBelongGid());
		asset.setCreatorId(assetsInfo.getCreatorId());
		asset.setCreateTime(assetsInfo.getCreateTime());
		asset.setEditorId(assetsInfo.getEditorId());
		asset.setEditTime(assetsInfo.getEditTime());
		asset.setAuditorId(assetsInfo.getAuditorId());
		asset.setAuditTime(assetsInfo.getAuditTime());
		asset.setPublisherId(assetsInfo.getPublisherId());
		asset.setPublishTime(assetsInfo.getPublishTime());
		asset.setConfirmerId(assetsInfo.getConfirmerId());
		asset.setConfirmTime(assetsInfo.getConfirmTime());
		asset.setBrokerId(assetsInfo.getBrokerId());
		asset.setBrokerTime(assetsInfo.getBrokerTime());
		asset.setRefereeId(assetsInfo.getRefereeId());
		asset.setRefereeTime(assetsInfo.getRefereeTime());
		asset.setStatus(assetsInfo.getStatus());
		return asset;		
	}
	
	
	
	public PeAbsAsset assetsInfoInsertPeAbsAsset(AssetsDetialInfo assetsInfo){		
		PeAbsAsset peAbsAsset = new PeAbsAsset();
		peAbsAsset.setAid(assetsInfo.getAid());
		peAbsAsset.setAssetType(assetsInfo.getAssetType());
		peAbsAsset.setBorrower(assetsInfo.getBorrower());
		peAbsAsset.setScale(assetsInfo.getScale());
		peAbsAsset.setTenor(assetsInfo.getTenor());
		peAbsAsset.setTenorType(assetsInfo.getTenorType());
		peAbsAsset.setAssetMgr(assetsInfo.getAssetMgr());
		peAbsAsset.setSetTrench(assetsInfo.getSetTrench());
		peAbsAsset.setSeniorPercent(assetsInfo.getSeniorPercent());
		peAbsAsset.setSeniorRating(assetsInfo.getSeniorRating());
		peAbsAsset.setAssetSubType(assetsInfo.getAssetSubType());
		peAbsAsset.setRate(assetsInfo.getRate());
		peAbsAsset.setAbsRatingReport(assetsInfo.getAbsRatingReport());	
		peAbsAsset.setOtherFile(assetsInfo.getOtherFile());
		peAbsAsset.setIssueDate(assetsInfo.getIssueDate());
		
		peAbsAsset.setIsGuarante(assetsInfo.getIsGuarante());
		peAbsAsset.setGuarantorType(assetsInfo.getGuarantorType());
		return peAbsAsset;		
	}
	
	
	public UsufructTransferAsset assetsInfoInsertUsufructTransferAsset(AssetsDetialInfo assetsInfo){
		UsufructTransferAsset usufructTransferAsset = new UsufructTransferAsset();
		usufructTransferAsset.setAid(assetsInfo.getAid());
		usufructTransferAsset.setAssetType(assetsInfo.getAssetType());
		usufructTransferAsset.setProjectName(assetsInfo.getProjectName());
		usufructTransferAsset.setProjectType(assetsInfo.getProjectType());
		usufructTransferAsset.setBorrower(assetsInfo.getBorrower());
		usufructTransferAsset.setScale(assetsInfo.getScale());
		usufructTransferAsset.setTenorType(assetsInfo.getTenorType());
		usufructTransferAsset.setTenor(assetsInfo.getTenor());
		usufructTransferAsset.setAssetMgr(assetsInfo.getAssetMgr());
		usufructTransferAsset.setRepurchase(assetsInfo.getRepurchase());
		usufructTransferAsset.setTradingStructure(assetsInfo.getTradingStructure());
		usufructTransferAsset.setAssetSubType(assetsInfo.getAssetSubType());
		usufructTransferAsset.setRate(assetsInfo.getRate());
		usufructTransferAsset.setRelatedAgreement(assetsInfo.getRelatedAgreement());
		usufructTransferAsset.setFinancingCost(assetsInfo.getFinancingCost());
		usufructTransferAsset.setOtherFile(assetsInfo.getOtherFile());
		
		usufructTransferAsset.setIsGuarante(assetsInfo.getIsGuarante());
		usufructTransferAsset.setGuarantorType(assetsInfo.getGuarantorType());
		return usufructTransferAsset;			
	}
	
	
	public IpoAsset assetsInfoInsertIpoAsset(AssetsDetialInfo assetsInfo){
		IpoAsset ipoAsset = new IpoAsset();
		ipoAsset.setAid(assetsInfo.getAid());
		ipoAsset.setDataExpierationDate(assetsInfo.getDataExpierationDate());
		ipoAsset.setAssetType(assetsInfo.getAssetType());
		ipoAsset.setBorrower(assetsInfo.getBorrower());
		ipoAsset.setScale(assetsInfo.getScale());
		ipoAsset.setTenor(assetsInfo.getTenor());
		ipoAsset.setTenorType(assetsInfo.getTenorType());
		ipoAsset.setRepaymentType(assetsInfo.getRepaymentType());
		ipoAsset.setFinancingCost(assetsInfo.getFinancingCost());
		ipoAsset.setFinancingType(assetsInfo.getFinancingType());
		ipoAsset.setFinancingPurpose(assetsInfo.getFinancingPurpose());
		ipoAsset.setTimingRequirement(assetsInfo.getTimingRequirement());
		ipoAsset.setIpoRelation(assetsInfo.getIpoRelation());
		ipoAsset.setListedCode(assetsInfo.getListedCode());
		ipoAsset.setHolderBackground(assetsInfo.getHolderBackground());
		ipoAsset.setCapital(assetsInfo.getCapital());
		ipoAsset.setFoundedTime(stringTranToDate(assetsInfo.getFoundedTime()));
		ipoAsset.setIndustry(assetsInfo.getIndustry());
		ipoAsset.setLocatedProvince(assetsInfo.getLocatedProvince());
		ipoAsset.setLocatedCity(assetsInfo.getLocatedCity());
		ipoAsset.setMajorBusiness(assetsInfo.getMajorBusiness());
		ipoAsset.setBusinessDesc(assetsInfo.getBusinessDesc());
		ipoAsset.setTotalAsset(assetsInfo.getTotalAsset());
		ipoAsset.setNetAsset(assetsInfo.getNetAsset());
		ipoAsset.setMainIncome(assetsInfo.getMainIncome());
		ipoAsset.setNetProfit(assetsInfo.getNetProfit());
		ipoAsset.setGrossProfitRatio(assetsInfo.getGrossProfitRatio());
		ipoAsset.setRating(assetsInfo.getRating());
		ipoAsset.setForecast(assetsInfo.getForecast());
		ipoAsset.setPublicDebt(assetsInfo.getPublicDebt());
		ipoAsset.setMatureDate(stringTranToDate(assetsInfo.getMatureDate()));
		ipoAsset.setBankLine(assetsInfo.getBankLine());
		ipoAsset.setBadSuit(assetsInfo.getBadSuit());
		ipoAsset.setLawSuit(assetsInfo.getLawSuit());
		ipoAsset.setFinanceCooporation(assetsInfo.getFinanceCooporation());
		ipoAsset.setLandValue(assetsInfo.getLandValue());
		ipoAsset.setOtherCollateral(assetsInfo.getOtherCollateral());
		ipoAsset.setReceivable(assetsInfo.getReceivable());
		ipoAsset.setCounterParty(assetsInfo.getCounterParty());
		ipoAsset.setRepayDate(stringTranToDate(assetsInfo.getRepayDate()));
		ipoAsset.setPledgeStock(assetsInfo.getPledgeStock());
        ipoAsset.setPledgeRatio(assetsInfo.getPledgeRatio());
        ipoAsset.setGuarantor(assetsInfo.getGuarantor());
        ipoAsset.setGuarantorBackground(assetsInfo.getGuarantorBackground());
        ipoAsset.setGuarantorRating(assetsInfo.getGuarantorRating());
        ipoAsset.setLisence(assetsInfo.getLisence());
        ipoAsset.setOrgInstCode(assetsInfo.getOrgInstCode());
        ipoAsset.setTaxReg(assetsInfo.getTaxReg());
        ipoAsset.setLeId(assetsInfo.getLeId());
        ipoAsset.setBankCard(assetsInfo.getBankCard());
        ipoAsset.setAcApproval(assetsInfo.getAcApproval());
        ipoAsset.setArticle(assetsInfo.getArticle());
        ipoAsset.setCaptialVer(assetsInfo.getCaptialVer());
        ipoAsset.setCreditReport(assetsInfo.getCreditReport());
        ipoAsset.setFeasibility(assetsInfo.getFeasibility());
        ipoAsset.setAuditReport(assetsInfo.getAuditReport());
        ipoAsset.setFinanceStatement(assetsInfo.getFinanceStatement());
        ipoAsset.setFinanceDvalid(stringTranToDate(assetsInfo.getFinanceDvalid()));
        ipoAsset.setOtherPledge(assetsInfo.getOtherPledge());
        ipoAsset.setOtherFile(assetsInfo.getOtherFile());
        ipoAsset.setProjectValue(assetsInfo.getProjectValue());
        
        ipoAsset.setIsTrust(assetsInfo.getIsTrust());
        ipoAsset.setIsGuarante(assetsInfo.getIsGuarante());
        ipoAsset.setGuarantorType(assetsInfo.getGuarantorType());
        ipoAsset.setGuarantorFinancierRelation(assetsInfo.getGuarantorFinancierRelation());
        ipoAsset.setGuarantorRegistMoney(assetsInfo.getGuarantorRegistMoney());
        ipoAsset.setGuarantorTotalMoney(assetsInfo.getGuarantorTotalMoney());
        ipoAsset.setGuarantorNetMoney(assetsInfo.getGuarantorNetMoney());
        ipoAsset.setGuarantorNetProfit(assetsInfo.getGuarantorNetProfit());
        ipoAsset.setGuarantorRevenue(assetsInfo.getGuarantorRevenue());
        ipoAsset.setGuarantorGrossRate(assetsInfo.getGuarantorGrossRate());
        ipoAsset.setGuarantorSetupDate(stringTranToDate(assetsInfo.getGuarantorSetupDate()));
        ipoAsset.setGuarantorDataExpired(stringTranToDate(assetsInfo.getGuarantorDataExpired()));
        ipoAsset.setGuarantorRatingOutlook(assetsInfo.getGuarantorRatingOutlook());
        ipoAsset.setGuarantorIsPublish(assetsInfo.getGuarantorIsPublish());
        ipoAsset.setGuarantorLastestDate(stringTranToDate(assetsInfo.getGuarantorLastestDate()));
        ipoAsset.setGuarantorBadBreach(assetsInfo.getGuarantorBadBreach());
        ipoAsset.setGuarantorPendingAction(assetsInfo.getGuarantorPendingAction());
        
		return ipoAsset;		
	}
	
	
	public SupplyChainAsset assetsInfoInsertSupplyChainAsset(AssetsDetialInfo assetsInfo){
		SupplyChainAsset supplyChainAsset = new SupplyChainAsset();
		supplyChainAsset.setAid(assetsInfo.getAid());
		supplyChainAsset.setDataExpierationDate(assetsInfo.getDataExpierationDate());
		supplyChainAsset.setAssetType(assetsInfo.getAssetType());
		supplyChainAsset.setBorrower(assetsInfo.getBorrower());
		supplyChainAsset.setScale(assetsInfo.getScale());
		supplyChainAsset.setTenorType(assetsInfo.getTenorType());
		supplyChainAsset.setTenor(assetsInfo.getTenor());
		supplyChainAsset.setCoreEnterprise(assetsInfo.getCoreEnterprise());
		supplyChainAsset.setIndustryChain(assetsInfo.getIndustryChain());
		supplyChainAsset.setIndustry(assetsInfo.getIndustry());
		supplyChainAsset.setFinancingCost(assetsInfo.getFinancingCost());
		supplyChainAsset.setFinancingType(assetsInfo.getFinancingType());
		supplyChainAsset.setMajorBusiness(assetsInfo.getMajorBusiness());
		supplyChainAsset.setTradeType(assetsInfo.getTradeType());
		supplyChainAsset.setRepaymentPeriod(assetsInfo.getRepaymentPeriod());
		supplyChainAsset.setAvgArAge(assetsInfo.getAvgArAge());
		supplyChainAsset.setTotalAsset(assetsInfo.getTotalAsset());
		supplyChainAsset.setTotalDebt(assetsInfo.getTotalDebt());
		supplyChainAsset.setNetAsset(assetsInfo.getNetAsset());
		supplyChainAsset.setInventory(assetsInfo.getInventory());
		supplyChainAsset.setAcReceiveable(assetsInfo.getAcReceiveable());
		supplyChainAsset.setPrepayment(assetsInfo.getPrepayment());
		supplyChainAsset.setMainIncome(assetsInfo.getMainIncome());
		supplyChainAsset.setArRotationRatio(assetsInfo.getArRotationRatio());
		supplyChainAsset.setInventoryRation(assetsInfo.getInventoryRation());
		supplyChainAsset.setNetProfit(assetsInfo.getNetProfit());
		supplyChainAsset.setGrossProfitRatio(assetsInfo.getGrossProfitRatio());
		supplyChainAsset.setTrasactionHistory(assetsInfo.getTrasactionHistory());
		supplyChainAsset.setSettlementType(assetsInfo.getSettlementType());
		supplyChainAsset.setLogisticType(assetsInfo.getLogisticType());
		supplyChainAsset.setLisenceNeeded(assetsInfo.getLisenceNeeded());
		supplyChainAsset.setLineGrandingPolicy(assetsInfo.getLineGrandingPolicy());
		supplyChainAsset.setRating(assetsInfo.getRating());
		supplyChainAsset.setCreditEnforce(assetsInfo.getCreditEnforce());
		supplyChainAsset.setLisence(assetsInfo.getLisence());
		supplyChainAsset.setOrgInstCode(assetsInfo.getOrgInstCode());
		supplyChainAsset.setTaxReg(assetsInfo.getTaxReg());
		supplyChainAsset.setArticle(assetsInfo.getArticle());
		supplyChainAsset.setCaptialVer(assetsInfo.getCaptialVer());
		supplyChainAsset.setFeasibility(assetsInfo.getFeasibility());
		supplyChainAsset.setAuditReport(assetsInfo.getAuditReport());
		supplyChainAsset.setYearTransaction(assetsInfo.getYearTransaction());
		supplyChainAsset.setFinanceDvalid(stringTranToDate(assetsInfo.getFinanceDvalid()));
		supplyChainAsset.setOtherFile(assetsInfo.getOtherFile());
		supplyChainAsset.setFinanceStatement(assetsInfo.getFinanceStatement());
		
		supplyChainAsset.setIsGuarante(assetsInfo.getIsGuarante());
		supplyChainAsset.setGuarantorType(assetsInfo.getGuarantorType());
		return supplyChainAsset;		
	}
	
	public AssetsDetialInfo stateInvestmentAssetTrantoAssetsDetialInfo(StateInvestmentAsset stateInvestmentAsset){
		AssetsDetialInfo assetsDetialInfo = new AssetsDetialInfo();
		assetsDetialInfo.setId(stateInvestmentAsset.getAid());
		assetsDetialInfo.setBelongGid(stateInvestmentAsset.getBelongGid());
		assetsDetialInfo.setName(stateInvestmentAsset.getName());
		assetsDetialInfo.setShortName(stateInvestmentAsset.getShortName());
		assetsDetialInfo.setCreatorId(stateInvestmentAsset.getCreatorId());
		assetsDetialInfo.setEditorId(stateInvestmentAsset.getEditorId());
		assetsDetialInfo.setCreateTime(stateInvestmentAsset.getCreateTime());
		assetsDetialInfo.setEditTime(stateInvestmentAsset.getEditTime());
		assetsDetialInfo.setAssetType(stateInvestmentAsset.getAssetType());
		assetsDetialInfo.setBorrower(stateInvestmentAsset.getBorrower());
		assetsDetialInfo.setScale(stateInvestmentAsset.getScale());
		assetsDetialInfo.setCompleteDate(stateInvestmentAsset.getCompleteDate());
		assetsDetialInfo.setTenorType(stateInvestmentAsset.getTenorType());
		assetsDetialInfo.setTenor(stateInvestmentAsset.getTenor());
		assetsDetialInfo.setRepaymentType(stateInvestmentAsset.getRepaymentType());
		assetsDetialInfo.setFinancingCost(stateInvestmentAsset.getFinancingCost());
		assetsDetialInfo.setFinancingType(stateInvestmentAsset.getFinancingType());
		assetsDetialInfo.setFinancingPurpose(stateInvestmentAsset.getFinancingPurpose());
		assetsDetialInfo.setTimingRequirement(stateInvestmentAsset.getTimingRequirement());
		assetsDetialInfo.setCompanyName(stateInvestmentAsset.getCompanyName());
		assetsDetialInfo.setHolderBackground(stateInvestmentAsset.getHolderBackground());
		assetsDetialInfo.setCapital(stateInvestmentAsset.getCapital());
		assetsDetialInfo.setFoundedTime(dateTranToString(stateInvestmentAsset.getFoundedTime()));
		assetsDetialInfo.setMajorBusiness(stateInvestmentAsset.getMajorBusiness());
		assetsDetialInfo.setTotalAsset(stateInvestmentAsset.getTotalAsset());
		assetsDetialInfo.setNetAsset(stateInvestmentAsset.getNetAsset());
		assetsDetialInfo.setMainIncome(stateInvestmentAsset.getMainIncome());
		assetsDetialInfo.setAllowanceIncome(stateInvestmentAsset.getAllowanceIncome());
		assetsDetialInfo.setNetProfit(stateInvestmentAsset.getNetProfit());
		assetsDetialInfo.setAlRatio(stateInvestmentAsset.getAlRatio());
		assetsDetialInfo.setRating(stateInvestmentAsset.getRating());
		assetsDetialInfo.setForecast(stateInvestmentAsset.getForecast());
		assetsDetialInfo.setPublicDebt(stateInvestmentAsset.getPublicDebt());
		assetsDetialInfo.setMatureDate(dateTranToString(stateInvestmentAsset.getMatureDate()));
		assetsDetialInfo.setBankLine(stateInvestmentAsset.getBankLine());
		assetsDetialInfo.setBadSuit(stateInvestmentAsset.getBadSuit());
		assetsDetialInfo.setLawSuit(stateInvestmentAsset.getLawSuit());
		assetsDetialInfo.setFinanceCooporation(stateInvestmentAsset.getFinanceCooporation());
		assetsDetialInfo.setProjectType(stateInvestmentAsset.getProjectType());	
		assetsDetialInfo.setProjectCompliance(stateInvestmentAsset.getProjectCompliance());	
		assetsDetialInfo.setCompanyName(stateInvestmentAsset.getCompanyName());	
		assetsDetialInfo.setSelfFunded(stateInvestmentAsset.getSelfFunded());	
		assetsDetialInfo.setRepayArrangement(stateInvestmentAsset.getRepayArrangement());	
		assetsDetialInfo.setOtherFinancing(stateInvestmentAsset.getOtherFinancing());	
		assetsDetialInfo.setLandValue(stateInvestmentAsset.getLandValue());	
		assetsDetialInfo.setProjectValue(stateInvestmentAsset.getProjectValue());	
		assetsDetialInfo.setOtherCollateral(stateInvestmentAsset.getOtherCollateral());	
		assetsDetialInfo.setReceivable(stateInvestmentAsset.getReceivable());	
		assetsDetialInfo.setCounterParty(stateInvestmentAsset.getCounterParty());	
		assetsDetialInfo.setRepayDate(dateTranToString(stateInvestmentAsset.getRepayDate()));	
		assetsDetialInfo.setPledgeStock(stateInvestmentAsset.getPledgeStock());	
		assetsDetialInfo.setPledgeRatio(stateInvestmentAsset.getPledgeRatio());	
		assetsDetialInfo.setOtherPledge(stateInvestmentAsset.getOtherPledge());	
		assetsDetialInfo.setGuarantor(stateInvestmentAsset.getGuarantor());	
		assetsDetialInfo.setGuarantorBackground(stateInvestmentAsset.getGuarantorBackground());	
		assetsDetialInfo.setGuarantorRating(stateInvestmentAsset.getGuarantorRating());	
		assetsDetialInfo.setGovDecision(stateInvestmentAsset.getGovDecision());	
		assetsDetialInfo.setGovAgreement(stateInvestmentAsset.getGovAgreement());	
		assetsDetialInfo.setFinanceArrangement(stateInvestmentAsset.getFinanceArrangement());	
		assetsDetialInfo.setProvince(stateInvestmentAsset.getProvince());
		assetsDetialInfo.setCity(stateInvestmentAsset.getCity());
		assetsDetialInfo.setAdmGrade(stateInvestmentAsset.getAdmGrade());	
		assetsDetialInfo.setGdp(stateInvestmentAsset.getGdp());	
		assetsDetialInfo.setMainIndustry(stateInvestmentAsset.getMainIndustry());	
		assetsDetialInfo.setBigThreeRatio(stateInvestmentAsset.getBigThreeRatio());	
		assetsDetialInfo.setPopulation(stateInvestmentAsset.getPopulation());	
		assetsDetialInfo.setGovTotalIncome(stateInvestmentAsset.getGovTotalIncome());	
		assetsDetialInfo.setGeneralIncome(stateInvestmentAsset.getGeneralIncome());	
		assetsDetialInfo.setTransferIncome(stateInvestmentAsset.getTransferIncome());	
		assetsDetialInfo.setGovFundIncome(stateInvestmentAsset.getGovFundIncome());	
		assetsDetialInfo.setGovDebt(stateInvestmentAsset.getGovDebt());	
		assetsDetialInfo.setGovGuarantee(stateInvestmentAsset.getGovGuarantee());	
		assetsDetialInfo.setLiabilitiesRatio(stateInvestmentAsset.getLiabilitiesRatio());	
		assetsDetialInfo.setDebtRation(stateInvestmentAsset.getDebtRation());	
		assetsDetialInfo.setLisence(stateInvestmentAsset.getLisence());	
		assetsDetialInfo.setOrgInstCode(stateInvestmentAsset.getOrgInstCode());	
		assetsDetialInfo.setTaxReg(stateInvestmentAsset.getTaxReg());	
		assetsDetialInfo.setLeId(stateInvestmentAsset.getLeId());	
		assetsDetialInfo.setBankCard(stateInvestmentAsset.getBankCard());	
		assetsDetialInfo.setAcApproval(stateInvestmentAsset.getAcApproval());	
		assetsDetialInfo.setArticle(stateInvestmentAsset.getArticle());	
		assetsDetialInfo.setCaptialVer(stateInvestmentAsset.getCaptialVer());	
		assetsDetialInfo.setCreditReport(stateInvestmentAsset.getCreditReport());	
		assetsDetialInfo.setFeasibility(stateInvestmentAsset.getFeasibility());	
		assetsDetialInfo.setAuditReport(stateInvestmentAsset.getAuditReport());	
		assetsDetialInfo.setFinanceStatement(stateInvestmentAsset.getFinanceStatement());	
		assetsDetialInfo.setDataExpierationDate(stateInvestmentAsset.getDataExpierationDate());
		assetsDetialInfo.setDataGoveExpirationDate(stateInvestmentAsset.getDataGoveExpirationDate());
		assetsDetialInfo.setFinanceDvalid(dateTranToString(stateInvestmentAsset.getFinanceDvalid()));
		assetsDetialInfo.setFinancialDvalid(dateTranToString(stateInvestmentAsset.getFinancialDvalid()));
		assetsDetialInfo.setStatus(stateInvestmentAsset.getStatus());
		assetsDetialInfo.setOtherFile(stateInvestmentAsset.getOtherFile());
		
		assetsDetialInfo.setIsGuarante(stateInvestmentAsset.getIsGuarante());
		assetsDetialInfo.setGuarantorType(stateInvestmentAsset.getGuarantorType());
		return assetsDetialInfo;		
	}
	
	
	public AssetsDetialInfo ipoAssetTrantoAssetsDetialInfo(IpoAsset ipoAsset){
		AssetsDetialInfo assetsDetialInfo = new AssetsDetialInfo();
		assetsDetialInfo.setId(ipoAsset.getAid());
		assetsDetialInfo.setAssetType(ipoAsset.getAssetType());
		assetsDetialInfo.setCreatorId(ipoAsset.getCreatorId());
		assetsDetialInfo.setName(ipoAsset.getName());
		assetsDetialInfo.setShortName(ipoAsset.getShortName());
		assetsDetialInfo.setOtherPledge(ipoAsset.getOtherPledge());
		assetsDetialInfo.setBorrower(ipoAsset.getBorrower());
		assetsDetialInfo.setScale(ipoAsset.getScale());
		assetsDetialInfo.setTenor(ipoAsset.getTenor());
		assetsDetialInfo.setTenorType(ipoAsset.getTenorType());
		assetsDetialInfo.setRepaymentType(ipoAsset.getRepaymentType());
		assetsDetialInfo.setFinancingCost(ipoAsset.getFinancingCost());
		assetsDetialInfo.setFinancingType(ipoAsset.getFinancingType());
		assetsDetialInfo.setFinancingPurpose(ipoAsset.getFinancingPurpose());
		assetsDetialInfo.setTimingRequirement(ipoAsset.getTimingRequirement());
		assetsDetialInfo.setIpoRelation(ipoAsset.getIpoRelation());
		assetsDetialInfo.setListedCode(ipoAsset.getListedCode());
		assetsDetialInfo.setHolderBackground(ipoAsset.getHolderBackground());
		assetsDetialInfo.setCapital(ipoAsset.getCapital());
		assetsDetialInfo.setFoundedTime(dateTranToString(ipoAsset.getFoundedTime()));
		assetsDetialInfo.setIndustry(ipoAsset.getIndustry());
		assetsDetialInfo.setLocatedProvince(ipoAsset.getLocatedProvince());
		assetsDetialInfo.setLocatedCity(ipoAsset.getLocatedCity());
		assetsDetialInfo.setMajorBusiness(ipoAsset.getMajorBusiness());
		assetsDetialInfo.setBusinessDesc(ipoAsset.getBusinessDesc());
		assetsDetialInfo.setTotalAsset(ipoAsset.getTotalAsset());
		assetsDetialInfo.setNetAsset(ipoAsset.getNetAsset());
		assetsDetialInfo.setMainIncome(ipoAsset.getMainIncome());
		assetsDetialInfo.setNetProfit(ipoAsset.getNetProfit());
		assetsDetialInfo.setGrossProfitRatio(ipoAsset.getGrossProfitRatio());
		assetsDetialInfo.setRating(ipoAsset.getRating());
		assetsDetialInfo.setForecast(ipoAsset.getForecast());
		assetsDetialInfo.setPublicDebt(ipoAsset.getPublicDebt());
		assetsDetialInfo.setMatureDate(dateTranToString(ipoAsset.getMatureDate()));
		assetsDetialInfo.setBankLine(ipoAsset.getBankLine());
		assetsDetialInfo.setBadSuit(ipoAsset.getBadSuit());
		assetsDetialInfo.setLawSuit(ipoAsset.getLawSuit());
		assetsDetialInfo.setFinanceCooporation(ipoAsset.getFinanceCooporation());
		assetsDetialInfo.setLandValue(ipoAsset.getLandValue());
		assetsDetialInfo.setOtherCollateral(ipoAsset.getOtherCollateral());
		assetsDetialInfo.setReceivable(ipoAsset.getReceivable());
		assetsDetialInfo.setCounterParty(ipoAsset.getCounterParty());
		assetsDetialInfo.setRepayDate(dateTranToString(ipoAsset.getRepayDate()));
		assetsDetialInfo.setPledgeStock(ipoAsset.getPledgeStock());
		assetsDetialInfo.setPledgeRatio(ipoAsset.getPledgeRatio());
		assetsDetialInfo.setGuarantor(ipoAsset.getGuarantor());
		assetsDetialInfo.setGuarantorBackground(ipoAsset.getGuarantorBackground());
		assetsDetialInfo.setGuarantorRating(ipoAsset.getGuarantorRating());
		assetsDetialInfo.setLisence(ipoAsset.getLisence());
		assetsDetialInfo.setOrgInstCode(ipoAsset.getOrgInstCode());
		assetsDetialInfo.setTaxReg(ipoAsset.getTaxReg());
		assetsDetialInfo.setLeId(ipoAsset.getLeId());
		assetsDetialInfo.setBankCard(ipoAsset.getBankCard());
		assetsDetialInfo.setAcApproval(ipoAsset.getAcApproval());
		assetsDetialInfo.setArticle(ipoAsset.getArticle());
		assetsDetialInfo.setCaptialVer(ipoAsset.getCaptialVer());
		assetsDetialInfo.setCreditReport(ipoAsset.getCreditReport());
		assetsDetialInfo.setFeasibility(ipoAsset.getFeasibility());
		assetsDetialInfo.setAuditReport(ipoAsset.getAuditReport());
		assetsDetialInfo.setFinanceStatement(ipoAsset.getFinanceStatement()); 
		assetsDetialInfo.setDataExpierationDate(ipoAsset.getDataExpierationDate());
		assetsDetialInfo.setStatus(ipoAsset.getStatus());
		assetsDetialInfo.setFinanceDvalid(dateTranToString(ipoAsset.getFinanceDvalid()));
		assetsDetialInfo.setOtherFile(ipoAsset.getOtherFile());
		assetsDetialInfo.setProjectValue(ipoAsset.getProjectValue());
		assetsDetialInfo.setOtherFile(ipoAsset.getOtherFile());
		
		assetsDetialInfo.setIsTrust(ipoAsset.getIsTrust());
		assetsDetialInfo.setIsGuarante(ipoAsset.getIsGuarante());
		assetsDetialInfo.setGuarantorType(ipoAsset.getGuarantorType());
		assetsDetialInfo.setGuarantorFinancierRelation(ipoAsset.getGuarantorFinancierRelation());
		assetsDetialInfo.setGuarantorRegistMoney(ipoAsset.getGuarantorRegistMoney());
		assetsDetialInfo.setGuarantorTotalMoney(ipoAsset.getGuarantorTotalMoney());
		assetsDetialInfo.setGuarantorNetMoney(ipoAsset.getGuarantorNetMoney());
		assetsDetialInfo.setGuarantorNetProfit(ipoAsset.getGuarantorNetProfit());
		assetsDetialInfo.setGuarantorRevenue(ipoAsset.getGuarantorRevenue());
		assetsDetialInfo.setGuarantorGrossRate(ipoAsset.getGuarantorGrossRate());
		assetsDetialInfo.setGuarantorSetupDate(dateTranToString(ipoAsset.getGuarantorSetupDate()));
		assetsDetialInfo.setGuarantorDataExpired(dateTranToString(ipoAsset.getGuarantorDataExpired()));
		assetsDetialInfo.setGuarantorRatingOutlook(ipoAsset.getGuarantorRatingOutlook());
		assetsDetialInfo.setGuarantorIsPublish(ipoAsset.getGuarantorIsPublish());
		assetsDetialInfo.setGuarantorLastestDate(dateTranToString(ipoAsset.getGuarantorLastestDate()));
		assetsDetialInfo.setGuarantorBadBreach(ipoAsset.getGuarantorBadBreach());
		assetsDetialInfo.setGuarantorPendingAction(ipoAsset.getGuarantorPendingAction());
		return assetsDetialInfo;
	}
	
	
	public AssetsDetialInfo supplyChainAssetTrantoAssetsDetialInfo(SupplyChainAsset supplyChainAsset){
		AssetsDetialInfo assetsDetialInfo = new AssetsDetialInfo();
		assetsDetialInfo.setId(supplyChainAsset.getAid());
		assetsDetialInfo.setAssetType(supplyChainAsset.getAssetType());
		assetsDetialInfo.setCreatorId(supplyChainAsset.getCreatorId());
		assetsDetialInfo.setName(supplyChainAsset.getName());
		assetsDetialInfo.setShortName(supplyChainAsset.getShortName());
		assetsDetialInfo.setBorrower(supplyChainAsset.getBorrower());
		assetsDetialInfo.setScale(supplyChainAsset.getScale());
		assetsDetialInfo.setTenorType(supplyChainAsset.getTenorType());
		assetsDetialInfo.setTenor(supplyChainAsset.getTenor());
		assetsDetialInfo.setCoreEnterprise(supplyChainAsset.getCoreEnterprise());
		assetsDetialInfo.setIndustryChain(supplyChainAsset.getIndustryChain());
		assetsDetialInfo.setIndustry(supplyChainAsset.getIndustry());
		assetsDetialInfo.setFinancingCost(supplyChainAsset.getFinancingCost());
		assetsDetialInfo.setFinancingType(supplyChainAsset.getFinancingType());
		assetsDetialInfo.setMajorBusiness(supplyChainAsset.getMajorBusiness());
		assetsDetialInfo.setTradeType(supplyChainAsset.getTradeType());
		assetsDetialInfo.setRepaymentPeriod(supplyChainAsset.getRepaymentPeriod());
		assetsDetialInfo.setAvgArAge(supplyChainAsset.getAvgArAge());
		assetsDetialInfo.setTotalAsset(supplyChainAsset.getTotalAsset());
		assetsDetialInfo.setTotalDebt(supplyChainAsset.getTotalDebt());
		assetsDetialInfo.setNetAsset(supplyChainAsset.getNetAsset());
		assetsDetialInfo.setInventory(supplyChainAsset.getInventory());
		assetsDetialInfo.setAcReceiveable(supplyChainAsset.getAcReceiveable());
		assetsDetialInfo.setPrepayment(supplyChainAsset.getPrepayment());
		assetsDetialInfo.setMainIncome(supplyChainAsset.getMainIncome());
		assetsDetialInfo.setArRotationRatio(supplyChainAsset.getArRotationRatio());
		assetsDetialInfo.setInventoryRation(supplyChainAsset.getInventoryRation());
		assetsDetialInfo.setNetProfit(supplyChainAsset.getNetProfit());
		assetsDetialInfo.setGrossProfitRatio(supplyChainAsset.getGrossProfitRatio());
		assetsDetialInfo.setTrasactionHistory(supplyChainAsset.getTrasactionHistory());
		assetsDetialInfo.setSettlementType(supplyChainAsset.getSettlementType());
		assetsDetialInfo.setLogisticType(supplyChainAsset.getLogisticType());
		assetsDetialInfo.setLisenceNeeded(supplyChainAsset.getLisenceNeeded());
		assetsDetialInfo.setLineGrandingPolicy(supplyChainAsset.getLineGrandingPolicy());
		assetsDetialInfo.setRating(supplyChainAsset.getRating());
		assetsDetialInfo.setCreditEnforce(supplyChainAsset.getCreditEnforce());
		assetsDetialInfo.setLisence(supplyChainAsset.getLisence());
		assetsDetialInfo.setOrgInstCode(supplyChainAsset.getOrgInstCode());
		assetsDetialInfo.setTaxReg(supplyChainAsset.getTaxReg());
		assetsDetialInfo.setArticle(supplyChainAsset.getArticle());
		assetsDetialInfo.setCaptialVer(supplyChainAsset.getCaptialVer());
		assetsDetialInfo.setFeasibility(supplyChainAsset.getFeasibility());
		assetsDetialInfo.setAuditReport(supplyChainAsset.getAuditReport());
		assetsDetialInfo.setYearTransaction(supplyChainAsset.getYearTransaction());
		assetsDetialInfo.setDataExpierationDate(supplyChainAsset.getDataExpierationDate());
		assetsDetialInfo.setStatus(supplyChainAsset.getStatus());
		assetsDetialInfo.setFinanceDvalid(dateTranToString(supplyChainAsset.getFinanceDvalid()));
		assetsDetialInfo.setOtherFile(supplyChainAsset.getOtherFile());
		assetsDetialInfo.setFinanceStatement(supplyChainAsset.getFinanceStatement());
		assetsDetialInfo.setOtherFile(supplyChainAsset.getOtherFile());
		
		assetsDetialInfo.setIsGuarante(supplyChainAsset.getIsGuarante());
		assetsDetialInfo.setGuarantorType(supplyChainAsset.getGuarantorType());
		return assetsDetialInfo;
	}
	
	

	public AssetsDetialInfo peAbsAssetTrantoAssetsDetialInfo(PeAbsAsset peAbsAsset){
		AssetsDetialInfo assetsDetialInfo = new AssetsDetialInfo();
		assetsDetialInfo.setId(peAbsAsset.getAid());
		assetsDetialInfo.setAssetType(peAbsAsset.getAssetType());
		assetsDetialInfo.setCreatorId(peAbsAsset.getCreatorId());
		assetsDetialInfo.setName(peAbsAsset.getName());
		assetsDetialInfo.setShortName(peAbsAsset.getShortName());
		assetsDetialInfo.setBorrower(peAbsAsset.getBorrower());
		assetsDetialInfo.setScale(peAbsAsset.getScale());
		assetsDetialInfo.setTenor(peAbsAsset.getTenor());
		assetsDetialInfo.setTenorType(peAbsAsset.getTenorType());
		assetsDetialInfo.setAssetMgr(peAbsAsset.getAssetMgr());
		assetsDetialInfo.setSetTrench(peAbsAsset.getSetTrench());
		assetsDetialInfo.setSeniorPercent(peAbsAsset.getSeniorPercent());
		assetsDetialInfo.setSeniorRating(peAbsAsset.getSeniorRating());
		assetsDetialInfo.setAssetSubType(peAbsAsset.getAssetSubType());
		assetsDetialInfo.setRate(peAbsAsset.getRate());
		assetsDetialInfo.setAbsRatingReport(peAbsAsset.getAbsRatingReport());	
		assetsDetialInfo.setStatus(peAbsAsset.getStatus());
		assetsDetialInfo.setIssueDate(peAbsAsset.getIssueDate());
		assetsDetialInfo.setOtherFile(peAbsAsset.getOtherFile());
		
		assetsDetialInfo.setIsGuarante(peAbsAsset.getIsGuarante());
		assetsDetialInfo.setGuarantorType(peAbsAsset.getGuarantorType());
		return assetsDetialInfo;
	}
	
	
	public AssetsDetialInfo usufructTransferAssetTrantoAssetsDetialInfo(UsufructTransferAsset usufructTransferAsset){
		AssetsDetialInfo assetsDetialInfo = new AssetsDetialInfo();
		assetsDetialInfo.setId(usufructTransferAsset.getAid());
		assetsDetialInfo.setCreatorId(usufructTransferAsset.getCreatorId());
		assetsDetialInfo.setAssetType(usufructTransferAsset.getAssetType());
		assetsDetialInfo.setName(usufructTransferAsset.getName());
		assetsDetialInfo.setShortName(usufructTransferAsset.getShortName());
		assetsDetialInfo.setProjectName(usufructTransferAsset.getProjectName());
		assetsDetialInfo.setProjectType(usufructTransferAsset.getProjectType());
		assetsDetialInfo.setBorrower(usufructTransferAsset.getBorrower());
		assetsDetialInfo.setScale(usufructTransferAsset.getScale());
		assetsDetialInfo.setTenorType(usufructTransferAsset.getTenorType());
		assetsDetialInfo.setTenor(usufructTransferAsset.getTenor());
		assetsDetialInfo.setAssetMgr(usufructTransferAsset.getAssetMgr());
		assetsDetialInfo.setRepurchase(usufructTransferAsset.getRepurchase());
		assetsDetialInfo.setTradingStructure(usufructTransferAsset.getTradingStructure());
		assetsDetialInfo.setAssetSubType(usufructTransferAsset.getAssetSubType());
		assetsDetialInfo.setRate(usufructTransferAsset.getRate());
		assetsDetialInfo.setRelatedAgreement(usufructTransferAsset.getRelatedAgreement());
		assetsDetialInfo.setStatus(usufructTransferAsset.getStatus());
		assetsDetialInfo.setOtherFile(usufructTransferAsset.getOtherFile());
		
		assetsDetialInfo.setIsGuarante(usufructTransferAsset.getIsGuarante());
		assetsDetialInfo.setGuarantorType(usufructTransferAsset.getGuarantorType());
		return assetsDetialInfo;
	}
	
	public AssetsDetialInfo industryCommunityAssetTrantoAssetsDetialInfo(IndustryCommunityAsset industryCommunityAsset){
		AssetsDetialInfo assetsDetialInfo = new AssetsDetialInfo();
		BeanUtils.copyProperties(industryCommunityAsset, assetsDetialInfo);
		assetsDetialInfo.setFoundedTime(dateTranToString(industryCommunityAsset.getFoundedTime()));
		assetsDetialInfo.setFinancingDvalid(dateTranToString(industryCommunityAsset.getFinancingDvalid()));
		assetsDetialInfo.setMatureDate(dateTranToString(industryCommunityAsset.getMatureDate()));
		assetsDetialInfo.setRepayDate(dateTranToString(industryCommunityAsset.getRepayDate()));
		assetsDetialInfo.setGuarantorDataExpired(dateTranToString(industryCommunityAsset.getGuarantorDataExpired()));
		assetsDetialInfo.setGuarantorSetupDate(dateTranToString(industryCommunityAsset.getGuarantorSetupDate()));
		assetsDetialInfo.setGuarantorLastestDate(dateTranToString(industryCommunityAsset.getGuarantorLastestDate()));
		
		return assetsDetialInfo;
	}
	
	public IndustryCommunityAsset assetsInfoInsertIndustryCommunityAsset(AssetsDetialInfo assetsInfo){
		IndustryCommunityAsset industryCommunityAsset = new IndustryCommunityAsset();
		BeanUtils.copyProperties(assetsInfo, industryCommunityAsset);
		industryCommunityAsset.setFoundedTime(stringTranToDate(assetsInfo.getFoundedTime()));
		industryCommunityAsset.setFinancingDvalid(stringTranToDate(assetsInfo.getFinancingDvalid()));
		industryCommunityAsset.setMatureDate(stringTranToDate(assetsInfo.getMatureDate()));
		industryCommunityAsset.setRepayDate(stringTranToDate(assetsInfo.getRepayDate()));
		industryCommunityAsset.setGuarantorDataExpired(stringTranToDate(assetsInfo.getGuarantorDataExpired()));
		industryCommunityAsset.setGuarantorSetupDate(stringTranToDate(assetsInfo.getGuarantorSetupDate()));
		industryCommunityAsset.setGuarantorLastestDate(stringTranToDate(assetsInfo.getGuarantorLastestDate()));
		
		return industryCommunityAsset;			
	}

	/**
     * 查询资产在某个步骤的协议文档
     * @param assetId  资产id
     * @param stepId  流程步骤id
     * @return
     */
	public RecordLog queryRecordLog(String assetId, String stepId) {
		return recordLogMapper.queryRecordLog(assetId, stepId);
	}

	/**
	 * 新增其他工商类资产
	 */
	public int addIndustryCommunityAsset(AssetsDetialInfo assetsDetialInfo) {
		IndustryCommunityAsset industryCommunityAsset = assetsInfoInsertIndustryCommunityAsset(assetsDetialInfo);
		Assets asset = assetsInfoInsertAsset(assetsDetialInfo);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());// new Date()为获取当前系统时间
		asset.setCreateTime(createTime);
		asset.setEditTime(createTime);
		if(asset.getTenor() != null && asset.getTenorType() != null){
			int tenor = asset.getTenor();
			int tenorType = asset.getTenorType();
			asset.setTenorDay(getTenorDay(tenor,tenorType));
		}	
		int flagm = assetsMapper.insertSelective(asset);
		if(flagm != 1){
			return 0;
		}
		industryCommunityAsset.setAid(asset.getId());
		assetsDetialInfo.setAid(asset.getId());
		return industryCommunityAssetMapper.insertSelective(industryCommunityAsset);
	}

	/**
	 * 修改其他工商类资产
	 */
	public int editIndustryCommunityAsset(AssetsDetialInfo assetsDetialInfo) {
		IndustryCommunityAsset industryCommunityAsset = assetsInfoInsertIndustryCommunityAsset(assetsDetialInfo);
		Assets asset = assetsInfoInsertAsset(assetsDetialInfo);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());// new Date()为获取当前系统时间
		asset.setCreateTime(createTime);
		asset.setEditTime(createTime);
		if(asset.getTenor() != null && asset.getTenorType() != null){
			int tenor = asset.getTenor();
			int tenorType = asset.getTenorType();
			asset.setTenorDay(getTenorDay(tenor,tenorType));
		}	
		asset.setId(assetsDetialInfo.getAid());
		int flagm = assetsMapper.updateByPrimaryKeySelective(asset);
		if(flagm != 1){
			return 0;
		}
		assetsDetialInfo.setAid(asset.getId());
		return industryCommunityAssetMapper.updateByAssetsidSelective(industryCommunityAsset);
	}

	/**
	 * 查询需要兑付的ABS资产包
	 * @return
	 */
	public List<Assets> getABSAssetsToPay(){
		return assetsMapper.getABSAssetsToPay();
	}

	/**
	 * 查询未到兑付状态的资产包列表
	 * @return
	 */
	public List<AssetPackage> queryAssetPackageList(){
		return assetsMapper.queryAssetPackageList();
	}

	/**
	 * 查询某个资产的基础资产金额
	 * @return
	 */
	public AssetPackage queryAssetBaseAmount(Integer aid){
		return assetsMapper.queryAssetBaseAmount(aid);
	}


	/**
	 * 资产包修改为正常(资产包中匹配的基础资产规模大于预警规模)
	 * @param aid
	 * @return
	 */
	public int normalAssetPackage(Integer aid){
		return assetsMapper.normalAssetPackage(aid);
	}


	/**
	 * 批量保存资产包基础资产更新记录
	 * @param aid
	 * @param baseAssetIds
	 * @param changeType  变更动作类型：1-新增  2-手动被替换  3-手动替换  4-自动被替换  5-自动替换 6-自动补充  7-完结解除
	 * @return
	 */
	public int saveAssetChangeHisBatch(String aid,String baseAssetIds,String changeType){
		String[] baseAssetIdsArray = new String[]{};
		List list = new ArrayList();
		if(!"".equals(baseAssetIds) && baseAssetIds != null){
			baseAssetIdsArray = baseAssetIds.split(",");
			for(String bai : baseAssetIdsArray){
				PeAccountsReceivable par = peAccountsReceivableMapper.selectByPrimaryKey(Integer.parseInt(bai));
				BaseAssetChangeHisVo bacv = new BaseAssetChangeHisVo();
				bacv.setBaseAssetId(bai);
				bacv.setAid(aid);
				bacv.setChangeType(changeType);
				bacv.setCreateName("自动");
				if("2".equals(par.getStatus()) || "5".equals(par.getStatus())){
					bacv.setAssetAmount(par.getBillAmount());
				}else{
					bacv.setAssetAmount(par.getBillBalance());
				}
				bacv.setReceivableDebtor(par.getDebtorName());
				bacv.setInvoiceCode(par.getInvoiceCode());
				bacv.setInvoiceNo(par.getInvoiceNo());
				list.add(bacv);
			}
		}

		return peAssetChangeHistoryMapper.insertBatchInit(list);
	}

	/**
	 * 批量保存资产包资金更新记录
	 * @param peAccountsReceivable
	 * @param backpayRecord
	 * @param paybackAmount
	 * @return
	 */
	public int saveFundChangeHisSelective(PeAccountsReceivable peAccountsReceivable,PeBackpaymentRecord backpayRecord,BigDecimal paybackAmount,String changeType){
		PeFundChangeHistory peFundChangeHistory = new PeFundChangeHistory();
  		if(!"".equals(peAccountsReceivable.getAid()) && peAccountsReceivable.getAid() != null){
			PeFundChangeHistory pfh = peFundChangeHistoryMapper.selectNewestRecord(peAccountsReceivable.getAid());
			peFundChangeHistory.setAid(peAccountsReceivable.getAid());
			peFundChangeHistory.setBalanceAmount(pfh.getBalanceAmount().subtract(paybackAmount));
		}
		peFundChangeHistory.setBaseAssetId(peAccountsReceivable.getId());
		peFundChangeHistory.setPaybackAmount(paybackAmount);
		peFundChangeHistory.setCreateName("自动");
		peFundChangeHistory.setAccountName(backpayRecord.getPayerName());
		peFundChangeHistory.setAccountNo(backpayRecord.getPayerAccount());
		peFundChangeHistory.setChangeType(changeType);

		return peFundChangeHistoryMapper.insertSelective(peFundChangeHistory);
	}

	/**
	 * 准备到期
	 * @return
	 */
	public int expiredAssetPackage(){
	  return assetsMapper.expiredAssetPackage();
	}

	/**
	 * 查询是否已记录逾期资产记录
	 * @param aid
	 * @return
	 */
	public int selectHaveSaveRecord(Integer aid){
		return peAssetChangeHistoryMapper.selectHaveSaveRecord(aid);
	}

}
