package com.jinhui.model;

import java.math.BigDecimal;

public class SaleFeedbacks {
	
	private String token;
	
    private Integer id;

    private Integer assetId;

    private String contents;

    private String feedbackerId;
    
    private String feedbackerName;

    private Integer feedbackTime;

    private String gid;
    
    private String orgId;

    private String orgName;

//    private Boolean saleType;
    
    private Integer saleType;

    private Boolean isUnderwriting;

    private BigDecimal packPrice;

    private BigDecimal numDistribution;

    private String commission;

//    private Boolean selfFunk;
    
    private Integer  selfFunk;

    private String contactInfo;

    private String presented;

    private String createAt;

    private String updateAt;

    private String feedType;
    
    private String shortName;
    
    private Integer assetType;
    
    private String borrower;
    
    private BigDecimal scale;
    
    private BigDecimal financingCost;
    
    private Integer status;
    
    

    public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	public BigDecimal getFinancingCost() {
		return financingCost;
	}

	public void setFinancingCost(BigDecimal financingCost) {
		this.financingCost = financingCost;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

    public String getFeedbackerId() {
		return feedbackerId;
	}

	public void setFeedbackerId(String feedbackerId) {
		this.feedbackerId = feedbackerId;
	}

	public Integer getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Integer feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Boolean getIsUnderwriting() {
        return isUnderwriting;
    }

    public void setIsUnderwriting(Boolean isUnderwriting) {
        this.isUnderwriting = isUnderwriting;
    }

    public BigDecimal getPackPrice() {
        return packPrice;
    }

    public void setPackPrice(BigDecimal packPrice) {
        this.packPrice = packPrice;
    }

    public BigDecimal getNumDistribution() {
        return numDistribution;
    }

    public void setNumDistribution(BigDecimal numDistribution) {
        this.numDistribution = numDistribution;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission == null ? null : commission.trim();
    }

//    public Boolean getSelfFunk() {
//        return selfFunk;
//    }
//
//    public void setSelfFunk(Boolean selfFunk) {
//        this.selfFunk = selfFunk;
//    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo == null ? null : contactInfo.trim();
    }

    public String getPresented() {
        return presented;
    }

    public void setPresented(String presented) {
        this.presented = presented == null ? null : presented.trim();
    }

    public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}


	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public Integer getSaleType() {
		return saleType;
	}

	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	public Integer getSelfFunk() {
		return selfFunk;
	}

	public void setSelfFunk(Integer selfFunk) {
		this.selfFunk = selfFunk;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getFeedbackerName() {
		return feedbackerName;
	}

	public void setFeedbackerName(String feedbackerName) {
		this.feedbackerName = feedbackerName;
	}
	
}