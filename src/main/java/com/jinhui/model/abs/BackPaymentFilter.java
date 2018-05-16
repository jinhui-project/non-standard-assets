package com.jinhui.model.abs;

import com.jinhui.mapper.abs.PeBackpaymentRecordMapper;
import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/10/12 0012.
 */
public class BackPaymentFilter {

    private List<BackPayment> matchList = new ArrayList<>();//匹配白名单的数据

    private String matchRate;//匹配率 = 匹配数/全部数据数

    private List<BackPayment> errorList;//不合法的数据

    private List<BackPayment> paymentList;

    private List<PeWhiteList> whiteList;

    private PeBackpaymentRecordMapper peBackpaymentRecordMapper;



    public BackPaymentFilter(List<BackPayment> paymentList, List<PeWhiteList> whiteList, PeBackpaymentRecordMapper peBackpaymentRecordMapper) {
        this.paymentList = paymentList;
        this.whiteList = whiteList;
        this.peBackpaymentRecordMapper = peBackpaymentRecordMapper;
    }

    public void checkPayments() {


        for (BackPayment payment : paymentList) {

            //检查必输项是否为空
            checkIsNull(payment);


            //如果debtorName不为空,检测是不是在白名单内
            if (StringUtils.isNotBlank(payment.getReceivableDebtor())) {

                boolean match = whiteList.stream().anyMatch(white -> white.getReceivableDebtor().equals(payment.getReceivableDebtor()));
                if (!match) {
                    payment.setCorrect(false);
                    payment.appendErrorMessage("应收债务人未匹配");
                }
            }


            //检查重复的数据，根据tradeTimestamp来判断
            String tradeTimestamp = payment.getTradeTimestamp();
            if (StringUtils.isNotBlank(tradeTimestamp)) {
                //检测数据中是否存在重复
                long count = paymentList.stream().filter(pay -> StringUtils.isNotBlank(pay.getTradeTimestamp()))
                        .filter(pay -> pay.getTradeTimestamp().equals(tradeTimestamp)).count();
                if (count > 1) {
                    payment.setCorrect(false);
                    payment.appendErrorMessage("该条数据是重复数据，请交易时间戳是否相同");
                }

                //检查数据库中是否有重复数据
                if (StringUtils.isNotBlank(tradeTimestamp)) {
                    PeBackpaymentRecord record = peBackpaymentRecordMapper.selectByTradeTimestamp(tradeTimestamp);
                    if (null != record) {
                        payment.setCorrect(false);
                        payment.appendErrorMessage("该条数据系统已经录入，不能重复上传");
                    }
                }

            }

        }
        errorList = paymentList.stream().filter(pay -> !pay.isCorrect()).collect(Collectors.toList());

        matchList = paymentList.stream().filter(pay -> pay.isCorrect()).collect(Collectors.toList());

        matchRate = calcMatchRate(matchList.size(), paymentList.size());

    }


    private void checkIsNull(BackPayment payment) {


        checkNullAndLength(payment, payment.getReceivableDebtor(), 20, "应收债务人");

        checkNullAndLength(payment, payment.getTadeBankName(), 50, "交易行名");

        checkNullAndLength(payment, payment.getPayerAccount(), 19, "对方账号");

        checkNullAndLength(payment, payment.getPayerName(), 50, "对方户名");

        checkNullAndLength(payment, payment.getTradeTimestamp(), 20, "交易时间戳");

        if (null == payment.getTradeTime()) {
            payment.setCorrect(false);
            payment.appendErrorMessage("交易日期不能为空");
        }


        if (null == payment.getBackpayAmount()) {
            payment.setCorrect(false);
            payment.appendErrorMessage("收入金额不能为空");
        }


    }

    private void checkNullAndLength(BackPayment payment, String checkMsg, int length, String tip) {

        if (StringUtils.isBlank(checkMsg)) {
            payment.setCorrect(false);
            payment.appendErrorMessage(tip + "不能为空");
        } else {
            if (checkMsg.length() > length) {
                payment.setCorrect(false);
                payment.appendErrorMessage(tip + "长度过长");
            }
        }

    }



    /**
     * 计算匹配率
     *
     * @param matchNum
     * @param totalNum
     * @return
     */
    public String calcMatchRate(int matchNum, int totalNum) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (matchNum == 0 || totalNum == 0) {
            return "0";
        }
        double rate = (double) matchNum / (double) totalNum * 100;

        return df.format(rate) + "%";


    }


    public List<BackPayment> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<BackPayment> matchList) {
        this.matchList = matchList;
    }

    public String getMatchRate() {
        return matchRate;
    }

    public void setMatchRate(String matchRate) {
        this.matchRate = matchRate;
    }

    public List<BackPayment> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<BackPayment> errorList) {
        this.errorList = errorList;
    }

    public List<BackPayment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<BackPayment> paymentList) {
        this.paymentList = paymentList;
    }


    @Override
    public String toString() {
        return "BackPaymentFilter{" +
                "matchList=" + matchList +
                ", matchRate='" + matchRate + '\'' +
                ", errorList=" + errorList +
                ", paymentList=" + paymentList +
                '}';
    }
}





