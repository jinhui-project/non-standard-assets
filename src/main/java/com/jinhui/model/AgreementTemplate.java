package com.jinhui.model;

import java.io.Serializable;

/**
 * <p>Title:AgreementTemplate</p>
 * <p>Description:文件模版类型表VO类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2017-03-07
 */
public class AgreementTemplate  implements Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 2082394985089024L;

		/** fileinfos表主键id */	private Long fid;	/** 产品类型：0：银杏理财、1银杏私募债 */	private Integer productType;	/** 备案机构：0贵州股权金融资产交易中心 */	private Integer recordInstitutionsType;	/** 签约角色：0资金方、1资产方 */	private Integer signingType;
	
	/** 资金方：0否，1是 */
	private Integer moneySideType;
	/** 资产方：0否，1是 */
	private Integer assetType;
	/** 备案机构：0否，1是 */
	private Integer organizationType;
	/** 受托管理人：0否，1是 */
	private Integer trusteeType;
	/** 服务商：0否，1是 */
	private Integer serviceType;
	/** 其他机构：0否，1是 */
	private Integer otherType;	public  Long getFid() {	    return this.fid;	}	public void setFid( Long fid) {	    this.fid=fid;	}	public  Integer getProductType() {	    return this.productType;	}	public void setProductType( Integer productType) {	    this.productType=productType;	}	public  Integer getRecordInstitutionsType() {	    return this.recordInstitutionsType;	}	public void setRecordInstitutionsType( Integer recordInstitutionsType) {	    this.recordInstitutionsType=recordInstitutionsType;	}	public  Integer getSigningType() {	    return this.signingType;	}	public void setSigningType( Integer signingType) {	    this.signingType=signingType;	}
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