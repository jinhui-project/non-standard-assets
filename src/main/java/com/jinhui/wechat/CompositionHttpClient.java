package com.jinhui.wechat;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jinhui on 2017/9/26.
 */
public class CompositionHttpClient {

    private static final Long timeout = 60000L;

    public static void httpsRequest(Action action){
        SslContextFactory sslContextFactory = new SslContextFactory();
        HttpClient httpClient = new HttpClient(sslContextFactory);
        try {
            httpClient.start();
            httpClient.setConnectTimeout(timeout);
            httpClient.setFollowRedirects(false);
            action.doAction(httpClient);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(httpClient.isStarted() || httpClient.isStarting())
                    httpClient.stop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    interface Action {
        void doAction(HttpClient httpClient)
                throws InterruptedException, ExecutionException, TimeoutException;
    }

}
