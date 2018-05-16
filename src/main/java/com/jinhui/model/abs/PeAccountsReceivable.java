package com.jinhui.model.abs;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PeAccountsReceivable {
    /**
     * id
     */
    private Integer id;

    /**
     * 资产ID
     */
    private Integer aid;

    /**
     * 应收债务人
     */
    private String debtorName;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户识别号
     */
    private String custormerNumber;

    /**
     * 原始权益人
     */
    private String originalHolder;

    /**
     * 开票金额
     */
    private BigDecimal invoicedAmount;

    /**
     * 账单金额
     */
    private BigDecimal billAmount;

    /**
     * 账单余额
     */
    private BigDecimal billBalance;

    /**
     * 发票号码
     */
    private String invoiceNo;

    /**
     * 发票代码
     */
    private String invoiceCode;

    /**
     * 开票日期
     */
    @JsonFormat(pattern="yyyy/MM/dd", locale = "zh", timezone = "GMT+8")
    private Date invoicedDate;

    /**
     * 待还日期
     */
    @JsonFormat(pattern="yyyy/MM/dd", locale = "zh", timezone = "GMT+8")
    private Date returnedDate;

    /**
     * 应收账期
     */
    private Integer receivablePeriod;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 回款日期
     */
    private Date arrivalDate;

    /**
     * 状态：1-待收  2-已收  3-逾期  4-冲销  5-逾期后回收  6-坏账
     */
    private String status;

    /**
     * 回款记录ID
     */
    private Integer backpaymentID;

    /**
     * 警告状态
     */
    private String warnStatus;



    /**
     * 1.如果该笔存量资产余额为零，表示该资产是“已收”或“逾期已收”状态,回款金额=账单金额
     * 2.如果该笔存量资产余额不为零，表示该资产是“待收”，“坏账”或“逾期”状态，回款金额=账单金额减去余额
     * @param status
     * @return
     */
    public PeBackpaymentRecord covertToPeBackpaymentRecord(String status) {
        PeBackpaymentRecord backpaymentRecord = new PeBackpaymentRecord();
        backpaymentRecord.setOriginalHolder(originalHolder);
        backpaymentRecord.setStatus(status);
        backpaymentRecord.setReceivableDebtor(debtorName);
        backpaymentRecord.setTradeTime(new Date());



        if (billBalance.doubleValue() == 0) {
            backpaymentRecord.setBackpayAmount(billAmount);
            backpaymentRecord.setBackpayBalance(billAmount);
        } else {
            BigDecimal balance = billAmount.subtract(billBalance);
            backpaymentRecord.setBackpayAmount(balance);
            backpaymentRecord.setBackpayBalance(balance);

        }

        return backpaymentRecord;

    }

    public String getWarnStatus() {
        return warnStatus;
    }

    public void setWarnStatus(String warnStatus) {
        this.warnStatus = warnStatus;
    }

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
     * 获取资产ID
     *
     * @return aid - 资产ID
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 设置资产ID
     *
     * @param aid 资产ID
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * 获取应收债务人
     *
     * @return debtor_name - 应收债务人
     */
    public String getDebtorName() {
        return debtorName;
    }

    /**
     * 设置应收债务人
     *
     * @param debtorName 应收债务人
     */
    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName == null ? null : debtorName.trim();
    }

    /**
     * 获取客户名称
     *
     * @return customer_name - 客户名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置客户名称
     *
     * @param customerName 客户名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     * 获取客户识别号
     *
     * @return custormer_number - 客户识别号
     */
    public String getCustormerNumber() {
        return custormerNumber;
    }

    /**
     * 设置客户识别号
     *
     * @param custormerNumber 客户识别号
     */
    public void setCustormerNumber(String custormerNumber) {
        this.custormerNumber = custormerNumber == null ? null : custormerNumber.trim();
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
     * 获取开票金额
     *
     * @return invoiced_amount - 开票金额
     */
    public BigDecimal getInvoicedAmount() {
        return invoicedAmount;
    }

    /**
     * 设置开票金额
     *
     * @param invoicedAmount 开票金额
     */
    public void setInvoicedAmount(BigDecimal invoicedAmount) {
        this.invoicedAmount = invoicedAmount;
    }

    /**
     * 获取账单金额
     *
     * @return bill_amount - 账单金额
     */
    public BigDecimal getBillAmount() {
        return billAmount;
    }

    /**
     * 设置账单金额
     *
     * @param billAmount 账单金额
     */
    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    /**
     * 获取账单余额
     *
     * @return bill_balance - 账单余额
     */
    public BigDecimal getBillBalance() {
        return billBalance;
    }

    /**
     * 设置账单余额
     *
     * @param billBalance 账单余额
     */
    public void setBillBalance(BigDecimal billBalance) {
        this.billBalance = billBalance;
    }

    /**
     * 获取发票号码
     *
     * @return invoice_no - 发票号码
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * 设置发票号码
     *
     * @param invoiceNo 发票号码
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }

    /**
     * 获取发票代码
     *
     * @return invoice_code - 发票代码
     */
    public String getInvoiceCode() {
        return invoiceCode;
    }

    /**
     * 设置发票代码
     *
     * @param invoiceCode 发票代码
     */
    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode == null ? null : invoiceCode.trim();
    }

    /**
     * 获取开票日期
     *
     * @return invoiced_date - 开票日期
     */
    public Date getInvoicedDate() {
        return invoicedDate;
    }

    /**
     * 设置开票日期
     *
     * @param invoicedDate 开票日期
     */
    public void setInvoicedDate(Date invoicedDate) {
        this.invoicedDate = invoicedDate;
    }

    /**
     * 获取待还日期
     *
     * @return returned_date - 待还日期
     */
    public Date getReturnedDate() {
        return returnedDate;
    }

    /**
     * 设置待还日期
     *
     * @param returnedDate 待还日期
     */
    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    /**
     * 获取应收账期
     *
     * @return receivable_period - 应收账期
     */
    public Integer getReceivablePeriod() {
        return receivablePeriod;
    }

    /**
     * 设置应收账期
     *
     * @param receivablePeriod 应收账期
     */
    public void setReceivablePeriod(Integer receivablePeriod) {
        this.receivablePeriod = receivablePeriod;
    }

    /**
     * 获取项目名称
     *
     * @return project_name - 项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置项目名称
     *
     * @param projectName 项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     * 获取回款日期
     *
     * @return arrival_date - 回款日期
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * 设置回款日期
     *
     * @param arrivalDate 回款日期
     */
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * 获取状态：1-待收  2-已收  3-逾期  4-冲销  5-逾期后回收  6-坏账
     *
     * @return status - 状态：1-待收  2-已收  3-逾期  4-冲销  5-逾期后回收  6-坏账
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：1-待收  2-已收  3-逾期  4-冲销  5-逾期后回收  6-坏账
     *
     * @param status 状态：1-待收  2-已收  3-逾期  4-冲销  5-逾期后回收  6-坏账
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getBackpaymentID() {
        return backpaymentID;
    }

    public void setBackpaymentID(Integer backpaymentID) {
        this.backpaymentID = backpaymentID;
    }

    @Override
    public String toString() {
        return "PeAccountsReceivable{" +
                "id=" + id +
                ", aid=" + aid +
                ", debtorName='" + debtorName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", custormerNumber='" + custormerNumber + '\'' +
                ", originalHolder='" + originalHolder + '\'' +
                ", invoicedAmount=" + invoicedAmount +
                ", billAmount=" + billAmount +
                ", billBalance=" + billBalance +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", invoiceCode='" + invoiceCode + '\'' +
                ", invoicedDate=" + invoicedDate +
                ", returnedDate=" + returnedDate +
                ", receivablePeriod=" + receivablePeriod +
                ", projectName='" + projectName + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", status='" + status + '\'' +
                '}';
    }
}