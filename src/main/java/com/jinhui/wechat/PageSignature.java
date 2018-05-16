package com.jinhui.wechat;

import com.alibaba.fastjson.JSONObject;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jinhui on 2017/9/29.
 */
public class PageSignature {

    private static Logger logger = LoggerFactory.getLogger(PageSignature.class);

    private static final String TicketKey = "ticket";
    private static final String TicketUri = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?" +
            "access_token=$ACCESS_TOKEN&" +
            "type=jsapi";
    private static final String SignatureString = "jsapi_ticket=$jsapi_ticket&" +
            "noncestr=$nonceStr&" +
            "timestamp=$timestamp&" +
            "url=$link";
    protected static final String WechatJs = "wechat.js";
    /**
     * 由于前后端完全分离,前端为"单页面"应用,在浏览器解释js前,前端页面必须静态配置好signature;
     * 因此，选择在后台为前端动态生成signature的js配置文件，供前端通过<script/>标签动态获取。
     * 后台需要预先配置好页面分享信息，并根据分享配置信息，针对不同页面动态生成js分享配置文件。
     */
    private static String SharedConfig = "";
    private static final Properties SharedInfo = new Properties();
    static {
        try(InputStreamReader in = new InputStreamReader(
                PageSignature.class.getResourceAsStream("/wechat/shared_info.properties"), "UTF-8")) {
            SharedInfo.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(
                PageSignature.class.getResourceAsStream("/wechat/shared_config.js")))) {
            String tmp = null;
            while ((tmp=reader.readLine()) != null){
                SharedConfig += tmp.trim();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected static final String WechatJsAbsPath =
            new File(PageSignature.class.getResource("/").getPath())
                    .getParent()+"/wechat/";
    static {
        File wechatJsPath = new File(WechatJsAbsPath);
        if(!wechatJsPath.exists()) {
            wechatJsPath.mkdirs();
        }
    }
    /**
     * 如果signature过期或者不存在，则需要刷新signature文件
     * @param request
     */
    public static void refreshSignature(final HttpServletRequest request){
        CompositionHttpClient.httpsRequest(new CompositionHttpClient.Action() {
            @Override
            public void doAction(HttpClient httpClient)
                    throws InterruptedException, ExecutionException, TimeoutException {
                requestSignature(httpClient, request);
            }
        });
    }
    /**
     * 请求signature, 生成分享配置js文件
     * @param request
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    private static void requestSignature(HttpClient httpClient, final HttpServletRequest request)
            throws InterruptedException, ExecutionException, TimeoutException {
        //access token
        Parameter<String> accessToken = WechatAuthorizeManager.requestIfAbsentOpenApiToken(httpClient);
        //ticket
        String ticket = WechatDataCache.instance().get(TicketKey);
        if(StringUtils.isEmpty(ticket)) {
            ticket = requestTicket(httpClient, accessToken);
            WechatDataCache.instance().put(TicketKey, ticket);
        }
        //
        genSignatureAndWechatJs(request, ticket);
    }

    /**
     * 获取ticket，详解见{@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115}
     * @param httpClient
     * @param accessToken
     * @return
     */
    private static String requestTicket(HttpClient httpClient, Parameter<String> accessToken)
            throws InterruptedException, ExecutionException, TimeoutException {
        String ticketUri = TicketUri.replace(accessToken.key(), accessToken.value());
        if (logger.isInfoEnabled())
            logger.info("ticket请求={}", ticketUri);
        ContentResponse wechatResp = httpClient.GET(ticketUri);
        if (logger.isInfoEnabled())
            logger.info("请求ticket返回: {}", wechatResp.getContentAsString());
        JSONObject jsonObject = JSONObject.parseObject(wechatResp.getContentAsString());
        String ticket = jsonObject.getString(TicketKey);
        if (StringUtils.isEmpty(ticket)) {
            logger.warn("获取微信ticket失败");
            return null;
        }
        return ticket;
    }

    /**
     * 按照{@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115}中的说明，
     * 获取signature需要{@param ticket}; 每一个url对应一个signature;
     * 由于前后端完全分离,前端为"单页面"应用,在浏览器解释js前,前端页面必须静态配置好signature;
     * 因此，选择在后台为前端动态生成signature的js配置文件，供前端通过<script/>标签动态获取。
     * @param request
     * @param ticket
     */
    private static void genSignatureAndWechatJs(final HttpServletRequest request, String ticket){
        String timestamp = System.currentTimeMillis()/1000+"";
        String noncestr = UUID.randomUUID().toString();
        final String relativeUrl = WechatAuthorizeManager.RequestRelativeUrl(request);
        String signatureUrl = relativeUrl.equals("")
                        ? WechatAuthorizeManager.Homepage :
                          WechatAuthorizeManager.Domain + relativeUrl;
        SharedInfoParameter.Builder parameterBuilder = new SharedInfoParameter.Builder();
        parameterBuilder.ticket(ticket);
        parameterBuilder.timestamp(timestamp);
        parameterBuilder.nonceStr(noncestr);
        String sharedKey = "";
        String matchUrl = relativeUrl.equals("") ?  "/" : relativeUrl;
        for(String key:SharedInfo.stringPropertyNames()){
            if(matchUrl.matches(key)) {
                sharedKey = key;
                break;
            }
        }
        if(!StringUtils.isEmpty(sharedKey)) {
            parameterBuilder.title(SharedInfo.getProperty(sharedKey));
            parameterBuilder.link(signatureUrl);
        } else {
            //所有未配置的分享页面，都转为分享主页
            parameterBuilder.title(SharedInfo.getProperty("/"));
            parameterBuilder.link(WechatAuthorizeManager.Domain);
        }
        SharedInfoParameter sharedInfoParameter = parameterBuilder.build();
        String sig_src = SignatureString
                .replace(sharedInfoParameter.ticket().key(), sharedInfoParameter.ticket().value())
                .replace(sharedInfoParameter.nonceStr().key(), sharedInfoParameter.nonceStr().value())
                .replace(sharedInfoParameter.timestamp().key(), sharedInfoParameter.timestamp().value())
                .replace(sharedInfoParameter.link().key(), signatureUrl);
        String signature = org.springframework.data.redis.core.script.DigestUtils.sha1DigestAsHex(sig_src);
        if(logger.isInfoEnabled()){
            logger.info("signature src => {},\nsignature={}",sig_src, signature);
        }
        parameterBuilder.signature(signature);
        generateWechatJs(WechatJsFilename(request, WechatJs), parameterBuilder.build());
    }

    /**
     * 在{@param WechatJsAbsPath}目录下生成{@param jsFilename}文件。
     * 使用{@param WechatJsAbsPath}作为key，标明该目录下的数据文件，以目录的缓存周期算：
     * 也即是说，当{@param WechatJsAbsPath}对应的key被清理时，该目录下的所有缓存数据也就失效。
     * @param jsFilename
     */
    private static void generateWechatJs(String jsFilename, final SharedInfoParameter parameters){
        if(logger.isInfoEnabled()) {
            logger.info("SharedInfoParameter ==> {}", parameters.toString());
        }
        WechatDataCache.instance().put(WechatJsAbsPath, new WechatDataCache.FileValuesAction(jsFilename) {
            @Override
            String doAction() {
                String sharedConfig = SharedConfig
                        .replace(parameters.timestamp().key(), parameters.timestamp().value())
                        .replace(parameters.nonceStr().key(), parameters.nonceStr().value())
                        .replace(parameters.signature().key(), parameters.signature().value())
                        .replace(parameters.title().key(), parameters.title().value())
                        .replace(parameters.link().key(),
                                parameters.link().value().replace(WechatAuthorizeManager.HomepagePath,""));
                File tmp = new File(WechatJsAbsPath +value());
                if(!tmp.exists()){
                    try {
                        if(logger.isInfoEnabled())
                            logger.info("创建文件:{}", tmp.getAbsolutePath());
                        tmp.createNewFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp))) {
                    bw.write(sharedConfig);
                } catch (Exception  e){
                    throw new RuntimeException(e);
                }
                return value();
            }
        });
    }

    /**
     * 实际根据request所属的session中存放js的宿主页面url来生成对应的js签名数据文件
     * @return 返回真实的js签名数据文件
     */
    public static String WechatJsFilename(HttpServletRequest request, String endWith){
        String relativeUrl = WechatAuthorizeManager.RequestRelativeUrl(request);
        return relativeUrl.replace("/","_")+"_"+endWith;
    }

    public static void main(String[] strs){
        for(Object key :SharedInfo.keySet()){
            System.out.println(SharedInfo.get(key));
        }
    }

    /**
     * 检查js文件是否缓存(有效)
     * @param jsFilename
     */
    public static boolean exists(String jsFilename){
        return WechatDataCache.instance().exists(PageSignature.WechatJsAbsPath, jsFilename);
    }
}
