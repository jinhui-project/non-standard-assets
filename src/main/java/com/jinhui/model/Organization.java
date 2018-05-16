package com.jinhui.model;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title:Organization</p>
 * <p>Description:机构信息表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-12-15
 */
public class Organization extends QueryEntity implements Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1966672154740736L;
	
	private Long id;
	
	/** 上级id */
	private Integer fid;
	/** 名称 */
	private String name;
	/** 机构简称 */
	private String shortName;
	/** 权限串，0代表否，1代表是(000)备案类型第一位、资产方类型第二位、资金方类型第三位 */
	private String power;
	/**  */
	private Integer isSuper;
	/**  */
	private Integer isLimits;
	/** 服务类型0未指定1会计师事务所2律师事务所 */
	private Integer serverType;
	/** 备案类型0未指定1金交2证券3信托 */
	private Integer recordType;
	/** 资产方类型0未指定1上市公司2政府平台3一般企业4资产中介 */
	private Integer assetsType;
	/** 资金方类型：0未指定1三方2互金平台3私募4银行 */
	private Integer fundType;
	/** 其他类型：0未指定1会计 */
	private Integer otherType;
	/** 销售偏好数组 */
	private  String interest;
	/** 法人代表 */
	private  String legalPerson;
	/** 注册资本 */
	private  String regitFund;
	/** 公司主页 */
	private  String homePage;
	/** 社会信用代码 */
	private  String creditCode;
	/** 营业执照路径 */
	private  String busiLicence;
	/** 资质附件路径 */
	private  String certificate;
	/** 平台运营附件路径 */
	private  String platBussReport;
	/** 联系地址 */
	private  String address;
	/** 联系人 */
	private  String contact;
	/** 联系电话 */
	private  String tel;
	/** 手机号码 */
	private  String mobile;
	/** 联系人邮箱 */
	private  String email;
	/** 推荐人姓名 */
	private  String refereeId;
	/** 考核计数 */
	private  Integer refereeNum;
	/** 创建人id */
	private  Long creatorId;
	/** 创建时间 */
	private  Long createTime;
	/** 修改人id */
	private  Long editorId;
	/** 修改时间 */
	private  Long editorTime;
	/** 审核状态 */
	private  Integer auditStatus;
	
	/** 需要删除的文件id*/
	private String delFid;
	
	private String token;
	
	/** 营业执照路径 */
	private List<Fileinfos>  busiLicenceFile;
	/** 资质附件路径 */
	private  List<Fileinfos>  certificateFile;
	
	private  List<Fileinfos>   platBussReportFile;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public Integer getIsSuper() {
		return isSuper;
	}
	public void setIsSuper(Integer isSuper) {
		this.isSuper = isSuper;
	}
	public Integer getIsLimits() {
		return isLimits;
	}
	public void setIsLimits(Integer isLimits) {
		this.isLimits = isLimits;
	}
	public Integer getServerType() {
		return serverType;
	}
	public void setServerType(Integer serverType) {
		this.serverType = serverType;
	}
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	public Integer getAssetsType() {
		return assetsType;
	}
	public void setAssetsType(Integer assetsType) {
		this.assetsType = assetsType;
	}
	public Integer getFundType() {
		return fundType;
	}
	public void setFundType(Integer fundType) {
		this.fundType = fundType;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getRegitFund() {
		return regitFund;
	}
	public void setRegitFund(String regitFund) {
		this.regitFund = regitFund;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getBusiLicence() {
		return busiLicence;
	}
	public void setBusiLicence(String busiLicence) {
		this.busiLicence = busiLicence;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRefereeId() {
		return refereeId;
	}
	public void setRefereeId(String refereeId) {
		this.refereeId = refereeId;
	}
	public Integer getRefereeNum() {
		return refereeNum;
	}
	public void setRefereeNum(Integer refereeNum) {
		this.refereeNum = refereeNum;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getEditorId() {
		return editorId;
	}
	public void setEditorId(Long editorId) {
		this.editorId = editorId;
	}
	public Long getEditorTime() {
		return editorTime;
	}
	public void setEditorTime(Long editorTime) {
		this.editorTime = editorTime;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getDelFid() {
		return delFid;
	}
	public void setDelFid(String delFid) {
		this.delFid = delFid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<Fileinfos> getBusiLicenceFile() {
		return busiLicenceFile;
	}
	public void setBusiLicenceFile(List<Fileinfos> busiLicenceFile) {
		this.busiLicenceFile = busiLicenceFile;
	}
	public List<Fileinfos> getCertificateFile() {
		return certificateFile;
	}
	public void setCertificateFile(List<Fileinfos> certificateFile) {
		this.certificateFile = certificateFile;
	}
	public String getPlatBussReport() {
		return platBussReport;
	}
	public void setPlatBussReport(String platBussReport) {
		this.platBussReport = platBussReport;
	}
	public List<Fileinfos> getPlatBussReportFile() {
		return platBussReportFile;
	}
	public void setPlatBussReportFile(List<Fileinfos> platBussReportFile) {
		this.platBussReportFile = platBussReportFile;
	}

	public Integer getOtherType() {
		return otherType;
	}

	public void setOtherType(Integer otherType) {
		this.otherType = otherType;
	}
}