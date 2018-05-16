package com.jinhui.model.abs;

/**
 * 构建abs基本资产查询参数
 * Created by luoyuanq on 2017/10/11 0011.
 */
public class BillQueryParam {

    /**
     * 原始权益人
     */
    private String originalHolder;

    /**
     *应收债务人
     */
    private String debtorName;


    /**
     * 价税合计开始
     */
    private String billBalanceStart;


    /**
     * 价税合计结束
     */
    private String billBalanceEnd;


    /**
     * 到期日开始
     */
    private String returnedDateStart;

    /**
     * 到期日结束
     */
    private String returnedDateEnd;


    public String getOriginalHolder() {
        return originalHolder;
    }

    public void setOriginalHolder(String originalHolder) {
        this.originalHolder = originalHolder;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public String getBillBalanceStart() {
        return billBalanceStart;
    }

    public void setBillBalanceStart(String billBalanceStart) {
        this.billBalanceStart = billBalanceStart;
    }

    public String getBillBalanceEnd() {
        return billBalanceEnd;
    }

    public void setBillBalanceEnd(String billBalanceEnd) {
        this.billBalanceEnd = billBalanceEnd;
    }

    public String getReturnedDateStart() {
        return returnedDateStart;
    }

    public void setReturnedDateStart(String returnedDateStart) {
        this.returnedDateStart = returnedDateStart;
    }

    public String getReturnedDateEnd() {
        return returnedDateEnd;
    }

    public void setReturnedDateEnd(String returnedDateEnd) {
        this.returnedDateEnd = returnedDateEnd;
    }

    @Override
    public String toString() {
        return "BillQueryParam{" +
                "originalHolder='" + originalHolder + '\'' +
                ", debtorName='" + debtorName + '\'' +
                ", billBalanceStart='" + billBalanceStart + '\'' +
                ", billBalanceEnd='" + billBalanceEnd + '\'' +
                ", returnedDateStart='" + returnedDateStart + '\'' +
                ", returnedDateEnd='" + returnedDateEnd + '\'' +
                '}';
    }
}
