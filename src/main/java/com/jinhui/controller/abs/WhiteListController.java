package com.jinhui.controller.abs;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinhui.exception.AbsException;
import com.jinhui.mapper.abs.PeWhiteListMapper;
import com.jinhui.model.Users;
import com.jinhui.model.WebResult;
import com.jinhui.model.abs.PeWhiteList;
import com.jinhui.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 应收债务人白名单
 * Created by Administrator on 2017/9/30 0030.
 */

@Controller
@RequestMapping("/abs/white")
public class WhiteListController {

    private static Logger logger = LoggerFactory.getLogger(WhiteListController.class);


    @Autowired
    private PeWhiteListMapper peWhiteListMapper;


    @RequestMapping(value = "/savePeWhiteList", method = RequestMethod.POST)
    @ResponseBody
    public WebResult savePeWhiteList(@RequestBody PeWhiteList peWhiteList) throws AbsException {

        //TODO 检查数据

        peWhiteList.setStatus("1");//1代表生效
        peWhiteList.setCreateTime(new Date());

        String gName = UserUtils.getUser().getGname();
        peWhiteList.setOriginalHolder(gName);
        peWhiteList.setCreator(UserUtils.getUserName());
        try {
            int i = peWhiteListMapper.insert(peWhiteList);
        } catch (DuplicateKeyException e) {
            logger.error("已经存在相同应收债务人:" +peWhiteList.getReceivableDebtor() , e);
            throw new AbsException("已经存在相同应收债务人:" +peWhiteList.getReceivableDebtor(), e);
        }

        return WebResult.ok();

    }


    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    @ResponseBody
    public WebResult queryPeWhiteList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize,
                                      String debtor, String periodStart, String periodStop) {

        Users user = UserUtils.getUser();

        String gname=user.getGname();
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);

        List<PeWhiteList> whiteLists = peWhiteListMapper.selectByQueryParam(gname, debtor, periodStart, periodStop);

        PageInfo<PeWhiteList> pageInfo = new PageInfo(whiteLists);

        WebResult result = WebResult.ok();
        result.setData(pageInfo);

        return result;

    }

    @RequestMapping(value = "/queryByName", method = RequestMethod.GET)
    @ResponseBody
    public WebResult queryByName(String debtor) {

        logger.info("输入：  "+ "debtor="+debtor);
        String gName = UserUtils.getUser().getGname();

        List<PeWhiteList> list = peWhiteListMapper.selectByName(gName, debtor);

        ArrayList<String> nameList = new ArrayList();
        for (PeWhiteList white : list) {
            nameList.add(white.getReceivableDebtor());
        }

        WebResult result = WebResult.ok();
        result.setData(nameList);

        return result;

    }


    @RequestMapping(value = "/updatePeWhiteList", method = RequestMethod.POST)
    @ResponseBody
    public WebResult updatePeWhiteList(@RequestBody PeWhiteList peWhiteList) {


        //只能更新应收债务人的合同和账期

        String gname = UserUtils.getUser().getGname();
        peWhiteList.setOriginalHolder(gname);

        int i = peWhiteListMapper.updateByDebtor(peWhiteList);

        return WebResult.ok();

    }


}
