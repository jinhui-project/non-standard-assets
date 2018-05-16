package com.jinhui.model.abs;

import java.math.BigDecimal;
import java.util.Date;

public class PeAssetChangeHistory {
    private Integer id;

    private Integer aid;

    private Integer baseAssetId;

    private Date createTime;

    private String changeType;

    private String receivableDebtor;

    private BigDecimal assetAmount;

    private String createName;

    private String invoiceNo;

    private String invoiceCode;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType == null ? null : changeType.trim();
    }

    public String getReceivableDebtor() {
        return receivableDebtor;
    }

    public void setReceivableDebtor(String receivableDebtor) {
        this.receivableDebtor = receivableDebtor;
    }

    public BigDecimal getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(BigDecimal assetAmount) {
        this.assetAmount = assetAmount;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }
}