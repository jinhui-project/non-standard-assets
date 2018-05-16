package com.jinhui.model;

import java.io.Serializable;

/**
 * <p>Title:Fileinfos</p>
 * <p>Description:文件信息表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-12-20
 */
public class Fileinfos extends QueryEntity implements Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1973786781763584L;

	private Long id;
	/** 机构id */
	private Long gid;	/** 文件路径(包含文件名和后缀) */	private  String filePath;	/** 文件名  asc  或者     dsc  */	private  String fileName;	/** 文件类型(模板和普通文件) */	private  String fileType;	/** 上传时间 */	private  Long uploadTime;	/** 修改时间 */	private  Long editTime;
	
	/** 上传时间 */
	private  String uploadTimeStr;
	/** 修改时间 */
	private  String editTimeStr;
	
	/** 0用户上传文件1模板文件 */
	private Integer type;
	/** 文件首字母（排序用） */
	private String firstLetter;
	
	private String token;
	
	
	/** 产品类型：0：银杏理财、1银杏私募债 */
	private Integer productType;
	/** 备案机构：0贵州股权金融资产交易中心 */
	private Integer recordInstitutionsType;
	/** 签约角色：0资金方、1资产方 */
	private Integer signingType;
	
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
	private Integer otherType;	public String getUploadTimeStr() {
		return uploadTimeStr;
	}
	public void setUploadTimeStr(String uploadTimeStr) {
		this.uploadTimeStr = uploadTimeStr;
	}
	public String getEditTimeStr() {
		return editTimeStr;
	}
	public void setEditTimeStr(String editTimeStr) {
		this.editTimeStr = editTimeStr;
	}
	public  Long getId() {	    return this.id;	}	public void setId( Long id) {	    this.id=id;	}	public  String getFilePath() {	    return this.filePath;	}	public void setFilePath( String filePath) {	    this.filePath=filePath;	}	public  String getFileName() {	    return this.fileName;	}	public void setFileName( String fileName) {	    this.fileName=fileName;	}	public  String getFileType() {	    return this.fileType;	}	public void setFileType( String fileType) {	    this.fileType=fileType;	}	public  Long getUploadTime() {	    return this.uploadTime;	}	public void setUploadTime( Long uploadTime) {	    this.uploadTime=uploadTime;	}	public  Long getEditTime() {	    return this.editTime;	}	public void setEditTime( Long editTime) {	    this.editTime=editTime;	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getFirstLetter() {
		return firstLetter;
	}
	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	public Integer getRecordInstitutionsType() {
		return recordInstitutionsType;
	}
	public void setRecordInstitutionsType(Integer recordInstitutionsType) {
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
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
}