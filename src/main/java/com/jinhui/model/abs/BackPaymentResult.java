package com.jinhui.model.abs;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
public class BackPaymentResult {

    private String fileName;

    /**
     * 上传文件后返回的文件id
     */
    private String fileId;

    private int errorCount;

    private int matchCount;

    private String errorTotalAmount;

    private String matchTotalAmount;

    private List<BackPayment> matchList;

    private List<BackPayment> errorList;


    public List<BackPayment> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<BackPayment> matchList) {
        this.matchList = matchList;
    }

    public List<BackPayment> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<BackPayment> errorList) {
        this.errorList = errorList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }

    public String getErrorTotalAmount() {
        return errorTotalAmount;
    }

    public void setErrorTotalAmount(String errorTotalAmount) {
        this.errorTotalAmount = errorTotalAmount;
    }

    public String getMatchTotalAmount() {
        return matchTotalAmount;
    }

    public void setMatchTotalAmount(String matchTotalAmount) {
        this.matchTotalAmount = matchTotalAmount;
    }

    @Override
    public String toString() {
        return "BackPaymentResult{" +
                "fileName='" + fileName + '\'' +
                ", fileId='" + fileId + '\'' +
                ", errorCount=" + errorCount +
                ", matchCount=" + matchCount +
                ", errorTotalAmount='" + errorTotalAmount + '\'' +
                ", matchTotalAmount='" + matchTotalAmount + '\'' +
                ", matchList=" + matchList +
                ", errorList=" + errorList +
                '}';
    }
}
