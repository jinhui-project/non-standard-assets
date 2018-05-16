package com.jinhui.model.abs;

import java.math.BigDecimal;
import java.util.Date;

public class PeFundChangeHistory {
    private Integer id;

    private Integer aid;

    private Integer baseAssetId;

    private String accountName;

    private String accountNo;

    private BigDecimal paybackAmount;

    private BigDecimal paytoAmount;

    private BigDecimal balanceAmount;

    private String isEnabled;

    private Date createTime;

    private String createName;
    
    private String changeType;

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

    public Integer getBaseAssetId() {
        return baseAssetId;
    }

    public void setBaseAssetId(Integer baseAssetId) {
        this.baseAssetId = baseAssetId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public BigDecimal getPaybackAmount() {
        return paybackAmount;
    }

    public void setPaybackAmount(BigDecimal paybackAmount) {
        this.paybackAmount = paybackAmount;
    }

    public BigDecimal getPaytoAmount() {
        return paytoAmount;
    }

    public void setPaytoAmount(BigDecimal paytoAmount) {
        this.paytoAmount = paytoAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled == null ? null : isEnabled.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
    
    
}