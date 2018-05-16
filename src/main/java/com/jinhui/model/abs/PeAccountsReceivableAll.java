package com.jinhui.model.abs;

import java.math.BigDecimal;
import java.util.Date;

public class PeAccountsReceivableAll {
    /**
     * id
     */
    private Integer id;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件id
     */
    private String fileId;

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
    private Date invoicedDate;

    /**
     * 待还日期
     */
    private Date returnedDate;

    /**
     * 回款日期
     */
    private Date arrivalDate;

    /**
     * 项目名称
     */
    private String projectName;

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
     * 获取文件id
     *
     * @return file_id - 文件id
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 设置文件id
     *
     * @param fileId 文件id
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
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