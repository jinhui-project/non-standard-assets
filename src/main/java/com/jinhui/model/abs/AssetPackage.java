package com.jinhui.model.abs;

import java.math.BigDecimal;

/**
 * 资产包详情
 *
 * @autor wsc
 * @create 2017-10-27 18:41
 **/
public class AssetPackage {
    //资产包名称
    private String name;
    //资产包简称
    private String shortName;
    //资产包类型
    private int assetType;
    //预计发行时间
    private String issueDate;
    //发行规模
    private BigDecimal scale;
    //产品管理人
    private String assetMgr;
    //是否分级
    private String setTrench;
    //优先劣后比
    private String seniorPercent;
    //优先级评级
    private int seniorRating;
    //资产包预警规模
    private BigDecimal warnScale;
    //存续期
    private int tenor;
    //发行利率
    private BigDecimal rate;
    //基础资产数量
    private int assetsCount;
    //实际资产包规模
    private BigDecimal packageAmount;
    //应收债务人
    private String debtorNames;

    private String id;

    private String isHot;

    private String status;

    private String editDate;

    private String warnStatus;

    private String setupDate;

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

    public int getAssetType() {
        return assetType;
    }

    public void setAssetType(int assetType) {
        this.assetType = assetType;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public BigDecimal getScale() {
        return scale;
    }

    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }

    public String getAssetMgr() {
        return assetMgr;
    }

    public void setAssetMgr(String assetMgr) {
        this.assetMgr = assetMgr;
    }

    public String getSetTrench() {
        return setTrench;
    }

    public void setSetTrench(String setTrench) {
        this.setTrench = setTrench;
    }

    public String getSeniorPercent() {
        return seniorPercent;
    }

    public void setSeniorPercent(String seniorPercent) {
        this.seniorPercent = seniorPercent;
    }

    public int getSeniorRating() {
        return seniorRating;
    }

    public void setSeniorRating(int seniorRating) {
        this.seniorRating = seniorRating;
    }

    public BigDecimal getWarnScale() {
        return warnScale;
    }

    public void setWarnScale(BigDecimal warnScale) {
        this.warnScale = warnScale;
    }

    public int getTenor() {
        return tenor;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public int getAssetsCount() {
        return assetsCount;
    }

    public void setAssetsCount(int assetsCount) {
        this.assetsCount = assetsCount;
    }

    public BigDecimal getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(BigDecimal packageAmount) {
        this.packageAmount = packageAmount;
    }

    public String getDebtorNames() {
        return debtorNames;
    }

    public void setDebtorNames(String debtorNames) {
        this.debtorNames = debtorNames;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public String getWarnStatus() {
        return warnStatus;
    }

    public void setWarnStatus(String warnStatus) {
        this.warnStatus = warnStatus;
    }

    public String getSetupDate() {
        return setupDate;
    }

    public void setSetupDate(String setupDate) {
        this.setupDate = setupDate;
    }
}
