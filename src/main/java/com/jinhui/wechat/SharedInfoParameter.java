package com.jinhui.wechat;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 使用pojo而不是map来封装配置型参数，提高业务代码可读性;
 * 使用Builder模式来适应多样的参数配置.
 */
public class SharedInfoParameter {

    private Parameter<String> timestamp;//: $timestamp,
    private Parameter<String> nonceStr;//: '$nonceStr',
    private Parameter<String> signature;//: '$signature',
    private Parameter<String> title;//: '$title',
    private Parameter<String> link;//: '$link'
    private Parameter<String> ticket;//'$jsapi_ticket'

    /*public SharedInfoParameter(String timestamp, String nonceStr, String signature,
                               String title, String link) {
        this.timestamp = new ParameterImpl("$timestamp",timestamp);
        this.nonceStr = new ParameterImpl("$nonceStr", nonceStr);
        this.signature = new ParameterImpl("$signature", signature);
        this.title = new ParameterImpl("$title", title);
        this.link = new ParameterImpl("$link", link);
    }*/

    private SharedInfoParameter() {
    }

    public static class Builder {
        final SharedInfoParameter sharedInfoParameter;

        public Builder() {
            sharedInfoParameter = new SharedInfoParameter();
        }
        public Builder timestamp(String timestamp){
            if(StringUtils.isEmpty(timestamp))
                throw new IllegalArgumentException("timestamp不能为空");
            sharedInfoParameter.timestamp = new Parameter.ParameterImpl("$timestamp",timestamp);
            return this;
        }
        public Builder nonceStr(String nonceStr) {
            if(StringUtils.isEmpty(nonceStr))
                throw new IllegalArgumentException("nonceStr不能为空");
            sharedInfoParameter.nonceStr = new Parameter.ParameterImpl("$nonceStr", nonceStr);
            return this;
        }

        public Builder signature(String signature) {
            if(StringUtils.isEmpty(signature))
                throw new IllegalArgumentException("signature不能为空");
            sharedInfoParameter.signature = new Parameter.ParameterImpl("$signature", signature);
            return this;
        }

        public Builder title(String title) {
            if(StringUtils.isEmpty(title))
                throw new IllegalArgumentException("title不能为空");
            sharedInfoParameter.title = new Parameter.ParameterImpl("$title", title);
            return this;
        }

        public Builder link(String link) {
            Assert.notNull(link, "link为null");
            sharedInfoParameter.link = new Parameter.ParameterImpl("$link", link);
            return this;
        }

        public Builder ticket(String ticket) {
            if(StringUtils.isEmpty(ticket))
                throw new IllegalArgumentException("title不能为空");
            sharedInfoParameter.ticket = new Parameter.ParameterImpl("$jsapi_ticket", ticket);
            return this;
        }
        public SharedInfoParameter build(){
            return sharedInfoParameter;
        }
    }

    public Parameter<String> timestamp() {
        return timestamp;
    }

    public Parameter<String> nonceStr() {
        return nonceStr;
    }

    public Parameter<String> signature() {
        return signature;
    }

    public Parameter<String> title() {
        return title;
    }

    public Parameter<String> link() {
        return link;
    }

    public Parameter<String> ticket() {
        return ticket;
    }

    @Override
    public String toString() {
        return "SharedInfoParameter{" +
                "timestamp=" + timestamp +
                ", nonceStr=" + nonceStr +
                ", signature=" + signature +
                ", title=" + title +
                ", link=" + link +
                ", ticket=" + ticket +
                '}';
    }
}
