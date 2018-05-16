package com.jinhui.model.abs;

/**
 *
 * Created by Administrator on 2017/10/13 0013.
 */
public class PaymentQueryParam {

    /**
     * 原始权益人
     */
    private String originalHolder;

    /**
     *应收债务人
     */
    private String debtorName;


    /**
     * 收入金额开始
     */
    private String backpayAmountStart;


    /**
     * 收入金额结束
     */
    private String backpayAmountEnd;

    /**
     * 交易金额开始
     */
    private String tradeTimeStart;

    /**
     * 交易金额结束
     */
    private String tradeTimeEnd;


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

    public String getBackpayAmountStart() {
        return backpayAmountStart;
    }

    public void setBackpayAmountStart(String backpayAmountStart) {
        this.backpayAmountStart = backpayAmountStart;
    }

    public String getBackpayAmountEnd() {
        return backpayAmountEnd;
    }

    public void setBackpayAmountEnd(String backpayAmountEnd) {
        this.backpayAmountEnd = backpayAmountEnd;
    }

    public String getTradeTimeStart() {
        return tradeTimeStart;
    }

    public void setTradeTimeStart(String tradeTimeStart) {
        this.tradeTimeStart = tradeTimeStart;
    }

    public String getTradeTimeEnd() {
        return tradeTimeEnd;
    }

    public void setTradeTimeEnd(String tradeTimeEnd) {
        this.tradeTimeEnd = tradeTimeEnd;
    }

    @Override
    public String toString() {
        return "PaymentQueryParam{" +
                "originalHolder='" + originalHolder + '\'' +
                ", debtorName='" + debtorName + '\'' +
                ", backpayAmountStart='" + backpayAmountStart + '\'' +
                ", backpayAmountEnd='" + backpayAmountEnd + '\'' +
                ", tradeTimeStart='" + tradeTimeStart + '\'' +
                ", tradeTimeEnd='" + tradeTimeEnd + '\'' +
                '}';
    }
}
