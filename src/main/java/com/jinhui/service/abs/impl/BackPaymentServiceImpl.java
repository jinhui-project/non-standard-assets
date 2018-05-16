package com.jinhui.service.abs.impl;

import com.jinhui.exception.AbsException;
import com.jinhui.mapper.abs.PeBackpaymentRecordAllMapper;
import com.jinhui.mapper.abs.PeBackpaymentRecordMapper;
import com.jinhui.mapper.abs.PeWhiteListMapper;
import com.jinhui.model.abs.*;
import com.jinhui.service.abs.BackPaymentService;
import com.jinhui.util.UserUtils;
import com.jinhui.util.excel.Excel2List;
import com.jinhui.util.excel.ExcelException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoyaunq on 2017/9/28 0028.
 */

@Service("BackPaymentService")
public class BackPaymentServiceImpl implements BackPaymentService {

    private static Logger logger = LoggerFactory.getLogger(BackPaymentServiceImpl.class);



    @Autowired
    private PeWhiteListMapper peWhiteListMapper;

    @Autowired
    private PeBackpaymentRecordMapper peBackpaymentRecordMapper;

    @Autowired
    private PeBackpaymentRecordAllMapper peBackpaymentRecordAllMapper;

    @Override
    public BackPaymentFilter importPayments(InputStream inputStream) throws AbsException {


        Excel2List<BackPayment> resolve = new Excel2List<>(BackPayment.class, 0, 2);

        List<BackPayment> paymentList = null;
        try {
            paymentList = resolve.resolve2List(inputStream);
        } catch (ExcelException e) {
            throw new AbsException(e.getMessage(), 500, e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }


        String gname = UserUtils.getUser().getGname();

        List<PeWhiteList> whiteLists = peWhiteListMapper.selectByOriginalHolder(gname);

        BackPaymentFilter filterResult=new BackPaymentFilter(paymentList,whiteLists,peBackpaymentRecordMapper);

        //检查数据，格式是否正确，是否有重复数据
        filterResult.checkPayments();



        return filterResult;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public List<PeBackpaymentRecord> saveMatchPayments(List<BackPayment> matchList) throws AbsException {

        if(null==matchList){
            return null;
        }
        ArrayList list = new ArrayList();
        String gname = UserUtils.getUser().getGname();
        for (BackPayment backPayment : matchList) {
            PeBackpaymentRecord peBackpaymentRecord = backPayment.covertToPeBackpaymentRecord(gname);
                peBackpaymentRecordMapper.insert(peBackpaymentRecord);
            list.add(peBackpaymentRecord);
        }

        return list;

    }

    @Override
    public List<PeAccountsReceivable> saveAllPayments(BackPaymentResult backPaymentResult) throws AbsException {

        List<BackPayment> list = backPaymentResult.getMatchList();

        list.addAll(backPaymentResult.getErrorList());

        String userName = UserUtils.getUserName();
        for (BackPayment payment : list) {

            PeBackpaymentRecordAll peBackpaymentRecordAll = payment.covertToPeBackpaymentRecordAll(backPaymentResult.getFileName(), backPaymentResult.getFileId(), userName);

            peBackpaymentRecordAllMapper.insert(peBackpaymentRecordAll);
        }


        return null;
    }





    /**
     * 查询未处理的回款记录
     *
     * @return
     */
    public List<PeBackpaymentRecord> selectBackpayRecordList() {
        return peBackpaymentRecordMapper.selectBackpayRecordList();
    }

    /**
     * 部分匹配
     *
     * @param amount
     * @return
     */
    public int partialMatch(Integer id, BigDecimal amount) {
        PeBackpaymentRecord pbr = new PeBackpaymentRecord();
        pbr.setId(id);
        pbr.setBackpayBalance(amount);
        return peBackpaymentRecordMapper.partialMatch(pbr);
    }

    /**
     * 完全匹配
     *
     * @return
     */
    public int perfectMatch(Integer id) {
        return peBackpaymentRecordMapper.perfectMatch(id);
    }


}
