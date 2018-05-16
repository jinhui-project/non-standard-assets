package com.jinhui.model.abs;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PeBackpaymentRecord {

    /**
     * id
     */
    private Integer id;

    /**
     * 交易ID
     */
    private String tradeId;

    /**
     * 应收债务人
     */
    private String receivableDebtor;

    /**
     * 原始权益人
     */
    private String originalHolder;

    /**
     * 交易时间戳
     */
    private String tradeTimestamp;

    /**
     * 交易日期
     */
    @JsonFormat(pattern="yyyy/MM/dd", locale = "zh", timezone = "GMT+8")
    private Date tradeTime;

    /**
     * 回款金额
     */
    private BigDecimal backpayAmount;

    /**
     * 回款余额
     */
    private BigDecimal backpayBalance;

    /**
     * 付款账号
     */
    private String payerAccount;

    /**
     * 付款户名
     */
    private String payerName;

    /**
     * 交易行名
     */
    private String tadeBankName;

    /**
     * 交易附言
     */
    private String remark;

    /**
     * 状态：0-未处理 1-完全匹配  2-冲销  3-部分匹配
     */
    private String status;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取交易ID
     *
     * @return trade_id - 交易ID
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置交易ID
     *
     * @param tradeId 交易ID
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 获取应收债务人
     *
     * @return receivable_debtor - 应收债务人
     */
    public String getReceivableDebtor() {
        return receivableDebtor;
    }

    /**
     * 设置应收债务人
     *
     * @param receivableDebtor 应收债务人
     */
    public void setReceivableDebtor(String receivableDebtor) {
        this.receivableDebtor = receivableDebtor == null ? null : receivableDebtor.trim();
    }

    /**
     * 获取原始权益人
     *
     * @return original_holder - 原始权益人
     */
    public String getOriginalHolder() {
        return originalHolder;
    }

    /**
     * 设置原始权益人
     *
     * @param originalHolder 原始权益人
     */
    public void setOriginalHolder(String originalHolder) {
        this.originalHolder = originalHolder == null ? null : originalHolder.trim();
    }

    /**
     * 获取交易时间戳
     *
     * @return trade_timestamp - 交易时间戳
     */
    public String getTradeTimestamp() {
        return tradeTimestamp;
    }

    /**
     * 设置交易时间戳
     *
     * @param tradeTimestamp 交易时间戳
     */
    public void setTradeTimestamp(String tradeTimestamp) {
        this.tradeTimestamp = tradeTimestamp == null ? null : tradeTimestamp.trim();
    }

    /**
     * 获取交易日期
     *
     * @return trade_time - 交易日期
     */
    public Date getTradeTime() {
        return tradeTime;
    }

    /**
     * 设置交易日期
     *
     * @param tradeTime 交易日期
     */
    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    /**
     * 获取回款金额
     *
     * @return backpay_amount - 回款金额
     */
    public BigDecimal getBackpayAmount() {
        return backpayAmount;
    }

    /**
     * 设置回款金额
     *
     * @param backpayAmount 回款金额
     */
    public void setBackpayAmount(BigDecimal backpayAmount) {
        this.backpayAmount = backpayAmount;
    }

    /**
     * 获取回款余额
     *
     * @return backpay_balance - 回款余额
     */
    public BigDecimal getBackpayBalance() {
        return backpayBalance;
    }

    /**
     * 设置回款余额
     *
     * @param backpayBalance 回款余额
     */
    public void setBackpayBalance(BigDecimal backpayBalance) {
        this.backpayBalance = backpayBalance;
    }

    /**
     * 获取付款账号
     *
     * @return payer_account - 付款账号
     */
    public String getPayerAccount() {
        return payerAccount;
    }

    /**
     * 设置付款账号
     *
     * @param payerAccount 付款账号
     */
    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount == null ? null : payerAccount.trim();
    }

    /**
     * 获取付款户名
     *
     * @return payer_name - 付款户名
     */
    public String getPayerName() {
        return payerName;
    }

    /**
     * 设置付款户名
     *
     * @param payerName 付款户名
     */
    public void setPayerName(String payerName) {
        this.payerName = payerName == null ? null : payerName.trim();
    }

    /**
     * 获取交易行名
     *
     * @return tade_bank_name - 交易行名
     */
    public String getTadeBankName() {
        return tadeBankName;
    }

    /**
     * 设置交易行名
     *
     * @param tadeBankName 交易行名
     */
    public void setTadeBankName(String tadeBankName) {
        this.tadeBankName = tadeBankName == null ? null : tadeBankName.trim();
    }

    /**
     * 获取交易附言
     *
     * @return remark - 交易附言
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置交易附言
     *
     * @param remark 交易附言
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取状态：0-未处理 1-完全匹配  2-冲销  3-部分匹配
     *
     * @return status - 状态：0-未处理 1-完全匹配  2-冲销  3-部分匹配
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：0-未处理 1-完全匹配  2-冲销  3-部分匹配
     *
     * @param status 状态：0-未处理 1-完全匹配  2-冲销  3-部分匹配
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}