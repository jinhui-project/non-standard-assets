package com.jinhui.exception;

import com.jinhui.model.WebResult;
import com.mysql.jdbc.MysqlDataTruncation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理，捕获所有Controller中抛出的异常。
 * Created by luoyuanq on 2017/9/28 0028.
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //处理AbsException异常
    @ExceptionHandler(AbsException.class)
    @ResponseBody
    public WebResult customHandler(AbsException e){

        WebResult result=WebResult.error(String.valueOf(e.getCode()),e.getMsg());

        return result;
    }



    //处理MysqlDataTruncation异常
    @ExceptionHandler(MysqlDataTruncation.class)
    @ResponseBody
    public WebResult customHandler(MysqlDataTruncation e){
        logger.error(e.getMessage(),e);
        WebResult result=WebResult.error("数据保存错误");

        return result;
    }


    //处理BizException业务异常
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public WebResult customHandler(BizException e){
        logger.error(e.getMessage(),e);
        WebResult result=WebResult.error(String.valueOf(e.getCode()),e.getMsg());

        return result;
    }

    //其他未处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception e){

        logger.error(e.getMessage(),e);

        WebResult result=WebResult.error("系统处理异常，请联系技术人员");

        return result;
    }


}
