package com.jinhui.constant;


/**
 * 供应链的基础资产和回款状态常量
 * Created by Administrator on 2017/9/27 0027.
 */
public class AbsConstant {

    /**
     * 基础资产_待收
     */
    public final static String RECEIVING = "1";

    /**
     * 基础资产_已收
     */
    public final static String RECEVIED = "2";

    /**
     * 基础资产_逾期
     */
    public final static String OVERDUE = "3";

    /**
     * 基础资产_冲销
     */
    public final static String WRITE_OFF = "4";

    /**
     * 基础资产_逾期后回收
     */
    public final static String OVERDUE_RECOVER = "5";

    /**
     * 基础资产_坏账
     */
    public final static String BAD_DEBTS = "6";


    /**
     * 回款_未处理
     */

    public final static String BACKPAYMENT_INITIALIZE = "0";

    /**
     * 回款_完全匹配
     */
    public final static String BACKPAYMENT_ALL_MATCH = "1";

    /**
     * 回款_冲销
     */
    public final static String BACKPAYMENT_WRITE_OFF = "2";






}
