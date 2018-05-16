package com.jinhui.model;

import java.math.BigDecimal;
import java.util.Date;

public class SupplyChainAsset {
	private String token;

	private Integer aid;

	private Integer assetType;

	private String name;

	private String shortName;

	private String borrower;

	private BigDecimal scale;

	private Integer tenorType;

	private Integer tenor;

	private Integer creatorId;

	private Integer status;

	private String coreEnterprise;

	private String industryChain;

	private Integer industry;

	private BigDecimal financingCost;

	private Integer financingType;

	private String majorBusiness;

	private String tradeType;

	private String repaymentPeriod;

	private String avgArAge;

	private BigDecimal totalAsset;

	private BigDecimal totalDebt;

	private BigDecimal netAsset;

	private BigDecimal inventory;

	private BigDecimal acReceiveable;

	private BigDecimal prepayment;

	private BigDecimal mainIncome;

	private BigDecimal arRotationRatio;

	private BigDecimal inventoryRation;

	private BigDecimal netProfit;

	private BigDecimal grossProfitRatio;

	private String trasactionHistory;

	private Integer settlementType;

	private Integer logisticType;

	private String lisenceNeeded;

	private String lineGrandingPolicy;

	private Integer rating;

	private Integer creditEnforce;

	private String lisence;

	private String orgInstCode;

	private String taxReg;

	private String article;

	private String captialVer;

	private String feasibility;

	private String auditReport;

	private String yearTransaction;

	private String dataExpierationDate;

	private Date financeDvalid;

	private String otherFile;

	private String financeStatement;

	private Integer isGuarante;

	private Integer guarantorType;

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

	public Date getFinanceDvalid() {
		return financeDvalid;
	}

	public void setFinanceDvalid(Date financeDvalid) {
		this.financeDvalid = financeDvalid;
	}

	public String getDataExpierationDate() {
		return dataExpierationDate;
	}

	public void setDataExpierationDate(String dataExpierationDate) {
		this.dataExpierationDate = dataExpierationDate;
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

	public String getCoreEnterprise() {
		return coreEnterprise;
	}

	public void setCoreEnterprise(String coreEnterprise) {
		this.coreEnterprise = coreEnterprise == null ? null : coreEnterprise
				.trim();
	}

	public String getIndustryChain() {
		return industryChain;
	}

	public void setIndustryChain(String industryChain) {
		this.industryChain = industryChain == null ? null : industryChain
				.trim();
	}

	public Integer getIndustry() {
		return industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
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

	public String getMajorBusiness() {
		return majorBusiness;
	}

	public void setMajorBusiness(String majorBusiness) {
		this.majorBusiness = majorBusiness == null ? null : majorBusiness
				.trim();
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType == null ? null : tradeType.trim();
	}

	public String getRepaymentPeriod() {
		return repaymentPeriod;
	}

	public void setRepaymentPeriod(String repaymentPeriod) {
		this.repaymentPeriod = repaymentPeriod == null ? null : repaymentPeriod
				.trim();
	}

	public String getAvgArAge() {
		return avgArAge;
	}

	public void setAvgArAge(String avgArAge) {
		this.avgArAge = avgArAge == null ? null : avgArAge.trim();
	}

	public BigDecimal getTotalAsset() {
		return totalAsset;
	}

	public void setTotalAsset(BigDecimal totalAsset) {
		this.totalAsset = totalAsset;
	}

	public BigDecimal getTotalDebt() {
		return totalDebt;
	}

	public void setTotalDebt(BigDecimal totalDebt) {
		this.totalDebt = totalDebt;
	}

	public BigDecimal getNetAsset() {
		return netAsset;
	}

	public void setNetAsset(BigDecimal netAsset) {
		this.netAsset = netAsset;
	}

	public BigDecimal getInventory() {
		return inventory;
	}

	public void setInventory(BigDecimal inventory) {
		this.inventory = inventory;
	}

	public BigDecimal getAcReceiveable() {
		return acReceiveable;
	}

	public void setAcReceiveable(BigDecimal acReceiveable) {
		this.acReceiveable = acReceiveable;
	}

	public BigDecimal getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(BigDecimal prepayment) {
		this.prepayment = prepayment;
	}

	public BigDecimal getMainIncome() {
		return mainIncome;
	}

	public void setMainIncome(BigDecimal mainIncome) {
		this.mainIncome = mainIncome;
	}

	public BigDecimal getArRotationRatio() {
		return arRotationRatio;
	}

	public void setArRotationRatio(BigDecimal arRotationRatio) {
		this.arRotationRatio = arRotationRatio;
	}

	public BigDecimal getInventoryRation() {
		return inventoryRation;
	}

	public void setInventoryRation(BigDecimal inventoryRation) {
		this.inventoryRation = inventoryRation;
	}

	public BigDecimal getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(BigDecimal netProfit) {
		this.netProfit = netProfit;
	}

	public BigDecimal getGrossProfitRatio() {
		return grossProfitRatio;
	}

	public void setGrossProfitRatio(BigDecimal grossProfitRatio) {
		this.grossProfitRatio = grossProfitRatio;
	}

	public String getTrasactionHistory() {
		return trasactionHistory;
	}

	public void setTrasactionHistory(String trasactionHistory) {
		this.trasactionHistory = trasactionHistory == null ? null
				: trasactionHistory.trim();
	}

	public Integer getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(Integer settlementType) {
		this.settlementType = settlementType;
	}

	public Integer getLogisticType() {
		return logisticType;
	}

	public void setLogisticType(Integer logisticType) {
		this.logisticType = logisticType;
	}

	public String getLisenceNeeded() {
		return lisenceNeeded;
	}

	public void setLisenceNeeded(String lisenceNeeded) {
		this.lisenceNeeded = lisenceNeeded == null ? null : lisenceNeeded
				.trim();
	}

	public String getLineGrandingPolicy() {
		return lineGrandingPolicy;
	}

	public void setLineGrandingPolicy(String lineGrandingPolicy) {
		this.lineGrandingPolicy = lineGrandingPolicy == null ? null
				: lineGrandingPolicy.trim();
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getCreditEnforce() {
		return creditEnforce;
	}

	public void setCreditEnforce(Integer creditEnforce) {
		this.creditEnforce = creditEnforce;
	}

	public String getLisence() {
		return lisence;
	}

	public void setLisence(String lisence) {
		this.lisence = lisence == null ? null : lisence.trim();
	}

	public String getOrgInstCode() {
		return orgInstCode;
	}

	public void setOrgInstCode(String orgInstCode) {
		this.orgInstCode = orgInstCode == null ? null : orgInstCode.trim();
	}

	public String getTaxReg() {
		return taxReg;
	}

	public void setTaxReg(String taxReg) {
		this.taxReg = taxReg == null ? null : taxReg.trim();
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article == null ? null : article.trim();
	}

	public String getCaptialVer() {
		return captialVer;
	}

	public void setCaptialVer(String captialVer) {
		this.captialVer = captialVer == null ? null : captialVer.trim();
	}

	public String getFeasibility() {
		return feasibility;
	}

	public void setFeasibility(String feasibility) {
		this.feasibility = feasibility == null ? null : feasibility.trim();
	}

	public String getAuditReport() {
		return auditReport;
	}

	public void setAuditReport(String auditReport) {
		this.auditReport = auditReport == null ? null : auditReport.trim();
	}

	public String getYearTransaction() {
		return yearTransaction;
	}

	public void setYearTransaction(String yearTransaction) {
		this.yearTransaction = yearTransaction;
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