package com.jinhui.model.abs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinhui.constant.AbsConstant;
import com.jinhui.util.excel.ExcelCell;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用@ExcelCell来 解析 “账单和回款模板.xlsx”的 “开票明细”
 * Created by luoyuanq on 2017/9/26 0026.
 */
public class BillDetails {

    private final static SimpleDateFormat sdf=new  SimpleDateFormat("yyyy/MM/dd");

    //序号
    @ExcelCell(index = 0, name="序号",allowNull = true)
    private String seq;

    /**
     * 应收债务人
     */
    @ExcelCell(index = 1,name="应收债务人", allowNull = true)
    private String debtorName;


    /**
     * 发票代码
     */
    @ExcelCell(index = 2,name="发票代码",allowNull = true)
    private String invoiceCode;


    /**
     * 发票号码
     */
    @ExcelCell(index = 3,name="发票号码", allowNull = true)
    private String invoiceNo;


    /**
     * 开票金额
     */
    @ExcelCell(index = 4,name="开票金额",decimal = "0.00",allowNull = true)
    private BigDecimal invoicedAmount;


    //账单金额(价税合计)
    @ExcelCell(index = 5, name="价税合计",decimal = "0.00",allowNull = true)
    private BigDecimal billBalance;


    /**
     * 开票日期
     */
    @JsonFormat(pattern="yyyy/MM/dd", locale = "zh", timezone = "GMT+8")
    @ExcelCell(index = 6,name="开票日期", format = "yyyy/MM/dd", allowNull = true)
    private Date invoicedDate;

    /**
     * 客户名称
     */
    @ExcelCell(index = 7, name="客户名称",allowNull = true)
    private String customerName;


    /**
     * 客户识别号
     */
    @ExcelCell(index = 8, name="客户识别号",allowNull = true)
    private String customerNumber;


    /**
     * 主要商品名称
     */
    @ExcelCell(index = 9, name="主要商品名称",allowNull = true)
    private String projectName;


    /**
     * 原发票代码
     */
    @ExcelCell(index = 10, name="原发票代码",allowNull = true)
    private String originInvoiceCode;

    /**
     * 原发票号码
     */
    @ExcelCell(index = 11, name="原发票号码",allowNull = true)
    private String originInvoiceNo;

    /**
     * 正确标志，默认为true
     */
    private boolean correct=true;


    /**
     * 错误信息描述
     */
    private String errorMessage;



    /**
     * 判断账单类型,原发票代码和原发票号码都不为空就是冲销类型
     * @return
     */
    public String checkType(){

        if(StringUtils.isNotBlank(originInvoiceNo)||StringUtils.isNotBlank(originInvoiceCode)){
            return AbsConstant.WRITE_OFF;
        } else{
            return AbsConstant.RECEIVING;
        }

    }

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


    /**
     * 把账单类转为 “应收账款类”（基本资产）
     * @param originalHolder 原始权益人
     * @param receivablePeriod  白名单中的“应收账期”
     * @return
     */
    public PeAccountsReceivable convertToPeAccountsReceivable(String originalHolder,int receivablePeriod) {
        PeAccountsReceivable receivable = new PeAccountsReceivable();


        receivable.setDebtorName(debtorName);
        receivable.setInvoiceCode(invoiceCode);
        receivable.setInvoiceNo(invoiceNo);
        receivable.setInvoicedAmount(invoicedAmount);
        receivable.setInvoicedDate(invoicedDate);
        receivable.setCustomerName(customerName);
        receivable.setCustormerNumber(customerNumber);
        receivable.setBillAmount(billBalance);
        receivable.setStatus(AbsConstant.RECEIVING);
        receivable.setProjectName(projectName);
        receivable.setBillBalance(billBalance);
        receivable.setReceivablePeriod(receivablePeriod);

        receivable.setOriginalHolder(originalHolder);


        //根据"开票日期"和白名单中的"应收账期"来计算出"待还日期";
        DateTime dt = new DateTime(invoicedDate);
        DateTime dateTime = dt.plusDays(receivablePeriod);
        receivable.setReturnedDate(dateTime.toDate());//待还日期


        return receivable;
    }


    /**
     * 把把账单类转为 “应收账款类全表类”
     * @return
     */
    public PeAccountsReceivableAll convertToPeAccountsReceivableAll(String fileName,String fileId,String userName) {

        PeAccountsReceivableAll receivableAll=new PeAccountsReceivableAll();
        receivableAll.setFileId(fileId);
        receivableAll.setFileName(fileName);
        receivableAll.setExceptionDesc(errorMessage);
        receivableAll.setCreator(userName);
        receivableAll.setCreateTime(new Date());
        if(!correct){
            receivableAll.setIsException("1");
        }else {
            receivableAll.setIsException("0");
        }

        receivableAll.setDebtorName(debtorName);
        receivableAll.setInvoiceCode(invoiceCode);
        receivableAll.setInvoiceNo(invoiceNo);
        receivableAll.setInvoicedAmount(invoicedAmount);
        receivableAll.setInvoicedDate(invoicedDate);
        receivableAll.setCustomerName(customerName);
        receivableAll.setCustormerNumber(customerNumber);
        receivableAll.setBillAmount(billBalance);
        receivableAll.setProjectName(projectName);
        receivableAll.setBillBalance(billBalance);

         return receivableAll;
    }


    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public BigDecimal getInvoicedAmount() {
        return invoicedAmount;
    }

    public void setInvoicedAmount(BigDecimal invoicedAmount) {
        this.invoicedAmount = invoicedAmount;
    }

    public BigDecimal getBillBalance() {
        return billBalance;
    }

    public void setBillBalance(BigDecimal billBalance) {
        this.billBalance = billBalance;
    }

    public Date getInvoicedDate() {
        return invoicedDate;
    }

    public void setInvoicedDate(Date invoicedDate) {
        this.invoicedDate = invoicedDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOriginInvoiceNo() {
        return originInvoiceNo;
    }

    public void setOriginInvoiceNo(String originInvoiceNo) {
        this.originInvoiceNo = originInvoiceNo;
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


    public String getOriginInvoiceCode() {
        return originInvoiceCode;
    }

    public void setOriginInvoiceCode(String originInvoiceCode) {
        this.originInvoiceCode = originInvoiceCode;
    }
}
