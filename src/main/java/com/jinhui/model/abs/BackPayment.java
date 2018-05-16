package com.jinhui.model.abs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinhui.constant.AbsConstant;
import com.jinhui.util.excel.ExcelCell;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/29 0029.
 */
public class BackPayment {

    /**
     * 序号
     */
    @ExcelCell(index = 0,name="序号",allowNull = true)
    private String seq;


    /**
     * 应收债务人
     */
    @ExcelCell(index = 1,name="应收债务人",allowNull = true)
    private String receivableDebtor;


    /**
     * 交易日期
     */
    @JsonFormat(pattern="yyyy/MM/dd", locale = "zh", timezone = "GMT+8")
    @ExcelCell(index = 2,name="交易日期",format = "yyyy/MM/dd",allowNull = true)
    private Date tradeTime;

    /**
     * 交易时间戳
     */
    @ExcelCell(index = 3,name="交易时间戳",allowNull = true)
    private String tradeTimestamp;


    /**
     * 回款金额（收入金额）
     */
    @ExcelCell(index = 4,name="收入金额",decimal = "0.00",allowNull = true)
    private BigDecimal backpayAmount;


    /**
     * 交易行名
     */
    @ExcelCell(index = 5,name="交易行名",allowNull = true)
    private String tadeBankName;


    /**
     * 付款账号（对方账号）
     */
    @ExcelCell(index = 6,name="对方账号",allowNull = true)
    private String payerAccount;

    /**
     * 付款户名（对方户名）
     */
    @ExcelCell(index = 7,name="对方户名",allowNull = true)
    private String payerName;


    /**
     * 交易附言
     */
    @ExcelCell(index = 8,name="交易附言",allowNull = true)
    private String remark;

    /**
     * 正确标志，默认为true
     */
    private boolean correct=true;


    /**
     * 错误信息描述
     */
    private String errorMessage;




    /**
     * 如果“错误信息描述”不为空，把error添加在后面
     * @param error
     */
    public void appendErrorMessage(String error){
        if(StringUtils.isNotBlank(this.errorMessage)){
            this.errorMessage=errorMessage+";"+error;
        }else {
            this.errorMessage=error;
        }
    }


    public PeBackpaymentRecord covertToPeBackpaymentRecord(String originalHolder){

        PeBackpaymentRecord backpaymentRecord = new PeBackpaymentRecord();
        backpaymentRecord.setOriginalHolder(originalHolder);
        backpaymentRecord.setTradeTimestamp(tradeTimestamp);
        backpaymentRecord.setTradeTime(tradeTime);
        backpaymentRecord.setReceivableDebtor(receivableDebtor);
        backpaymentRecord.setBackpayAmount(backpayAmount);
        backpaymentRecord.setBackpayBalance(backpayAmount);
        backpaymentRecord.setPayerAccount(payerAccount);
        backpaymentRecord.setPayerName(payerName);
        backpaymentRecord.setRemark(remark);
        backpaymentRecord.setTadeBankName(tadeBankName);
        backpaymentRecord.setStatus(AbsConstant.BACKPAYMENT_INITIALIZE);

        return backpaymentRecord;
    }


    public PeBackpaymentRecordAll covertToPeBackpaymentRecordAll(String fileName,String fileId,String userName){

        PeBackpaymentRecordAll all=new PeBackpaymentRecordAll();
        all.setFileName(fileName);
        all.setFileId(fileId);
        all.setCreator(userName);
        all.setCreateTime(new Date());

        all.setBackpayAmount(backpayAmount);
        all.setPayerAccount(payerAccount);
        all.setTadeBankName(tadeBankName);
        all.setTradeTimestamp(tradeTimestamp);
        all.setPayerName(payerName);
        all.setReceivableDebtor(receivableDebtor);
        all.setRemark(remark);
        all.setTradeTime(tradeTime);
        all.setExceptionDesc(errorMessage);
        if(!correct){
            all.setIsException("1");
        }else {
            all.setIsException("0");
        }


        return all;

    }




    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
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

    @Override
    public String toString() {
        return "BackPayment{" +
                "seq='" + seq + '\'' +
                ", receivableDebtor='" + receivableDebtor + '\'' +
                ", tradeTime=" + tradeTime +
                ", tradeTimestamp='" + tradeTimestamp + '\'' +
                ", backpayAmount=" + backpayAmount +
                ", tadeBankName='" + tadeBankName + '\'' +
                ", payerAccount='" + payerAccount + '\'' +
                ", payerName='" + payerName + '\'' +
                ", remark='" + remark + '\'' +
                ", correct=" + correct +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
