package com.xjq.covid19.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
 *@author：徐家庆
 *@time：2020-12-08 17:48
 *@description：
 *
 */
@Component
public class HttpClintUtil {
    //创建httpclient线程池
    private PoolingHttpClientConnectionManager cm;

    //初始化线程池对象
    public HttpClintUtil(){
        this.cm = new PoolingHttpClientConnectionManager();
        this.cm.setMaxTotal(100);    //设置最大线程对象
        this.cm.setDefaultMaxPerRoute(10);//设置每个连接最大线程对象数
    }

    /**
     *  get方式请求url
     * @param url
     * @return   页面源码
     */
    public String doGetPage(String url){
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(getRequestConfig());
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0");
        CloseableHttpResponse response = null;

        try {
                response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                return EntityUtils.toString(response.getEntity(),"utf8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";

    }

    /**
     * httpclient配置
     */

    public RequestConfig getRequestConfig(){
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(100000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .build();

        return requestConfig;
    }
}
