package com.jinhui.service.assets;

import java.math.BigDecimal;
import java.util.List;

import com.jinhui.model.*;
import com.jinhui.model.abs.AssetPackage;
import com.jinhui.model.abs.PeAccountsReceivable;
import com.jinhui.model.abs.PeAssetChangeHistory;
import com.jinhui.model.abs.PeBackpaymentRecord;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;


public interface AssetsService {
	
	public Assets getAssetsById(int assetId);
	
	public PageInfo<Assets> getAllAssets(Integer pageNo,Integer pageSize);
	
	public PageInfo<Assets> getAssets(Integer pageNo, Integer pageSize,Assets assets);
	
	public PageInfo<Assets> getMyAssets(Integer pageNo, Integer pageSize,Integer orgId,String roleType);

	int addIpoAsset(AssetsDetialInfo assetsDetialInfo);
	
	int editIpoAsset(AssetsDetialInfo assetsDetialInfo);

	//int addAsset(AssetsDetialInfo assetsInfo);

	int addPeAbsAsset(AssetsDetialInfo assetsDetialInfo);
	
    int editPeAbsAsset(AssetsDetialInfo assetsDetialInfo); 

	int addStateInvestmentAsset(AssetsDetialInfo assetsDetialInfo);
	
	int editStateInvestmentAsset(AssetsDetialInfo assetsDetialInfo);

	int addSupplyChainAsset(AssetsDetialInfo assetsDetialInfo);
	
	int editSupplyChainAsset(AssetsDetialInfo assetsDetialInfo);

	int addUsufructTransferAsset(AssetsDetialInfo assetsDetialInfo);
	
	int editUsufructTransferAsset(AssetsDetialInfo assetsDetialInfo);
	
	//新增其他工商类资产
    int addIndustryCommunityAsset(AssetsDetialInfo assetsDetialInfo);
	//修改其他工商类资产
	int editIndustryCommunityAsset(AssetsDetialInfo assetsDetialInfo);

	AssetsDetialInfo getSubAssetInfoById(int assetId,int assetType);

	int assetToProject(Assets asset);

	int checkRecord(Assets asset);

	int putOnRecordStep(RecordLog recordLog);
	
	/**
     * 查询资产在某个步骤的协议文档
     * @param assetId  资产id
     * @param stepId  流程步骤id
     * @return
     */
    RecordLog queryRecordLog(String assetId,String stepId);

	/**
	 * 查询需要兑付的ABS资产包
	 * @return
	 */
	List<Assets> getABSAssetsToPay();

	/**
	 * 查询未到兑付状态的资产包列表
	 * @return
	 */
	List<AssetPackage> queryAssetPackageList();

	/**
	 * 查询某个资产的基础资产金额
	 * @return
	 */
	AssetPackage queryAssetBaseAmount(Integer aid);


	/**
	 * 资产包修改为正常(资产包中匹配的基础资产规模大于预警规模)
	 * @param aid
	 * @return
	 */
	int normalAssetPackage(Integer aid);

	/**
	 * 准备到期
	 * @return
	 */
	int expiredAssetPackage();

	/**
	 * 批量保存资产包基础资产更新记录
	 * @param aid
	 * @param baseAssetIds
	 * @param changeType  变更动作类型：1-新增  2-手动被替换  3-手动替换  4-自动被替换  5-自动替换 6-手动补充 7-完结解除
	 * @return
	 */
	int saveAssetChangeHisBatch(String aid,String baseAssetIds,String changeType);

	/**
	 * 批量保存资产包资金更新记录
	 * @param peAccountsReceivable
	 * @param backpayRecord
	 * @param paybackAmount
	 * @return
	 */
	int saveFundChangeHisSelective(PeAccountsReceivable peAccountsReceivable, PeBackpaymentRecord backpayRecord, BigDecimal paybackAmount,String changeType);


	/**
	 * 查询是否已记录逾期资产记录
	 * @param aid
	 * @return
	 */
	int selectHaveSaveRecord(Integer aid);

}
