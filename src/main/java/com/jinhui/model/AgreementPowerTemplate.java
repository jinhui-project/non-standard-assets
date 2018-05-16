package com.jinhui.model;

import java.io.Serializable;

/**
 * <p>Title:AgreementPowerTemplate</p>
 * <p>Description:(权限)协议模板里三个分类方式和可选值表VO类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2017-03-29
 */
public class AgreementPowerTemplate implements Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 2113703840385024L;

		/** 机构表主键id */	private  Long gid;	/** 产品类型：0：(银杏)理财、1(银杏)私募债,2投资工具,3资产支持计划,4资产管理类的其他，5定向融资工具，6收益权凭证，7直接融资类的其他

 */	private  String productType;	/** 备案机构：0贵州股权金融资产交易中心 */	private  String recordInstitutionsType;	/** 签约角色：0资金方、1资产方,暂时不用这字段 */	private  Integer signingType;	/** 资金方：0否，1是 */	private  Integer moneySideType;	/** 资产方：0否，1是 */	private  Integer assetType;	/** 备案机构：0否，1是 */	private  Integer organizationType;	/** 受托管理人：0否，1是 */	private  Integer trusteeType;	/** 服务商：0否，1是 */	private  Integer serviceType;	/** 其他机构：0否，1是 */	private  Integer otherType;
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getRecordInstitutionsType() {
		return recordInstitutionsType;
	}
	public void setRecordInstitutionsType(String recordInstitutionsType) {
		this.recordInstitutionsType = recordInstitutionsType;
	}
	public Integer getSigningType() {
		return signingType;
	}
	public void setSigningType(Integer signingType) {
		this.signingType = signingType;
	}
	public Integer getMoneySideType() {
		return moneySideType;
	}
	public void setMoneySideType(Integer moneySideType) {
		this.moneySideType = moneySideType;
	}
	public Integer getAssetType() {
		return assetType;
	}
	public void setAssetType(Integer assetType) {
		this.assetType = assetType;
	}
	public Integer getOrganizationType() {
		return organizationType;
	}
	public void setOrganizationType(Integer organizationType) {
		this.organizationType = organizationType;
	}
	public Integer getTrusteeType() {
		return trusteeType;
	}
	public void setTrusteeType(Integer trusteeType) {
		this.trusteeType = trusteeType;
	}
	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	public Integer getOtherType() {
		return otherType;
	}
	public void setOtherType(Integer otherType) {
		this.otherType = otherType;
	}
}