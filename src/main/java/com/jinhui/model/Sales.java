package com.jinhui.model;

import java.math.BigDecimal;

public class Sales {
	
	private String token;
	
    private Integer id;

    private Integer productId;

    private BigDecimal shares;

    private BigDecimal dealLimit;

    private BigDecimal dealShares;

    private Integer assetId;

    private Integer belongGid;
    
    private String recordShortName;

    private Integer createUid;

    private String createAt;

    private String updateAt;
    
    private String belongName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getShares() {
        return shares;
    }

    public void setShares(BigDecimal shares) {
        this.shares = shares;
    }

    public BigDecimal getDealLimit() {
        return dealLimit;
    }

    public void setDealLimit(BigDecimal dealLimit) {
        this.dealLimit = dealLimit;
    }

    public BigDecimal getDealShares() {
        return dealShares;
    }

    public void setDealShares(BigDecimal dealShares) {
        this.dealShares = dealShares;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public Integer getBelongGid() {
        return belongGid;
    }

    public void setBelongGid(Integer belongGid) {
        this.belongGid = belongGid;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
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

	public String getRecordShortName() {
		return recordShortName;
	}

	public void setRecordShortName(String recordShortName) {
		this.recordShortName = recordShortName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBelongName() {
		return belongName;
	}

	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}
	
}