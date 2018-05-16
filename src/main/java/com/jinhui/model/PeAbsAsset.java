package com.jinhui.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Title:PeAbsAsset
 * </p>
 * 
 * @version v1.0 2016-12-13
 */
public class PeAbsAsset implements Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 1963784391280640L;

	private String token;
	/** 资产ID */
	private Integer aid;
	/** 资产类别 */
	private Integer assetType;
	/** 原始权益人 */
	private String borrower;
	/** 发行规模 */
	private BigDecimal scale;
	/** 期限类型 */
	private Integer tenorType;
	/** 存续期 */
	private Integer tenor;
	/** 产品管理人 */
	private String assetMgr;
	/** 是否分级 */
	private Integer setTrench;
	/** 优先劣后比 */
	private String seniorPercent;
	/** 优先级评级 */
	private Integer seniorRating;
	/** 基础资产类型 */
	private Integer assetSubType;
	/** 发行利率 */
	private BigDecimal rate;
	/** ABS评级报告 */
	private String absRatingReport;

	private String otherFile;

	private Integer status;

	private String issueDate;

	private String name;

	private String shortName;

	private Integer creatorId;

	private Integer isGuarante;

	private Integer guarantorType;

	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getAssetType() {
		return this.assetType;
	}

	public void setAssetType(Integer assetType) {
		this.assetType = assetType;
	}

	public String getBorrower() {
		return this.borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public BigDecimal getScale() {
		return this.scale;
	}

	public void setScale(BigDecimal scale) {
		this.scale = scale;
	}

	public Integer getTenorType() {
		return this.tenorType;
	}

	public void setTenorType(Integer tenorType) {
		this.tenorType = tenorType;
	}

	public Integer getTenor() {
		return this.tenor;
	}

	public void setTenor(Integer tenor) {
		this.tenor = tenor;
	}

	public String getAssetMgr() {
		return this.assetMgr;
	}

	public void setAssetMgr(String assetMgr) {
		this.assetMgr = assetMgr;
	}

	public Integer getSetTrench() {
		return this.setTrench;
	}

	public void setSetTrench(Integer setTrench) {
		this.setTrench = setTrench;
	}

	public String getSeniorPercent() {
		return this.seniorPercent;
	}

	public void setSeniorPercent(String seniorPercent) {
		this.seniorPercent = seniorPercent;
	}

	public Integer getSeniorRating() {
		return this.seniorRating;
	}

	public void setSeniorRating(Integer seniorRating) {
		this.seniorRating = seniorRating;
	}

	public Integer getAssetSubType() {
		return this.assetSubType;
	}

	public void setAssetSubType(Integer assetSubType) {
		this.assetSubType = assetSubType;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getAbsRatingReport() {
		return this.absRatingReport;
	}

	public void setAbsRatingReport(String absRatingReport) {
		this.absRatingReport = absRatingReport;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOtherFile() {
		return otherFile;
	}

	public void setOtherFile(String otherFile) {
		this.otherFile = otherFile;
	}

	public Integer getIsGuarante() {
		return isGuarante;
	}

	public void setIsGuarante(Integer isGuarante) {
		this.isGuarante = isGuarante;
	}

	public Integer getGuarantorType() {
		return guarantorType;
	}

	public void setGuarantorType(Integer guarantorType) {
		this.guarantorType = guarantorType;
	}
	
}