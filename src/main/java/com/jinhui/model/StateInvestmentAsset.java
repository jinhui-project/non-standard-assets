package com.jinhui.model;

import java.math.BigDecimal;
import java.util.Date;




public class StateInvestmentAsset {
	
	private String token;
	
    private Integer id;

    private Integer aid;
    
    private String name;

    private String shortName;

    private Integer assetType;

    private String borrower;

    private BigDecimal scale;

    private Integer tenorType;
    
    private Integer status;

    private Integer tenor;

    private String repaymentType;

    private BigDecimal financingCost;

    private Integer financingType;

    private String financingPurpose;

    private String timingRequirement;

    private String companyName;

    private Integer holderBackground;

    private BigDecimal capital;

    private Date foundedTime;

    private String majorBusiness;

    private BigDecimal totalAsset;

    private BigDecimal netAsset;

    private BigDecimal mainIncome;

    private BigDecimal allowanceIncome;

    private BigDecimal netProfit;

    private BigDecimal alRatio;

    private Integer rating;

    private Integer forecast;

    private Integer publicDebt;

    private Date matureDate;
    
    private String completeDate;

    private String bankLine;

    private Integer badSuit;

    private Integer lawSuit;

    private String financeCooporation;

    private Integer projectType;

    private String projectCompliance;
    
    private String  otherFile;

  

    private String selfFunded;

    private String repayArrangement;

    private String otherFinancing;

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
    
    private Integer guarantorType;

    private String guarantor;

    private String guarantorBackground;

    private Integer guarantorRating;

    private Integer govDecision;

    private Integer govAgreement;

    private Integer financeArrangement;

    private String province;
    
    private String city;

    private Integer admGrade;

    private BigDecimal gdp;

    private String mainIndustry;

    private String bigThreeRatio;

    private Integer population;

    private BigDecimal govTotalIncome;

    private BigDecimal generalIncome;

    private BigDecimal transferIncome;

    private BigDecimal govFundIncome;

    private BigDecimal govDebt;

    private BigDecimal govGuarantee;

    private BigDecimal liabilitiesRatio;

    private BigDecimal debtRation;

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
    
    private String dataExpierationDate;
    
    private String dataGoveExpirationDate;
    
    private Integer belongGid;

    private Integer creatorId;

    private String createTime;

    private Integer editorId;

    private String editTime;

    private Integer auditorId;

    private String auditTime;
    
    private Date financeDvalid; // '数据有效截止日',


    private Date financialDvalid;// '政府数据有效截止日',

   

	public Integer getBelongGid() {
		return belongGid;
	}

	public void setBelongGid(Integer belongGid) {
		this.belongGid = belongGid;
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

	public Integer getEditorId() {
		return editorId;
	}

	public void setEditorId(Integer editorId) {
		this.editorId = editorId;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getDataExpierationDate() {
		return dataExpierationDate;
	}

	public void setDataExpierationDate(String dataExpierationDate) {
		this.dataExpierationDate = dataExpierationDate;
	}

	public String getDataGoveExpirationDate() {
		return dataGoveExpirationDate;
	}

	public void setDataGoveExpirationDate(String dataGoveExpirationDate) {
		this.dataGoveExpirationDate = dataGoveExpirationDate;
	}

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
        this.financingPurpose = financingPurpose == null ? null : financingPurpose.trim();
    }

    public String getTimingRequirement() {
        return timingRequirement;
    }

    public void setTimingRequirement(String timingRequirement) {
        this.timingRequirement = timingRequirement == null ? null : timingRequirement.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
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

   

    public String getMajorBusiness() {
        return majorBusiness;
    }

    public void setMajorBusiness(String majorBusiness) {
        this.majorBusiness = majorBusiness == null ? null : majorBusiness.trim();
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

    public BigDecimal getAllowanceIncome() {
        return allowanceIncome;
    }

    public void setAllowanceIncome(BigDecimal allowanceIncome) {
        this.allowanceIncome = allowanceIncome;
    }

    public BigDecimal getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(BigDecimal netProfit) {
        this.netProfit = netProfit;
    }

    public BigDecimal getAlRatio() {
        return alRatio;
    }

    public void setAlRatio(BigDecimal alRatio) {
        this.alRatio = alRatio;
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

	

	public String getBankLine() {
        return bankLine;
    }

    public void setBankLine(String bankLine) {
        this.bankLine = bankLine == null ? null : bankLine.trim();
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

	public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    public String getProjectCompliance() {
        return projectCompliance;
    }

    public void setProjectCompliance(String projectCompliance) {
        this.projectCompliance = projectCompliance == null ? null : projectCompliance.trim();
    }

   

    public String getSelfFunded() {
        return selfFunded;
    }

    public void setSelfFunded(String selfFunded) {
        this.selfFunded = selfFunded == null ? null : selfFunded.trim();
    }

    public String getRepayArrangement() {
        return repayArrangement;
    }

    public void setRepayArrangement(String repayArrangement) {
        this.repayArrangement = repayArrangement == null ? null : repayArrangement.trim();
    }

    public String getOtherFinancing() {
        return otherFinancing;
    }

    public void setOtherFinancing(String otherFinancing) {
        this.otherFinancing = otherFinancing == null ? null : otherFinancing.trim();
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
        this.counterParty = counterParty == null ? null : counterParty.trim();
    }

  

  

	public Date getFoundedTime() {
		return foundedTime;
	}

	public void setFoundedTime(Date foundedTime) {
		this.foundedTime = foundedTime;
	}

	public Date getMatureDate() {
		return matureDate;
	}

	public void setMatureDate(Date matureDate) {
		this.matureDate = matureDate;
	}

	public Date getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	public Date getFinanceDvalid() {
		return financeDvalid;
	}

	public void setFinanceDvalid(Date financeDvalid) {
		this.financeDvalid = financeDvalid;
	}

	public Date getFinancialDvalid() {
		return financialDvalid;
	}

	public void setFinancialDvalid(Date financialDvalid) {
		this.financialDvalid = financialDvalid;
	}

	public String getPledgeStock() {
        return pledgeStock;
    }

    public void setPledgeStock(String pledgeStock) {
        this.pledgeStock = pledgeStock == null ? null : pledgeStock.trim();
    }

    public String getPledgeRatio() {
        return pledgeRatio;
    }

    public void setPledgeRatio(String pledgeRatio) {
        this.pledgeRatio = pledgeRatio == null ? null : pledgeRatio.trim();
    }

    public String getOtherPledge() {
        return otherPledge;
    }

    public void setOtherPledge(String otherPledge) {
        this.otherPledge = otherPledge == null ? null : otherPledge.trim();
    }

    public String getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(String guarantor) {
        this.guarantor = guarantor == null ? null : guarantor.trim();
    }

    public String getGuarantorBackground() {
        return guarantorBackground;
    }

    public void setGuarantorBackground(String guarantorBackground) {
        this.guarantorBackground = guarantorBackground == null ? null : guarantorBackground.trim();
    }

    public Integer getGuarantorRating() {
        return guarantorRating;
    }

    public void setGuarantorRating(Integer guarantorRating) {
        this.guarantorRating = guarantorRating;
    }

  

   

	public Integer getGovDecision() {
		return govDecision;
	}

	public void setGovDecision(Integer govDecision) {
		this.govDecision = govDecision;
	}

	public Integer getGovAgreement() {
		return govAgreement;
	}

	public void setGovAgreement(Integer govAgreement) {
		this.govAgreement = govAgreement;
	}

	public Integer getFinanceArrangement() {
		return financeArrangement;
	}

	public void setFinanceArrangement(Integer financeArrangement) {
		this.financeArrangement = financeArrangement;
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

	public Integer getAdmGrade() {
		return admGrade;
	}

	public void setAdmGrade(Integer admGrade) {
		this.admGrade = admGrade;
	}

	public BigDecimal getGdp() {
        return gdp;
    }

    public void setGdp(BigDecimal gdp) {
        this.gdp = gdp;
    }

    public String getMainIndustry() {
        return mainIndustry;
    }

    public void setMainIndustry(String mainIndustry) {
        this.mainIndustry = mainIndustry == null ? null : mainIndustry.trim();
    }

    public String getBigThreeRatio() {
        return bigThreeRatio;
    }

    public void setBigThreeRatio(String bigThreeRatio) {
        this.bigThreeRatio = bigThreeRatio == null ? null : bigThreeRatio.trim();
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public BigDecimal getGovTotalIncome() {
        return govTotalIncome;
    }

    public void setGovTotalIncome(BigDecimal govTotalIncome) {
        this.govTotalIncome = govTotalIncome;
    }

    public BigDecimal getGeneralIncome() {
        return generalIncome;
    }

    public void setGeneralIncome(BigDecimal generalIncome) {
        this.generalIncome = generalIncome;
    }

    public BigDecimal getTransferIncome() {
        return transferIncome;
    }

    public void setTransferIncome(BigDecimal transferIncome) {
        this.transferIncome = transferIncome;
    }

    public BigDecimal getGovFundIncome() {
        return govFundIncome;
    }

    public void setGovFundIncome(BigDecimal govFundIncome) {
        this.govFundIncome = govFundIncome;
    }

    public BigDecimal getGovDebt() {
        return govDebt;
    }

    public void setGovDebt(BigDecimal govDebt) {
        this.govDebt = govDebt;
    }

    public BigDecimal getGovGuarantee() {
        return govGuarantee;
    }

    public void setGovGuarantee(BigDecimal govGuarantee) {
        this.govGuarantee = govGuarantee;
    }

    public BigDecimal getLiabilitiesRatio() {
        return liabilitiesRatio;
    }

    public void setLiabilitiesRatio(BigDecimal liabilitiesRatio) {
        this.liabilitiesRatio = liabilitiesRatio;
    }

    public BigDecimal getDebtRation() {
        return debtRation;
    }

    public void setDebtRation(BigDecimal debtRation) {
        this.debtRation = debtRation;
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

    public String getLeId() {
        return leId;
    }

    public void setLeId(String leId) {
        this.leId = leId == null ? null : leId.trim();
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard == null ? null : bankCard.trim();
    }

    public String getAcApproval() {
        return acApproval;
    }

    public void setAcApproval(String acApproval) {
        this.acApproval = acApproval == null ? null : acApproval.trim();
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

    public String getCreditReport() {
        return creditReport;
    }

    public void setCreditReport(String creditReport) {
        this.creditReport = creditReport == null ? null : creditReport.trim();
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

    public String getFinanceStatement() {
        return financeStatement;
    }

    public void setFinanceStatement(String financeStatement) {
        this.financeStatement = financeStatement == null ? null : financeStatement.trim();
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
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