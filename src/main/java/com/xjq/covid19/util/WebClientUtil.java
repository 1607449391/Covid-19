package com.xjq.covid19.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


import java.io.IOException;

/*
 *@author：徐家庆
 *@time：2020-12-19 15:02
 *@description：
 *
 */

public class WebClientUtil {
    //超时时间 默认为60000ms
    private int timeout = 60000;
    //等待js执行的时间  默认为20000ms
    private int waitForBackgroundJavaScript = 50000;

    //单例模式
    private static WebClientUtil httpUtil;

    //获取httpUtil实例对象
    public static WebClientUtil getInstance(){
        if(httpUtil == null){
            httpUtil = new WebClientUtil();
        }
        return httpUtil;
    }

    /**
     * 爬取页面源码返回string类型
     * @param url
     * @return  页面源码
     *
     */
    public String  getHtmlPageResponse(String url){
        String result = "";
        //1.创建浏览器客户端，设置为谷歌浏览器
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //2.为浏览器设置相关参数
        //当js执行出错时是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        //当响应的状态码不是200时是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        //是否开启ActiveX组件
        webClient.getOptions().setActiveXNative(false);
        //是否启用css样式
        webClient.getOptions().setCssEnabled(false);
        //是否开启js  爬取动态页面必须开启
        webClient.getOptions().setJavaScriptEnabled(true);
        //设置支持ajax
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        //设置请求超时时间
        webClient.getOptions().setTimeout(timeout);
        //设置等待js执行时间
        webClient.setJavaScriptTimeout(waitForBackgroundJavaScript);

        try {
            HtmlPage htmlPage = webClient.getPage(url);
            //执行js  阻塞线程
            webClient.waitForBackgroundJavaScript(waitForBackgroundJavaScript);
            result = htmlPage.asXml();
            webClient.close();
        } catch (IOException e) {
            webClient.close();   //出现异常 ，关闭客户端对象
            e.printStackTrace();
        }
        return result;
    }

    public WebClientUtil() {
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getWaitForBackgroundJavaScript() {
        return waitForBackgroundJavaScript;
    }

    public void setWaitForBackgroundJavaScript(int waitForBackgroundJavaScript) {
        this.waitForBackgroundJavaScript = waitForBackgroundJavaScript;
    }
}
