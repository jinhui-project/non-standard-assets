package com.jinhui.model;

import java.math.BigDecimal;

/**
 * 产品实体类
 * @author wsc
 *
 */
public class Products {
	
	private String token;
	
    private Integer id;

    private Integer assetId;
    
    private String assetShortName;

    private String code;

    private String name;
    
    private String publishName;

    private String shortName;

    private String productType;

    private Integer riskLevel;

    private Integer isProtected;

    private Long scale;

    private Long surplusScale;

    private Integer term;

    private Integer termType;

    private Integer annualizedDay;

    private Integer beginAmount;

    private Integer increaseAmount;

    private Integer buyerLimits;

    private Integer status;
    
    private Integer isHot;
    
    private Integer salesModel;

    private String beginSaleDate;

    private String endSaleDate;

    private String beginIncomeDate;

    private String expiredDate;

    private Integer incomeType;

    private BigDecimal fixedRatio;

    private BigDecimal floatRatio;

    private Integer incomeWay;

    private String description;

    private Integer recordKeeping;
    
    private String recordKeepingName;
    
    private String recordKeepingShortName;

    private Integer creatorId;

    private String createTime;

    private Integer editorId;

    private String editorTime;

    private Integer isPublished;

    private Integer publisherId;

    private String publishTime;

    private Integer brokerId;

    private String brokerTime;

    private String recordTime;
    
    private String serverTime;
    
    private String scalePro;
    
    private Integer isCollect;
    
    private String gid;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    
    public String getPublishName() {
		return publishName;
	}

	public void setPublishName(String publishName) {
		this.publishName = publishName;
	}

	public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Integer getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(Integer isProtected) {
        this.isProtected = isProtected;
    }

    public Long getScale() {
        return scale;
    }

    public void setScale(Long scale) {
        this.scale = scale;
    }

    public Long getSurplusScale() {
        return surplusScale;
    }

    public void setSurplusScale(Long surplusScale) {
        this.surplusScale = surplusScale;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getTermType() {
        return termType;
    }

    public void setTermType(Integer termType) {
        this.termType = termType;
    }

    public Integer getAnnualizedDay() {
        return annualizedDay;
    }

    public void setAnnualizedDay(Integer annualizedDay) {
        this.annualizedDay = annualizedDay;
    }

    public Integer getBeginAmount() {
        return beginAmount;
    }

    public void setBeginAmount(Integer beginAmount) {
        this.beginAmount = beginAmount;
    }

    public Integer getIncreaseAmount() {
        return increaseAmount;
    }

    public void setIncreaseAmount(Integer increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    public Integer getBuyerLimits() {
        return buyerLimits;
    }

    public void setBuyerLimits(Integer buyerLimits) {
        this.buyerLimits = buyerLimits;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Integer getSalesModel() {
        return salesModel;
    }

    public void setSalesModel(Integer salesModel) {
        this.salesModel = salesModel;
    }

    public String getBeginSaleDate() {
		return beginSaleDate;
	}

	public void setBeginSaleDate(String beginSaleDate) {
		this.beginSaleDate = beginSaleDate;
	}

	public String getEndSaleDate() {
		return endSaleDate;
	}

	public void setEndSaleDate(String endSaleDate) {
		this.endSaleDate = endSaleDate;
	}

	public String getBeginIncomeDate() {
		return beginIncomeDate;
	}

	public void setBeginIncomeDate(String beginIncomeDate) {
		this.beginIncomeDate = beginIncomeDate;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Integer getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(Integer incomeType) {
        this.incomeType = incomeType;
    }

    public BigDecimal getFixedRatio() {
        return fixedRatio;
    }

    public void setFixedRatio(BigDecimal fixedRatio) {
        this.fixedRatio = fixedRatio;
    }

    public BigDecimal getFloatRatio() {
        return floatRatio;
    }

    public void setFloatRatio(BigDecimal floatRatio) {
        this.floatRatio = floatRatio;
    }

    public Integer getIncomeWay() {
        return incomeWay;
    }

    public void setIncomeWay(Integer incomeWay) {
        this.incomeWay = incomeWay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getRecordKeeping() {
        return recordKeeping;
    }

    public void setRecordKeeping(Integer recordKeeping) {
        this.recordKeeping = recordKeeping;
    }
    
    public String getRecordKeepingName() {
		return recordKeepingName;
	}

	public void setRecordKeepingName(String recordKeepingName) {
		this.recordKeepingName = recordKeepingName;
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

    public String getEditorTime() {
        return editorTime;
    }

    public void setEditorTime(String editorTime) {
        this.editorTime = editorTime;
    }

    public Integer getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Integer isPublished) {
        this.isPublished = isPublished;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Integer brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerTime() {
        return brokerTime;
    }

    public void setBrokerTime(String brokerTime) {
        this.brokerTime = brokerTime;
    }

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getAssetShortName() {
		return assetShortName;
	}

	public void setAssetShortName(String assetShortName) {
		this.assetShortName = assetShortName;
	}

	public String getServerTime() {
		return serverTime;
	}

	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}
	
	public String getScalePro() {
		return scalePro;
	}

	public void setScalePro(String scalePro) {
		this.scalePro = scalePro;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public Integer getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}
	
	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}
	
	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public String getRecordKeepingShortName() {
		return recordKeepingShortName;
	}

	public void setRecordKeepingShortName(String recordKeepingShortName) {
		this.recordKeepingShortName = recordKeepingShortName;
	}

	@Override
	public String toString() {
		return "Products [token=" + token + ", id=" + id + ", assetId="
				+ assetId + ", assetShortName=" + assetShortName + ", code="
				+ code + ", name=" + name + ", publishName=" + publishName
				+ ", shortName=" + shortName + ", productType=" + productType
				+ ", riskLevel=" + riskLevel + ", isProtected=" + isProtected
				+ ", scale=" + scale + ", surplusScale=" + surplusScale
				+ ", term=" + term + ", termType=" + termType
				+ ", annualizedDay=" + annualizedDay + ", beginAmount="
				+ beginAmount + ", increaseAmount=" + increaseAmount
				+ ", buyerLimits=" + buyerLimits + ", status=" + status
				+ ", isHot=" + isHot + ", salesModel=" + salesModel
				+ ", beginSaleDate=" + beginSaleDate + ", endSaleDate="
				+ endSaleDate + ", beginIncomeDate=" + beginIncomeDate
				+ ", expiredDate=" + expiredDate + ", incomeType=" + incomeType
				+ ", fixedRatio=" + fixedRatio + ", floatRatio=" + floatRatio
				+ ", incomeWay=" + incomeWay + ", description=" + description
				+ ", recordKeeping=" + recordKeeping + ", recordKeepingName="
				+ recordKeepingName + ", recordKeepingShortName="
				+ recordKeepingShortName + ", creatorId=" + creatorId
				+ ", createTime=" + createTime + ", editorId=" + editorId
				+ ", editorTime=" + editorTime + ", isPublished=" + isPublished
				+ ", publisherId=" + publisherId + ", publishTime="
				+ publishTime + ", brokerId=" + brokerId + ", brokerTime="
				+ brokerTime + ", recordTime=" + recordTime + ", serverTime="
				+ serverTime + ", scalePro=" + scalePro + ", isCollect="
				+ isCollect + ", gid=" + gid + "]";
	}

}