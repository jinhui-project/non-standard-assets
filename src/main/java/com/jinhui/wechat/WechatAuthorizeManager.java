package com.jinhui.wechat;


import com.alibaba.fastjson.JSONObject;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jinhui on 2017/9/18.
 */
public class WechatAuthorizeManager {

    private static Logger logger = LoggerFactory.getLogger(WechatAuthorizeManager.class);
    // TODO: 2017/9/22 考虑怎样更好的将这些常量放在properties等配置文件
    public static final String WechatBrowerFlag = "micromessenger";
    public static final String RequestUrlKey = "request_url";
    public static final String appid = "wxc8676e52af4c87ed";
    public static final String secret = "4d99896df648899519f3d1a385c74d4c";

    //获取openid之后的重定向主页
    public static final String HomepagePath = "/homepage";
    public static final String Domain = "http://m.jinfeibiao.com";
    public static final String Homepage = Domain+HomepagePath;
    public static final String WechatAuthCodeUrl = "/authentication/code";
    private static final String WechatLogin = "/wechatLogin";
    private final static String userAgent = "user-agent";


    public static final String OpenApiTokenUri = "https://api.weixin.qq.com/cgi-bin/token?" +
            "grant_type=client_credential&" +
            "appid="+appid+"&" +
            "secret="+secret;
    public static final String OpenApiTokenKey = "access_token";
    public static final String WechatContextPath = "/wechat";

    private WechatAuthorizeManager() {
    }

    /**
     * 从微信发往后台的http请求分两类:
     * 1, 微信浏览器自身发出的请求(不存在跨域问题), 该类请求可以理解为静态资源请求，
     *    包括.html, .js, .css和图片等资源请求+;
     * 2, 类ajax请求(存在跨域问题).
     * 如果是微信浏览器发出的请求，要么经过反向代理将重定向过来的静态资源的请求url上加入{@param WechatContextPath}，
     * 要么对html文件中的静态url加入{@param WechatContextPath}.
     * @param request
     * @return
     */
    public static boolean isWechatRequest(HttpServletRequest request){
        return request.getRequestURI().contains(WechatContextPath) &&
                request.getHeader(userAgent).toLowerCase().contains(WechatBrowerFlag);
    }

    public static boolean fromWechatRequest(HttpServletRequest request){
        return request.getHeader(userAgent).toLowerCase().contains(WechatBrowerFlag);
    }

    /**
     * 在完全的前后端分离应用里， 静态资源通常不会由servlet来返回，而是直接由反向代理服务器直接返回；
     * 然而，在特殊情况下，如微信分享的前端页面signature文件需要后台代为生成,以.js文件的形式返回给前端,
     * 以及OAuth授权访问等,需要后台服务依据前端的页面请求完成OAuth和页面signature。
     * 因此，为了让后台感知到浏览器发出了页面请求(而不仅是ajax发出的数据请求),
     * 反向代理服务器应当将某些静态资源请求加入{@param WechatContextPath}路径后，转发至后台服务器;
     * 而对于完全前后端分离的单页面应用，用户在做页面切换时，前端的页面路由时，应当主动调起浏览器刷新当前页面，
     * 使得后台能感知到页面跳转的动作。
     * 此外，只有.js或html页面可能会进入servlet请求:
     * 1, 对于html页面的请求,用于OAuth授权访问;
     * 2, 对于.js的请求, 用于在前后端完全分离的场景下，用于返回signature等。
     * 对于每一个微信客户端发出的页面请求，在当前session中缓存当前请求的页面路径，为后续处理提供依据：
     * 1，OAuth获取微信授权后，页面恢复；
     * 2，请求signature等由后台动态生成的静态资源文件时，识别宿主页面。
     */
    public static boolean wechatDispatcher(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String url = request.getRequestURI();
        //查询微信授权和登录的状态，不需要拦截
        if(url.contains(WechatLogin))
            return true;
        //微信服务器通过微信浏览器重定向过来并携带auth code的url, 不需要拦截
        if(url.contains(WechatAuthCodeUrl))
            return true;
        if(!url.endsWith(".js")) {
            //既然是html页面请求, 则需要在session中保存当前请求的页面相对路径,
            //一方面可以，在用于OAuth微信授权后，页面重定向恢复；
            //另一方面，可以用于定位后续.js等静态资源文件所归属的宿主页面。
            pushRelativeUrl(request);
        }
        if(!OAuth.existsOpenid(request, response)) {
            //完成OAuth授权动作
            return false;
        }
        //signature js文件
        String wechatJsFilename = PageSignature.WechatJsFilename(request, PageSignature.WechatJs);
        if(url.endsWith(wechatJsFilename) || url.endsWith(PageSignature.WechatJs)) {
            return locateSignatureJs(request, response);
        }
        redirectRequest(request, response);
        return false;
    }

    public static String getOpenId(HttpServletRequest request) {
        return OAuth.getOpenIdFromSession(request);
    }

    /**
     * {@link com.jinhui.controller.user.WechatLoginController#wechatCode}
     * 会调起该方法。
     * 向微信服务器请求授权凭证，并存入session
     * @param request
     * @param code
     */
    public static void requestWechat(final HttpServletRequest request, final String code){
        OAuth.requestWechat(request, code);
    }

    /**
     * {@link com.jinhui.controller.user.WechatLoginController#wechatCode}
     * 获取到微信授权凭证后，会调起该方法。
     * 获取微信授权凭证后，在session中获取先前存入的url，并告知微信浏览器重定向到正确的访问页面
     * @param request
     * @param response
     */
    public static void redirectRequest(HttpServletRequest request, HttpServletResponse response){
        String requestUrl = RequestRelativeUrl(request);
        if(logger.isInfoEnabled()){
            logger.info("请求重定向 url => {}", Homepage +requestUrl);
        }
        try {
            response.sendRedirect(Homepage +requestUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取OpenApiToken， 详解见{@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183}
     * @param httpClient
     * @return
     */
    public static Parameter<String> requestIfAbsentOpenApiToken(HttpClient httpClient)
            throws InterruptedException, ExecutionException, TimeoutException {
        //access token
        String accessToken = WechatDataCache.instance().get(WechatAuthorizeManager.OpenApiTokenKey);
        if(StringUtils.isEmpty(accessToken)) {
            ContentResponse wechatResp = httpClient.GET(WechatAuthorizeManager.OpenApiTokenUri);
            if (logger.isInfoEnabled())
                logger.info("请求OpenApiToken返回: {}", wechatResp.getContentAsString());
            JSONObject jsonObject = JSONObject.parseObject(wechatResp.getContentAsString());
            accessToken = jsonObject.getString(WechatAuthorizeManager.OpenApiTokenKey);
            if (StringUtils.isEmpty(accessToken)) {
                logger.warn("获取微信OpenApiToken失败");
                return null;
            }
            WechatDataCache.instance().put(WechatAuthorizeManager.OpenApiTokenKey, accessToken);
        }
        return new Parameter.ParameterImpl<>("$ACCESS_TOKEN", accessToken);
    }

    private static boolean locateSignatureJs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        String wechatJsFilename = PageSignature.WechatJsFilename(request, PageSignature.WechatJs);
        if(url.endsWith(wechatJsFilename)) {
            //定位到了真实js文件，则直接正确返回.
            return true;
        }
        /**
         * 前端获取后台生成的{@param WechatJS} signature文件，每一个页面都有对应的一份签名；
         * 因此需要根据不同的页面重定向到不同的signature，
         * 也即是{@link PageSignature#WechatJsFilename}指向的文件。
         */
        if(!PageSignature.exists(wechatJsFilename)) {
            //此处只有signature过期或不存在会进入到此.
            PageSignature.refreshSignature(request);
        }
        String dispatchSignatureJs = url.replace(request.getContextPath(),"/")
                .replace(PageSignature.WechatJs, wechatJsFilename);
        request.getRequestDispatcher(dispatchSignatureJs).forward(request,response);
        return false;
    }

    /**
     * 保存当前请求页面url
     */
    private static void pushRelativeUrl(HttpServletRequest request){
        String url = request.getRequestURI();
        String relativeUrl = url.replace(request.getContextPath() + WechatContextPath, "");
        relativeUrl = !relativeUrl.endsWith("/") ? relativeUrl :
                relativeUrl.substring(0, relativeUrl.length() - 1);
        request.getSession().setAttribute(RequestUrlKey, relativeUrl);
    }

    public static String RequestRelativeUrl(HttpServletRequest request){
        return (String) request.getSession().getAttribute(RequestUrlKey);
    }

    public static void main(String[] args){
        String regrex = "/productdetail/(\\d+)";
        String[] strs = {"/productdetail/123456","/productdetail/adfsdf","/productdetail/12adf"};
        for(String str : strs){
            if(str.matches(regrex))
                System.out.println(str);
        }
    }
}
