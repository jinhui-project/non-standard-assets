package com.jinhui.mapper.assets;

import java.util.List;

import com.jinhui.model.Assets;
import com.jinhui.model.Products;
import com.jinhui.model.abs.AssetPackage;
import org.apache.ibatis.annotations.Param;


public interface AssetsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Assets record);

    int insertSelective(Assets record);

    Assets selectByPrimaryKey(Integer id);
    
    Assets selectByIdandGid(Assets record);

    int updateByPrimaryKeySelective(Assets record);

    int updateByPrimaryKey(Assets record);
    
    List<Assets> getAllAssets();
    
    List<Assets> getAssets(Assets record);
    
    List<Assets> getMyAssets(Integer orgId);
    
    // 分页查询我的资产-资产列表(备案机构方_我备案的) 
    List<Assets> getMyRecordAssetsList(Integer orgId);
    
    // 分页查询我的资产-资产列表(备案机构方_我反馈的)
    List<Assets> getFeedBackRecordAssetsList(Integer orgId);
    
    // 分页查询我的资产-资产列表(资金方_我反馈的)
    List<Assets> getFeedBackFundsAssetsList(Integer orgId);
    
    // 分页查询我的资产-资产列表(资金方_我销售的)
    List<Assets> getSaleFundsAssetsList(Integer orgId);
    
    //  分页查询我的资产-资产列表(资产方) 
    List<Assets> getPropertyAssetsList(Integer orgId);
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
    AssetPackage queryAssetBaseAmount(@Param("aid") Integer aid);


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

}