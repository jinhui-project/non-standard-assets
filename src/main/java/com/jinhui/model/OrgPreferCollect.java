package com.jinhui.model;

public class OrgPreferCollect {
    private Integer id;
    
    private Integer organizationId;

    private Short buyerNum;

    private Short totalTrade;

    private Short monthTrade;

    private Short threeRate;

    private Short sixRate;

    private Short twelveRate;

    private Short otherRate;

    private String assetTypeOne;

    private String govermentTwo;

    private String listedCompanyTwo;

    private String financingMaturity;

    private String financingCeiling;

    private Short threeAssetPackage;

    private Short sixAssetPackage;

    private Short twelveAssetPackage;

    private Short otherAssetPackage;

    private Integer creatorId;
    
    private Integer editorId;

    private String createTime;

    private String editorTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getBuyerNum() {
		return buyerNum;
	}

	public void setBuyerNum(Short buyerNum) {
		this.buyerNum = buyerNum;
	}

	public Short getTotalTrade() {
		return totalTrade;
	}

	public void setTotalTrade(Short totalTrade) {
		this.totalTrade = totalTrade;
	}

	public Short getMonthTrade() {
		return monthTrade;
	}

	public void setMonthTrade(Short monthTrade) {
		this.monthTrade = monthTrade;
	}

	public Short getThreeRate() {
		return threeRate;
	}

	public void setThreeRate(Short threeRate) {
		this.threeRate = threeRate;
	}

	public Short getSixRate() {
		return sixRate;
	}

	public void setSixRate(Short sixRate) {
		this.sixRate = sixRate;
	}

	public Short getTwelveRate() {
		return twelveRate;
	}

	public void setTwelveRate(Short twelveRate) {
		this.twelveRate = twelveRate;
	}

	public Short getOtherRate() {
		return otherRate;
	}

	public void setOtherRate(Short otherRate) {
		this.otherRate = otherRate;
	}

	public String getAssetTypeOne() {
		return assetTypeOne;
	}

	public void setAssetTypeOne(String assetTypeOne) {
		this.assetTypeOne = assetTypeOne;
	}

	public String getGovermentTwo() {
		return govermentTwo;
	}

	public void setGovermentTwo(String govermentTwo) {
		this.govermentTwo = govermentTwo;
	}

	public String getListedCompanyTwo() {
		return listedCompanyTwo;
	}

	public void setListedCompanyTwo(String listedCompanyTwo) {
		this.listedCompanyTwo = listedCompanyTwo;
	}

	public String getFinancingMaturity() {
		return financingMaturity;
	}

	public void setFinancingMaturity(String financingMaturity) {
		this.financingMaturity = financingMaturity;
	}

	public String getFinancingCeiling() {
		return financingCeiling;
	}

	public void setFinancingCeiling(String financingCeiling) {
		this.financingCeiling = financingCeiling;
	}

	public Short getThreeAssetPackage() {
		return threeAssetPackage;
	}

	public void setThreeAssetPackage(Short threeAssetPackage) {
		this.threeAssetPackage = threeAssetPackage;
	}

	public Short getSixAssetPackage() {
		return sixAssetPackage;
	}

	public void setSixAssetPackage(Short sixAssetPackage) {
		this.sixAssetPackage = sixAssetPackage;
	}

	public Short getTwelveAssetPackage() {
		return twelveAssetPackage;
	}

	public void setTwelveAssetPackage(Short twelveAssetPackage) {
		this.twelveAssetPackage = twelveAssetPackage;
	}

	public Short getOtherAssetPackage() {
		return otherAssetPackage;
	}

	public void setOtherAssetPackage(Short otherAssetPackage) {
		this.otherAssetPackage = otherAssetPackage;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEditorTime() {
		return editorTime;
	}

	public void setEditorTime(String editorTime) {
		this.editorTime = editorTime;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	public Integer getEditorId() {
		return editorId;
	}

	public void setEditorId(Integer editorId) {
		this.editorId = editorId;
	}

	@Override
	public String toString() {
		return "OrgPreferCollect [id=" + id + ", organizationId="
				+ organizationId + ", buyerNum=" + buyerNum + ", totalTrade="
				+ totalTrade + ", monthTrade=" + monthTrade + ", threeRate="
				+ threeRate + ", sixRate=" + sixRate + ", twelveRate="
				+ twelveRate + ", otherRate=" + otherRate + ", assetTypeOne="
				+ assetTypeOne + ", govermentTwo=" + govermentTwo
				+ ", listedCompanyTwo=" + listedCompanyTwo
				+ ", financingMaturity=" + financingMaturity
				+ ", financingCeiling=" + financingCeiling
				+ ", threeAssetPackage=" + threeAssetPackage
				+ ", sixAssetPackage=" + sixAssetPackage
				+ ", twelveAssetPackage=" + twelveAssetPackage
				+ ", otherAssetPackage=" + otherAssetPackage + ", creatorId="
				+ creatorId + ", editorId=" + editorId + ", createTime="
				+ createTime + ", editorTime=" + editorTime + "]";
	}

}