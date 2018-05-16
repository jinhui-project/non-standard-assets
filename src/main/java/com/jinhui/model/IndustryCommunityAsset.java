package com.jinhui.model;

import java.math.BigDecimal;
import java.util.Date;

public class IndustryCommunityAsset {
    private Integer id;

    private Integer aid;
    
    private String name;

	private String shortName;
	
	private Integer creatorId;

    private Integer assetType;

    private String borrower;

    private BigDecimal scale;

    private Integer tenorType;

    private Integer tenor;

    private String repaymentType;

    private BigDecimal financingCost;

    private Integer financingType;

    private String financingPurpose;

    private String timingRequirement;

    private Integer holderBackground;

    private BigDecimal capital;

    private Date foundedTime;

    private Integer industry;
    
    private String province;
    
    private String city;

    private String locatedProvince;

	private String locatedCity;

    private String majorBusiness;

    private BigDecimal totalAsset;

    private BigDecimal netAsset;

    private BigDecimal mainIncome;

    private BigDecimal netProfit;

    private BigDecimal maoRatio;

    private Date financingDvalid;

    private Integer rating;

    private Integer forecast;

    private Integer publicDebt;

    private Date matureDate;

    private String bankLine;

    private Integer badSuit;

    private Integer lawSuit;

    private String financeCooporation;

    private Integer isTrust;

    private BigDecimal landValue;

    private BigDecimal projectValue;

    private BigDecimal otherCollateral;

    private BigDecimal receivable;

    private String counterParty;

    private Date repayDate;

    private String pledgeStock;

    private String pledgeRatio;

    private String otherPledge;

    private Integer isGuarante;

    private String guarantor;

    private Integer guarantorType;

    private Integer guarantorFinancierRelation;

    private String guarantorBackground;

    private BigDecimal guarantorRegistMoney;

    private BigDecimal guarantorTotalMoney;

    private BigDecimal guarantorNetMoney;

    private BigDecimal guarantorRevenue;

    private BigDecimal guarantorNetProfit;

    private BigDecimal guarantorGrossRate;

    private Date guarantorSetupDate;

    private Date guarantorDataExpired;

    private Integer guarantorRating;

    private Integer guarantorRatingOutlook;

    private Integer guarantorIsPublish;

    private Date guarantorLastestDate;

    private Integer guarantorBadBreach;

    private Integer guarantorPendingAction;

    private String lisence;

    private String orgInstCode;

    private String taxReg;

    private String leId;

    private String bankCard;

    private String acApproval;

    private String article;

    private String captialVer;

    private String creditReport;

    private String feasibility;

    private String auditReport;

    private String financeStatement;

    private String otherFile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
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

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public BigDecimal getFinancingCost() {
		return financingCost;
	}

	public void setFinancingCost(BigDecimal financingCost) {
		this.financingCost = financingCost;
	}

	public Integer getFinancingType() {
		return financingType;
	}

	public void setFinancingType(Integer financingType) {
		this.financingType = financingType;
	}

	public String getFinancingPurpose() {
		return financingPurpose;
	}

	public void setFinancingPurpose(String financingPurpose) {
		this.financingPurpose = financingPurpose;
	}

	public String getTimingRequirement() {
		return timingRequirement;
	}

	public void setTimingRequirement(String timingRequirement) {
		this.timingRequirement = timingRequirement;
	}

	public Integer getHolderBackground() {
		return holderBackground;
	}

	public void setHolderBackground(Integer holderBackground) {
		this.holderBackground = holderBackground;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public Date getFoundedTime() {
		return foundedTime;
	}

	public void setFoundedTime(Date foundedTime) {
		this.foundedTime = foundedTime;
	}

	public Integer getIndustry() {
		return industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	public String getLocatedProvince() {
		return locatedProvince;
	}

	public void setLocatedProvince(String locatedProvince) {
		this.locatedProvince = locatedProvince;
	}

	public String getLocatedCity() {
		return locatedCity;
	}

	public void setLocatedCity(String locatedCity) {
		this.locatedCity = locatedCity;
	}

	public String getMajorBusiness() {
		return majorBusiness;
	}

	public void setMajorBusiness(String majorBusiness) {
		this.majorBusiness = majorBusiness;
	}

	public BigDecimal getTotalAsset() {
		return totalAsset;
	}

	public void setTotalAsset(BigDecimal totalAsset) {
		this.totalAsset = totalAsset;
	}

	public BigDecimal getNetAsset() {
		return netAsset;
	}

	public void setNetAsset(BigDecimal netAsset) {
		this.netAsset = netAsset;
	}

	public BigDecimal getMainIncome() {
		return mainIncome;
	}

	public void setMainIncome(BigDecimal mainIncome) {
		this.mainIncome = mainIncome;
	}

	public BigDecimal getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(BigDecimal netProfit) {
		this.netProfit = netProfit;
	}

	public BigDecimal getMaoRatio() {
		return maoRatio;
	}

	public void setMaoRatio(BigDecimal maoRatio) {
		this.maoRatio = maoRatio;
	}

	public Date getFinancingDvalid() {
		return financingDvalid;
	}

	public void setFinancingDvalid(Date financingDvalid) {
		this.financingDvalid = financingDvalid;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getForecast() {
		return forecast;
	}

	public void setForecast(Integer forecast) {
		this.forecast = forecast;
	}

	public Integer getPublicDebt() {
		return publicDebt;
	}

	public void setPublicDebt(Integer publicDebt) {
		this.publicDebt = publicDebt;
	}

	public Date getMatureDate() {
		return matureDate;
	}

	public void setMatureDate(Date matureDate) {
		this.matureDate = matureDate;
	}

	public String getBankLine() {
		return bankLine;
	}

	public void setBankLine(String bankLine) {
		this.bankLine = bankLine;
	}

	public Integer getBadSuit() {
		return badSuit;
	}

	public void setBadSuit(Integer badSuit) {
		this.badSuit = badSuit;
	}

	public Integer getLawSuit() {
		return lawSuit;
	}

	public void setLawSuit(Integer lawSuit) {
		this.lawSuit = lawSuit;
	}

	public String getFinanceCooporation() {
		return financeCooporation;
	}

	public void setFinanceCooporation(String financeCooporation) {
		this.financeCooporation = financeCooporation;
	}

	public Integer getIsTrust() {
		return isTrust;
	}

	public void setIsTrust(Integer isTrust) {
		this.isTrust = isTrust;
	}

	public BigDecimal getLandValue() {
		return landValue;
	}

	public void setLandValue(BigDecimal landValue) {
		this.landValue = landValue;
	}

	public BigDecimal getProjectValue() {
		return projectValue;
	}

	public void setProjectValue(BigDecimal projectValue) {
		this.projectValue = projectValue;
	}

	public BigDecimal getOtherCollateral() {
		return otherCollateral;
	}

	public void setOtherCollateral(BigDecimal otherCollateral) {
		this.otherCollateral = otherCollateral;
	}

	public BigDecimal getReceivable() {
		return receivable;
	}

	public void setReceivable(BigDecimal receivable) {
		this.receivable = receivable;
	}

	public String getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(String counterParty) {
		this.counterParty = counterParty;
	}

	public Date getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	public String getPledgeStock() {
		return pledgeStock;
	}

	public void setPledgeStock(String pledgeStock) {
		this.pledgeStock = pledgeStock;
	}

	public String getPledgeRatio() {
		return pledgeRatio;
	}

	public void setPledgeRatio(String pledgeRatio) {
		this.pledgeRatio = pledgeRatio;
	}

	public String getOtherPledge() {
		return otherPledge;
	}

	public void setOtherPledge(String otherPledge) {
		this.otherPledge = otherPledge;
	}

	public Integer getIsGuarante() {
		return isGuarante;
	}

	public void setIsGuarante(Integer isGuarante) {
		this.isGuarante = isGuarante;
	}

	public String getGuarantor() {
		return guarantor;
	}

	public void setGuarantor(String guarantor) {
		this.guarantor = guarantor;
	}

	public Integer getGuarantorType() {
		return guarantorType;
	}

	public void setGuarantorType(Integer guarantorType) {
		this.guarantorType = guarantorType;
	}

	public Integer getGuarantorFinancierRelation() {
		return guarantorFinancierRelation;
	}

	public void setGuarantorFinancierRelation(Integer guarantorFinancierRelation) {
		this.guarantorFinancierRelation = guarantorFinancierRelation;
	}

	public String getGuarantorBackground() {
		return guarantorBackground;
	}

	public void setGuarantorBackground(String guarantorBackground) {
		this.guarantorBackground = guarantorBackground;
	}

	public BigDecimal getGuarantorRegistMoney() {
		return guarantorRegistMoney;
	}

	public void setGuarantorRegistMoney(BigDecimal guarantorRegistMoney) {
		this.guarantorRegistMoney = guarantorRegistMoney;
	}

	public BigDecimal getGuarantorTotalMoney() {
		return guarantorTotalMoney;
	}

	public void setGuarantorTotalMoney(BigDecimal guarantorTotalMoney) {
		this.guarantorTotalMoney = guarantorTotalMoney;
	}

	public BigDecimal getGuarantorNetMoney() {
		return guarantorNetMoney;
	}

	public void setGuarantorNetMoney(BigDecimal guarantorNetMoney) {
		this.guarantorNetMoney = guarantorNetMoney;
	}

	public BigDecimal getGuarantorRevenue() {
		return guarantorRevenue;
	}

	public void setGuarantorRevenue(BigDecimal guarantorRevenue) {
		this.guarantorRevenue = guarantorRevenue;
	}

	public BigDecimal getGuarantorNetProfit() {
		return guarantorNetProfit;
	}

	public void setGuarantorNetProfit(BigDecimal guarantorNetProfit) {
		this.guarantorNetProfit = guarantorNetProfit;
	}

	public BigDecimal getGuarantorGrossRate() {
		return guarantorGrossRate;
	}

	public void setGuarantorGrossRate(BigDecimal guarantorGrossRate) {
		this.guarantorGrossRate = guarantorGrossRate;
	}

	public Date getGuarantorSetupDate() {
		return guarantorSetupDate;
	}

	public void setGuarantorSetupDate(Date guarantorSetupDate) {
		this.guarantorSetupDate = guarantorSetupDate;
	}

	public Date getGuarantorDataExpired() {
		return guarantorDataExpired;
	}

	public void setGuarantorDataExpired(Date guarantorDataExpired) {
		this.guarantorDataExpired = guarantorDataExpired;
	}

	public Integer getGuarantorRating() {
		return guarantorRating;
	}

	public void setGuarantorRating(Integer guarantorRating) {
		this.guarantorRating = guarantorRating;
	}

	public Integer getGuarantorRatingOutlook() {
		return guarantorRatingOutlook;
	}

	public void setGuarantorRatingOutlook(Integer guarantorRatingOutlook) {
		this.guarantorRatingOutlook = guarantorRatingOutlook;
	}

	public Integer getGuarantorIsPublish() {
		return guarantorIsPublish;
	}

	public void setGuarantorIsPublish(Integer guarantorIsPublish) {
		this.guarantorIsPublish = guarantorIsPublish;
	}

	public Date getGuarantorLastestDate() {
		return guarantorLastestDate;
	}

	public void setGuarantorLastestDate(Date guarantorLastestDate) {
		this.guarantorLastestDate = guarantorLastestDate;
	}

	public Integer getGuarantorBadBreach() {
		return guarantorBadBreach;
	}

	public void setGuarantorBadBreach(Integer guarantorBadBreach) {
		this.guarantorBadBreach = guarantorBadBreach;
	}

	public Integer getGuarantorPendingAction() {
		return guarantorPendingAction;
	}

	public void setGuarantorPendingAction(Integer guarantorPendingAction) {
		this.guarantorPendingAction = guarantorPendingAction;
	}

	public String getLisence() {
		return lisence;
	}

	public void setLisence(String lisence) {
		this.lisence = lisence;
	}

	public String getOrgInstCode() {
		return orgInstCode;
	}

	public void setOrgInstCode(String orgInstCode) {
		this.orgInstCode = orgInstCode;
	}

	public String getTaxReg() {
		return taxReg;
	}

	public void setTaxReg(String taxReg) {
		this.taxReg = taxReg;
	}

	public String getLeId() {
		return leId;
	}

	public void setLeId(String leId) {
		this.leId = leId;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getAcApproval() {
		return acApproval;
	}

	public void setAcApproval(String acApproval) {
		this.acApproval = acApproval;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getCaptialVer() {
		return captialVer;
	}

	public void setCaptialVer(String captialVer) {
		this.captialVer = captialVer;
	}

	public String getCreditReport() {
		return creditReport;
	}

	public void setCreditReport(String creditReport) {
		this.creditReport = creditReport;
	}

	public String getFeasibility() {
		return feasibility;
	}

	public void setFeasibility(String feasibility) {
		this.feasibility = feasibility;
	}

	public String getAuditReport() {
		return auditReport;
	}

	public void setAuditReport(String auditReport) {
		this.auditReport = auditReport;
	}

	public String getFinanceStatement() {
		return financeStatement;
	}

	public void setFinanceStatement(String financeStatement) {
		this.financeStatement = financeStatement;
	}

	public String getOtherFile() {
		return otherFile;
	}

	public void setOtherFile(String otherFile) {
		this.otherFile = otherFile;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	

}