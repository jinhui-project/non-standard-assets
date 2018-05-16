package com.jinhui.model.abs;

import java.math.BigDecimal;
import java.util.Date;

public class PeBackpaymentRecordAll {
    /**
     * id
     */
    private Integer id;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件保存ID
     */
    private String fileId;

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
     * 是否异常：0-否  1-是
     */
    private String isException;

    /**
     * 异常描述
     */
    private String exceptionDesc;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

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
     * 获取文件名称
     *
     * @return file_name - 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 获取文件保存ID
     *
     * @return file_id - 文件保存ID
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 设置文件保存ID
     *
     * @param fileId 文件保存ID
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
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
     * 获取是否异常：0-否  1-是
     *
     * @return is_exception - 是否异常：0-否  1-是
     */
    public String getIsException() {
        return isException;
    }

    /**
     * 设置是否异常：0-否  1-是
     *
     * @param isException 是否异常：0-否  1-是
     */
    public void setIsException(String isException) {
        this.isException = isException == null ? null : isException.trim();
    }

    /**
     * 获取异常描述
     *
     * @return exception_desc - 异常描述
     */
    public String getExceptionDesc() {
        return exceptionDesc;
    }

    /**
     * 设置异常描述
     *
     * @param exceptionDesc 异常描述
     */
    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc == null ? null : exceptionDesc.trim();
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}