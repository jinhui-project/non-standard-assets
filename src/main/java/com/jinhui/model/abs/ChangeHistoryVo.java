package com.jinhui.model.abs;

/**
 * 变动列表查询vo
 *
 * @autor wsc
 * @create 2017-11-08 16:25
 **/
public class ChangeHistoryVo {

    private String pageNum;

    private String pageSize;

    private int aid;

    private String beginTime;

    private String endTime;

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ChangeHistoryVo{" +
                "pageNum='" + pageNum + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", aid=" + aid +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
