package com.jinhui.controller.abs;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinhui.exception.AbsException;
import com.jinhui.mapper.abs.PeBackpaymentRecordMapper;
import com.jinhui.model.WebResult;
import com.jinhui.model.abs.*;
import com.jinhui.quartz.ABSAssetsQuartz;
import com.jinhui.service.abs.BackPaymentService;
import com.jinhui.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

@Controller
@RequestMapping("/abs")
public class BackPaymentController {

    private static Logger logger = LoggerFactory.getLogger(BackPaymentController.class);

    @Autowired
    private BackPaymentService backPaymentService;

    @Autowired
    private PeBackpaymentRecordMapper peBackpaymentRecordMapper;


    @Autowired
    private ABSAssetsQuartz aBSAssetsQuartz;

    //上传回款文件,返回处理结果
    @RequestMapping(value = "/uploadBackPaymentFile", method = RequestMethod.POST)
    @ResponseBody
    public WebResult uploadBackPaymentFile(MultipartFile fileContent) throws AbsException {


        if (fileContent == null) {
            throw new AbsException("找不到上传文件，请重新上传", 404);
        }
        if (fileContent.getOriginalFilename().indexOf("回款")<0){
            throw new AbsException("请上传回款文件", 400);
        }

        InputStream  inputStream;
        try {
            inputStream = fileContent.getInputStream();

        } catch (IOException e) {
            throw new AbsException("文件上传失败，请联系管理员", 500, e);
        }

        BackPaymentFilter filterResult = backPaymentService.importPayments(inputStream);

        BackPaymentResult backPaymentResult=new BackPaymentResult();

        backPaymentResult.setErrorList(filterResult.getErrorList());
        backPaymentResult.setMatchList(filterResult.getMatchList());
        backPaymentResult.setFileName(fileContent.getOriginalFilename());
        backPaymentResult.setErrorCount(filterResult.getErrorList().size());
        backPaymentResult.setMatchCount(filterResult.getMatchList().size());

        final BigDecimal zero = new BigDecimal("0");
        BigDecimal errorTotalAmount = filterResult.getErrorList().stream().filter(pay -> null!=pay.getBackpayAmount())
                .map(pay -> pay.getBackpayAmount()).reduce(zero, (a, b) -> a.add(b));

        BigDecimal matchTotalAmount = filterResult.getMatchList().stream().filter(pay -> null!=pay.getBackpayAmount())
                .map(pay -> pay.getBackpayAmount()).reduce(zero, (a, b) -> a.add(b));

        backPaymentResult.setErrorTotalAmount(errorTotalAmount.toString());
        backPaymentResult.setMatchTotalAmount(matchTotalAmount.toString());

        WebResult  result = WebResult.ok();

        result.setData(backPaymentResult);

        return result;

    }


    //上传匹配的回款信息
    @RequestMapping(value = "/saveBackPayments", method = RequestMethod.POST)
    @ResponseBody
    public WebResult uploadBackPayments(@RequestBody BackPaymentResult backPaymentResult) throws AbsException {

        System.out.println("backPaymentResult:" + backPaymentResult);

        List<BackPayment> matchList = backPaymentResult.getMatchList();

        if(matchList.size()==0){
            return WebResult.error("提交失败，没有可匹配的回款信息");
        }

        logger.info("保存上传的回款文件："+backPaymentResult);
        backPaymentService.saveMatchPayments(matchList);
        backPaymentService.saveAllPayments(backPaymentResult);

        //保存完回款之后，进行回款流程
        aBSAssetsQuartz.backPayment();

        //补充资产和替换资产
        aBSAssetsQuartz.replaceBaseAssets();

        WebResult ok = WebResult.ok();
        ok.setData("保存回款成功");

        return ok;
    }



    //条件查询回款，分页
    @RequestMapping(value = "/queryPayments", method = RequestMethod.POST)
    @ResponseBody
    public WebResult queryBills(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody PaymentQueryParam queryParam) {


        String gname = UserUtils.getUser().getGname();
        queryParam.setOriginalHolder(gname);

        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<PeBackpaymentRecord> list = peBackpaymentRecordMapper.selectByQueryParam(queryParam);
        System.out.println(list);
        PageInfo<PeBackpaymentRecord> pageInfo=new PageInfo(list);

        WebResult result = WebResult.ok();
        result.setData(pageInfo);

        return result;
    }

}
