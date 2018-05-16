package com.jinhui.profile;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

/**
 * 预设系统参数的监听器,在web容器创建{@link javax.servlet.ServletContext}时执行操作
 * <p>该监听器配置在servlet应用的web.xml中，一般作为首个listener配置，务必放在
 * {@link org.springframework.web.context.ContextLoaderListener}之前
 * <p>通过读取环境变量中的deploy.env来生成环境标识，并存入到Java系统属性中
 */
public class PropertyConfigurationListener implements ServletContextListener {

    /** 预先设置的环境类型，DEV,TEST,PROD等 */
    public final static String DEPLOY_ENV_KEY        = "deploy.env";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.resolveProfileValue();
    }

    private void resolveProfileValue() {
        String profileValue = "";
        if (SystemPropertyUtil.contains(DEPLOY_ENV_KEY)) {
            profileValue = SystemPropertyUtil.get(DEPLOY_ENV_KEY);
        } else {
            throw new IllegalStateException(
                    "获取不到'" + DEPLOY_ENV_KEY
                            + "'的值,检查是否在 '.profile'配置中配置或在JVM启动时加上参数'-D"
                            + DEPLOY_ENV_KEY + "'。");
        }
        SystemPropertyUtil.set(ACTIVE_PROFILES_PROPERTY_NAME, profileValue.toLowerCase());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //Do nothing!
    }
}
