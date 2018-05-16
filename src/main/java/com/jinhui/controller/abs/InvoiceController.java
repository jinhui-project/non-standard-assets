package com.jinhui.controller.abs;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinhui.exception.AbsException;
import com.jinhui.mapper.abs.PeAccountsReceivableMapper;
import com.jinhui.model.WebResult;
import com.jinhui.model.abs.*;
import com.jinhui.quartz.ABSAssetsQuartz;
import com.jinhui.service.abs.InvoiceService;
import com.jinhui.util.UpLoadUtil;
import com.jinhui.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

@Controller
@RequestMapping("/abs")
public class InvoiceController {

    private static Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @Value("${excelPath}")
    private String excelPath;


    @Autowired
    private InvoiceService invoiceService;


    @Autowired
    private PeAccountsReceivableMapper peAccountsReceivableMapper;

    @Autowired
    private ABSAssetsQuartz aBSAssetsQuartz;


    //上传账单文件文件,返回处理结果
    @RequestMapping(value = "/uploadBillsFile", method = RequestMethod.POST)
    @ResponseBody
    public WebResult uploadBillsFile(MultipartFile fileContent) throws AbsException {


        if (fileContent == null) {
            throw new AbsException("找不到上传文件，请重新上传", 404);
        }
        if (fileContent.getOriginalFilename().indexOf("开票")<0){
            throw new AbsException("请上传开票文件", 400);
        }

        logger.info("上传账单：" + fileContent.getOriginalFilename());

        InputStream inputStream = null;
        try {
            inputStream = fileContent.getInputStream();

        } catch (IOException e) {
            throw new AbsException("文件上传失败，请联系管理员", 500, e);
        }


        BillDetailsFilter billDetailsFilterResult = invoiceService.importInvoices(inputStream);


        //组装返回的结果
        BillDetailsResult result = new BillDetailsResult();

        result.setFileName(fileContent.getOriginalFilename());
        result.setMatchList(billDetailsFilterResult.getMatchList());
        result.setErrorList(billDetailsFilterResult.getErrorList());

        result.setErrorCount(billDetailsFilterResult.getErrorList().size());
        result.setMatchCount(billDetailsFilterResult.getMatchList().size());

        final BigDecimal zero = new BigDecimal("0");
        BigDecimal errorTotalAmount = billDetailsFilterResult.getErrorList().stream().filter(bill -> null!=bill.getBillBalance())
                .map(bill -> bill.getBillBalance()).reduce(zero, (a, b) -> a.add(b));

        BigDecimal matchTotalAmount = billDetailsFilterResult.getMatchList().stream().filter(bill -> null!=bill.getBillBalance())
                .map(bill -> bill.getBillBalance()).reduce(zero, (a, b) -> a.add(b));

        result.setErrorTotalAmount(errorTotalAmount.toString());
        result.setMatchTotalAmount(matchTotalAmount.toString());


        WebResult webResult = WebResult.ok();

        webResult.setData(result);


        logger.info("返回处理结果：" + webResult);

        return webResult;


    }


    @RequestMapping(value = "/saveBills", method = RequestMethod.POST)
    @ResponseBody
    public WebResult saveBills(@RequestBody BillDetailsResult billDetailsResult) throws AbsException {


        List<BillDetails> matchList = billDetailsResult.getMatchList();

        if(matchList.size()==0){
            return WebResult.error("提交失败，没有可匹配的账单信息");
        }

        logger.info("保存上传的账单文件："+billDetailsResult);
        invoiceService.saveMatchBills(matchList);

        invoiceService.saveAllBills(billDetailsResult);

        //对冲销的资产进行回款流程
        aBSAssetsQuartz.backPayment();

        //补充资产和替换资产
        aBSAssetsQuartz.replaceBaseAssets();

        WebResult ok = WebResult.ok();
        ok.setData("保存账单成功");

        return ok;
    }


    //查询
    @RequestMapping(value = "/queryBills", method = RequestMethod.POST)
    @ResponseBody
    public WebResult queryBills(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody BillQueryParam queryParam) {


        //只能查询自己机构的基础资产
        String gname = UserUtils.getUser().getGname();

        queryParam.setOriginalHolder(gname);

        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<PeAccountsReceivable> list = peAccountsReceivableMapper.selectByQueryParam(queryParam);


        PageInfo<PeAccountsReceivable> pageInfo = new PageInfo(list);

        WebResult result = WebResult.ok();
        result.setData(pageInfo);

        return result;
    }


    /**
     * 文件下载
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadExcelTemplate", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) {
        UpLoadUtil u = new UpLoadUtil();
        String filePath = "友和渠道上传模板.zip";
        String fileName = "友和渠道上传模板.zip";
        try {
        u.downloadFile(filePath, fileName, excelPath, request, response);

        } catch (UnsupportedEncodingException e) {
            logger.error("【文件下载】download"+e);
        }
    }


}
