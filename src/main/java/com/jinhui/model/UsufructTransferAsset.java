package com.jinhui.model;

import java.math.BigDecimal;

public class UsufructTransferAsset {
	private String token;

	private Integer aid;

	private Integer assetType;

	private Integer creatorId;

	private String name;

	private String shortName;

	private String projectName;

	private String otherFile;

	private Integer projectType;

	private String borrower;

	private BigDecimal scale;

	private BigDecimal financingCost;

	private Integer status;

	private Integer tenorType;

	private Integer tenor;

	private String assetMgr;

	private Integer repurchase;

	private String tradingStructure;

	private Integer assetSubType;

	private BigDecimal rate;

	private String relatedAgreement;

	private Integer isGuarante;

	private Integer guarantorType;

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getAssetType() {
		return assetType;
	}

	public void setAssetType(Integer assetType) {
		this.assetType = assetType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName == null ? null : projectName.trim();
	}

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower == null ? null : borrower.trim();
	}

	public BigDecimal getScale() {
		return scale;
	}

	public void setScale(BigDecimal scale) {
		this.scale = scale;
	}

	public Integer getTenorType() {
		return tenorType;
	}

	public void setTenorType(Integer tenorType) {
		this.tenorType = tenorType;
	}

	public Integer getTenor() {
		return tenor;
	}

	public void setTenor(Integer tenor) {
		this.tenor = tenor;
	}

	public String getAssetMgr() {
		return assetMgr;
	}

	public void setAssetMgr(String assetMgr) {
		this.assetMgr = assetMgr == null ? null : assetMgr.trim();
	}

	public Integer getRepurchase() {
		return repurchase;
	}

	public void setRepurchase(Integer repurchase) {
		this.repurchase = repurchase;
	}

	public String getTradingStructure() {
		return tradingStructure;
	}

	public void setTradingStructure(String tradingStructure) {
		this.tradingStructure = tradingStructure == null ? null
				: tradingStructure.trim();
	}

	public Integer getAssetSubType() {
		return assetSubType;
	}

	public void setAssetSubType(Integer assetSubType) {
		this.assetSubType = assetSubType;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getRelatedAgreement() {
		return relatedAgreement;
	}

	public void setRelatedAgreement(String relatedAgreement) {
		this.relatedAgreement = relatedAgreement == null ? null
				: relatedAgreement.trim();
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

	public BigDecimal getFinancingCost() {
		return financingCost;
	}

	public void setFinancingCost(BigDecimal financingCost) {
		this.financingCost = financingCost;
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