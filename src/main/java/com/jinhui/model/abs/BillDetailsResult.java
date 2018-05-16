package com.jinhui.model.abs;


import java.util.List;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
public class BillDetailsResult {


    private String fileName;

    /**
     * 上传文件后返回的文件id
     */
    private String fileId;

    private int errorCount;

    private int matchCount;

    private String errorTotalAmount;

    private String matchTotalAmount;

    private List<BillDetails> matchList;

    private List<BillDetails> errorList;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<BillDetails> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<BillDetails> matchList) {
        this.matchList = matchList;
    }

    public List<BillDetails> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<BillDetails> errorList) {
        this.errorList = errorList;
    }

    @Override
    public String toString() {
        return "BillDetailsResult{" +
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
