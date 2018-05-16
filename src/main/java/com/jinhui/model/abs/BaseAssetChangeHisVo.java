package com.jinhui.model.abs;

import java.math.BigDecimal;

/**
 * @autor wsc
 * @create 2017-11-06 9:36
 **/
public class BaseAssetChangeHisVo {

    private String baseAssetId;

    private String aid;

    private String changeType;

    private String receivableDebtor;

    private BigDecimal assetAmount;

    private String createName;
    
    private String invoiceNo;

    private String invoiceCode;

    public String getBaseAssetId() {
        return baseAssetId;
    }

    public void setBaseAssetId(String baseAssetId) {
        this.baseAssetId = baseAssetId;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
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
