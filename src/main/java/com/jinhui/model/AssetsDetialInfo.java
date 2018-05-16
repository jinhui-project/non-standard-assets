package com.jinhui.model;

import java.math.BigDecimal;
import java.util.Date;



public class AssetsDetialInfo {
	
	private String token;

    private Integer id;
    
    private Integer aid;

    private String name;

    private String shortName;

    private Integer salesModel;

    private Integer assetType;
    
    private String repaymentType;

    private String borrower;
    
    private String issueDate;

    private Integer tenorType;

    private Integer tenor;

    private BigDecimal scale;

    private BigDecimal financingCost;
    
    private BigDecimal financingCostStart;
    
    private BigDecimal financingCostEnd;
    
    private Integer financingType;
    
    private String financingPurpose;

	private Integer recordGid;

    private Integer wfType;

    private Integer wfId;

    private Integer wfStatus;

    private String materials;

    private String dueReports;

    private Integer passResult;

    private String passContent;

    private Integer belongGid;

    private Integer creatorId;

    private String createTime;

    private Integer editorId;

    private String editTime;

    private Integer auditorId;

    private String auditTime;

    private Integer publisherId;

    private String publishTime;

    private Integer confirmerId;

    private String confirmTime;

    private Integer brokerId;

    private String brokerTime;

    private Integer refereeId;

    private String refereeTime;

    private Integer status;
    
    private String timingRequirement;
    
    private String companyName;
    
    private Integer holderBackground;
    
    private BigDecimal capital;

    private String foundedTime;
    
    private String majorBusiness;

    private BigDecimal totalAsset;

    private BigDecimal netAsset;

    private BigDecimal mainIncome;
    
    private BigDecimal allowanceIncome;

    private BigDecimal netProfit;
    
    //毛利率
    private BigDecimal maoRatio;

    private BigDecimal alRatio;
    
    //财务状况数据有效截止日
    private String financingDvalid;
    
    private Integer rating;

    private Integer forecast;

    private Integer publicDebt;
    
    private String matureDate;

    private String bankLine;

    private Integer badSuit;

    private Integer lawSuit;
    
    private String financeCooporation;
    
    private Integer projectType;

    private String projectCompliance;

    private String completeDate;
    
    private String selfFunded;

    private String repayArrangement;

    private String otherFinancing;

    private BigDecimal landValue;

    private BigDecimal projectValue;

    private BigDecimal otherCollateral;
    
    private BigDecimal receivable;

    private String counterParty;

    private String repayDate;

    private String pledgeStock;

    private String pledgeRatio;

    private String otherPledge;

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
	
	/** 是否回购 */
	private Integer repurchase;
	/** 交易结构描述 */
	private String tradingStructure;	
	/** 相关协议 */
	private String relatedAgreement;
	/** 收益权转让项目名称 */
	private String projectName;
	
	private Integer ipoRelation;

	private String listedCode;	   

	private String locatedProvince;
	
	private String locatedCity;

	private String businessDesc;

	
	private String coreEnterprise;

    private String industryChain;

    private Integer industry;
    
    private String tradeType;

    private String repaymentPeriod;

    private String avgArAge; 

    private BigDecimal totalDebt;


    private BigDecimal inventory;

    private BigDecimal acReceiveable;

    private BigDecimal prepayment;

    private BigDecimal arRotationRatio;

    private BigDecimal inventoryRation;


    private BigDecimal grossProfitRatio;

    private String trasactionHistory;

    private Integer settlementType;

    private Integer logisticType;

    private String lisenceNeeded;

    private String lineGrandingPolicy;


    private Integer creditEnforce;


    private String yearTransaction;
    
    private String issueString;
    
    private String dataExpierationDate;
    
    private String dataGoveExpirationDate;
    
    private String financeDvalid; // '数据有效截止日',公共的


    private String financialDvalid;// '数据有效截止日',
    
    
    private Integer isTrust;
    
    private Integer isGuarante;
    
    private Integer guarantorType;
    
    private Integer guarantorFinancierRelation;
    
    private BigDecimal guarantorRegistMoney;
    
    private BigDecimal guarantorTotalMoney;
    
    private BigDecimal guarantorNetMoney;
    
    private BigDecimal guarantorRevenue;
    
    private BigDecimal guarantorNetProfit;
    
    private BigDecimal guarantorGrossRate;
    
    private String guarantorSetupDate;
    
    private String guarantorDataExpired;
    
    private Integer guarantorRatingOutlook;
    
    private Integer guarantorIsPublish;
    
    private String guarantorLastestDate;
    
    private Integer guarantorBadBreach;
    
    private Integer guarantorPendingAction;
    
	public String getFoundedTime() {
		return foundedTime;
	}

	public void setFoundedTime(String foundedTime) {
		this.foundedTime = foundedTime;
	}

	public String getMatureDate() {
		return matureDate;
	}

	public void setMatureDate(String matureDate) {
		this.matureDate = matureDate;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	public String getFinanceDvalid() {
		return financeDvalid;
	}

	public void setFinanceDvalid(String financeDvalid) {
		this.financeDvalid = financeDvalid;
	}

	public String getFinancialDvalid() {
		return financialDvalid;
	}

	public void setFinancialDvalid(String financialDvalid) {
		this.financialDvalid = financialDvalid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public Integer getSalesModel() {
        return salesModel;
    }

    public void setSalesModel(Integer salesModel) {
        this.salesModel = salesModel;
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

    public BigDecimal getScale() {
        return scale;
    }

    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }

    public BigDecimal getFinancingCost() {
        return financingCost;
    }

    public void setFinancingCost(BigDecimal financingCost) {
        this.financingCost = financingCost;
    }

    public Integer getRecordGid() {
        return recordGid;
    }

    public void setRecordGid(Integer recordGid) {
        this.recordGid = recordGid;
    }

    public Integer getWfType() {
        return wfType;
    }

    public void setWfType(Integer wfType) {
        this.wfType = wfType;
    }

    public Integer getWfId() {
        return wfId;
    }

    public void setWfId(Integer wfId) {
        this.wfId = wfId;
    }

    public Integer getWfStatus() {
        return wfStatus;
    }

    public void setWfStatus(Integer wfStatus) {
        this.wfStatus = wfStatus;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials == null ? null : materials.trim();
    }

    public String getDueReports() {
        return dueReports;
    }

    public void setDueReports(String dueReports) {
        this.dueReports = dueReports == null ? null : dueReports.trim();
    }

    public Integer getPassResult() {
        return passResult;
    }

    public void setPassResult(Integer passResult) {
        this.passResult = passResult;
    }

    public String getPassContent() {
        return passContent;
    }

    public void setPassContent(String passContent) {
        this.passContent = passContent == null ? null : passContent.trim();
    }

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

  

    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

  

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

  

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

  

    public Integer getConfirmerId() {
        return confirmerId;
    }

    public void setConfirmerId(Integer confirmerId) {
        this.confirmerId = confirmerId;
    }

  

    public Integer getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Integer brokerId) {
        this.brokerId = brokerId;
    }

 

    public Integer getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(Integer refereeId) {
        this.refereeId = refereeId;
    }

  

    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getBrokerTime() {
		return brokerTime;
	}

	public void setBrokerTime(String brokerTime) {
		this.brokerTime = brokerTime;
	}

	public String getRefereeTime() {
		return refereeTime;
	}

	public void setRefereeTime(String refereeTime) {
		this.refereeTime = refereeTime;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
		this.projectCompliance = projectCompliance;
	}

	

	public String getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	public String getSelfFunded() {
		return selfFunded;
	}

	public void setSelfFunded(String selfFunded) {
		this.selfFunded = selfFunded;
	}

	public String getRepayArrangement() {
		return repayArrangement;
	}

	public void setRepayArrangement(String repayArrangement) {
		this.repayArrangement = repayArrangement;
	}

	public String getOtherFinancing() {
		return otherFinancing;
	}

	public void setOtherFinancing(String otherFinancing) {
		this.otherFinancing = otherFinancing;
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

	public String getGuarantor() {
		return guarantor;
	}

	public void setGuarantor(String guarantor) {
		this.guarantor = guarantor;
	}

	public String getGuarantorBackground() {
		return guarantorBackground;
	}

	public void setGuarantorBackground(String guarantorBackground) {
		this.guarantorBackground = guarantorBackground;
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
		this.mainIndustry = mainIndustry;
	}

	public String getBigThreeRatio() {
		return bigThreeRatio;
	}

	public void setBigThreeRatio(String bigThreeRatio) {
		this.bigThreeRatio = bigThreeRatio;
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

	public String getAssetMgr() {
		return assetMgr;
	}

	public void setAssetMgr(String assetMgr) {
		this.assetMgr = assetMgr;
	}

	public Integer getSetTrench() {
		return setTrench;
	}

	public void setSetTrench(Integer setTrench) {
		this.setTrench = setTrench;
	}

	public String getSeniorPercent() {
		return seniorPercent;
	}

	public void setSeniorPercent(String seniorPercent) {
		this.seniorPercent = seniorPercent;
	}

	public Integer getSeniorRating() {
		return seniorRating;
	}

	public void setSeniorRating(Integer seniorRating) {
		this.seniorRating = seniorRating;
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

	public String getAbsRatingReport() {
		return absRatingReport;
	}

	public void setAbsRatingReport(String absRatingReport) {
		this.absRatingReport = absRatingReport;
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
		this.tradingStructure = tradingStructure;
	}

	public String getRelatedAgreement() {
		return relatedAgreement;
	}

	public void setRelatedAgreement(String relatedAgreement) {
		this.relatedAgreement = relatedAgreement;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getIpoRelation() {
		return ipoRelation;
	}

	public void setIpoRelation(Integer ipoRelation) {
		this.ipoRelation = ipoRelation;
	}

	public String getListedCode() {
		return listedCode;
	}

	public void setListedCode(String listedCode) {
		this.listedCode = listedCode;
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

	public String getBusinessDesc() {
		return businessDesc;
	}

	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}

	public BigDecimal getGrossProfitRatio() {
		return grossProfitRatio;
	}

	public void setGrossProfitRatio(BigDecimal grossProfitRatio) {
		this.grossProfitRatio = grossProfitRatio;
	}

	public String getCoreEnterprise() {
		return coreEnterprise;
	}

	public void setCoreEnterprise(String coreEnterprise) {
		this.coreEnterprise = coreEnterprise;
	}

	public String getIndustryChain() {
		return industryChain;
	}

	public void setIndustryChain(String industryChain) {
		this.industryChain = industryChain;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getRepaymentPeriod() {
		return repaymentPeriod;
	}

	public void setRepaymentPeriod(String repaymentPeriod) {
		this.repaymentPeriod = repaymentPeriod;
	}

	public String getAvgArAge() {
		return avgArAge;
	}

	public void setAvgArAge(String avgArAge) {
		this.avgArAge = avgArAge;
	}

	public BigDecimal getTotalDebt() {
		return totalDebt;
	}

	public void setTotalDebt(BigDecimal totalDebt) {
		this.totalDebt = totalDebt;
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

	public String getTrasactionHistory() {
		return trasactionHistory;
	}

	public void setTrasactionHistory(String trasactionHistory) {
		this.trasactionHistory = trasactionHistory;
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
		this.lisenceNeeded = lisenceNeeded;
	}

	public String getLineGrandingPolicy() {
		return lineGrandingPolicy;
	}

	public void setLineGrandingPolicy(String lineGrandingPolicy) {
		this.lineGrandingPolicy = lineGrandingPolicy;
	}

	public Integer getCreditEnforce() {
		return creditEnforce;
	}

	public void setCreditEnforce(Integer creditEnforce) {
		this.creditEnforce = creditEnforce;
	}

	public String getYearTransaction() {
		return yearTransaction;
	}

	public void setYearTransaction(String yearTransaction) {
		this.yearTransaction = yearTransaction;
	}

	public String getIssueString() {
		return issueString;
	}

	public void setIssueString(String issueString) {
		this.issueString = issueString;
	}

	public BigDecimal getFinancingCostStart() {
		return financingCostStart;
	}

	public void setFinancingCostStart(BigDecimal financingCostStart) {
		this.financingCostStart = financingCostStart;
	}

	public BigDecimal getFinancingCostEnd() {
		return financingCostEnd;
	}

	public void setFinancingCostEnd(BigDecimal financingCostEnd) {
		this.financingCostEnd = financingCostEnd;
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

	

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
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

	public Integer getIsTrust() {
		return isTrust;
	}

	public void setIsTrust(Integer isTrust) {
		this.isTrust = isTrust;
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

	public Integer getGuarantorFinancierRelation() {
		return guarantorFinancierRelation;
	}

	public void setGuarantorFinancierRelation(Integer guarantorFinancierRelation) {
		this.guarantorFinancierRelation = guarantorFinancierRelation;
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

	public String getGuarantorSetupDate() {
		return guarantorSetupDate;
	}

	public void setGuarantorSetupDate(String guarantorSetupDate) {
		this.guarantorSetupDate = guarantorSetupDate;
	}

	public String getGuarantorDataExpired() {
		return guarantorDataExpired;
	}

	public void setGuarantorDataExpired(String guarantorDataExpired) {
		this.guarantorDataExpired = guarantorDataExpired;
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

	public String getGuarantorLastestDate() {
		return guarantorLastestDate;
	}

	public void setGuarantorLastestDate(String guarantorLastestDate) {
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

	public BigDecimal getMaoRatio() {
		return maoRatio;
	}

	public void setMaoRatio(BigDecimal maoRatio) {
		this.maoRatio = maoRatio;
	}

	public String getFinancingDvalid() {
		return financingDvalid;
	}

	public void setFinancingDvalid(String financingDvalid) {
		this.financingDvalid = financingDvalid;
	}

}
