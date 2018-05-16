package com.jinhui.model;

import java.math.BigDecimal;

public class Assets {
	private String token;
	
    private Integer id;
    
    private Integer assetId;
    
    private Integer gid;

    private String name;

    private String shortName;
    
    private String recordName;
    
    private String stepName;

    private Integer salesModel;

    private Integer assetType;

    private String borrower;

    private Integer tenorType;
    
    private Integer scaleType;
    
    private Integer stepId;
    
    private BigDecimal scaleStart;
    
    private BigDecimal scaleEnd;
    
    private Integer tenorDay;
   
    private Integer tenorRoleType;
    
    private Integer tenorDayStart;
    
    private Integer tenorDayEnd;

    private Integer tenor;

    private BigDecimal scale;

    private BigDecimal financingCost;

    private Integer recordGid;

    private Integer wfType;

    private Integer wfId;

    private Integer wfStatus;

    private String materials;

    private String dueReports;

    private Integer passResult;

    private String passContent;

    private Integer belongGid;
    
    private Integer  orgId;

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
    
    private Integer isHot;
    
    private BigDecimal financingCostStart;
    
    private BigDecimal financingCostEnd;
    
    private Integer feedType;
    
    

    public Integer getFeedType() {
		return feedType;
	}

	public void setFeedType(Integer feedType) {
		this.feedType = feedType;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
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

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public Integer getTenorDay() {
		return tenorDay;
	}

	public void setTenorDay(Integer tenorDay) {
		this.tenorDay = tenorDay;
	}

	public Integer getTenorDayStart() {
		return tenorDayStart;
	}

	public void setTenorDayStart(Integer tenorDayStart) {
		this.tenorDayStart = tenorDayStart;
	}

	public Integer getTenorDayEnd() {
		return tenorDayEnd;
	}

	public void setTenorDayEnd(Integer tenorDayEnd) {
		this.tenorDayEnd = tenorDayEnd;
	}

	public Integer getTenorRoleType() {
		return tenorRoleType;
	}

	public void setTenorRoleType(Integer tenorRoleType) {
		this.tenorRoleType = tenorRoleType;
	}

	public Integer getScaleType() {
		return scaleType;
	}

	public void setScaleType(Integer scaleType) {
		this.scaleType = scaleType;
	}

	public BigDecimal getScaleStart() {
		return scaleStart;
	}

	public void setScaleStart(BigDecimal scaleStart) {
		this.scaleStart = scaleStart;
	}

	public BigDecimal getScaleEnd() {
		return scaleEnd;
	}

	public void setScaleEnd(BigDecimal scaleEnd) {
		this.scaleEnd = scaleEnd;
	}

	public Integer getStepId() {
		return stepId;
	}

	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

}