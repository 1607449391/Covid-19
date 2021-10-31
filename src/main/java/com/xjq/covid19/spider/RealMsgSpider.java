package com.xjq.covid19.spider;

import com.xjq.covid19.bean.Message;
import com.xjq.covid19.mapper.RealMsgSpiderMapper;
import com.xjq.covid19.util.TimeUtil;
import com.xjq.covid19.util.WebClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/*
 *@author：徐家庆
 *@time：2020-12-18 19:23
 *@description：
 *
 */
@Service
public class RealMsgSpider {


    private static long  timeWy = 1614581798000L;
    private static long  timeDXY = 0;
    private static long  timeALi = 1614581798000L;
    private static long  timeSG = 1614581798000L;

    @Autowired
    RealMsgSpiderMapper realMsgSpiderMapper;


    /**
     * 爬取网易新闻的
     * 实时播报信息
     */
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void getWYData(){
        String url = "https://wp.m.163.com/163/page/news/virus_report/index.html?_nw_=1&_anw_=1";
        WebClientUtil webUtil = WebClientUtil.getInstance();
        String html = webUtil.getHtmlPageResponse(url);
        Document dom = Jsoup.parse(html);
        Element time_lists = dom.getElementById("time_lists");
        Elements lis = time_lists.getElementsByTag("li");
        List<Message> messages = new ArrayList<>();
        for (Element li : lis) {
            Message message = new Message();
            String time = li.getElementsByClass("time").get(0).text();//2020.12.19 15:19:43
            long time1 = TimeUtil.getTimestampForWY(time);
            if(time1 <= timeWy){
                break;
            }
            message.setTime(time1);
            String title = li.getElementsByClass("tit").get(0).text();
            message.setTitle(title);
            String newsUrl = li.getElementsByClass("con").get(0).attr("data-url");
            message.setUrl(newsUrl);
            messages.add(message);
        }
        if(messages.size()>0){
            timeWy = messages.get(0).getTime();
            realMsgSpiderMapper.insertWYMessage(messages);
        }

    }

    /**
     * 爬取丁香园的实时播报
     */
    //@Scheduled(cron = "0 5 0/2 * * ?")
    public void getDXYData(){
        String url  = "https://ncov.dxy.cn/ncovh5/view/pneumonia";
        WebClientUtil webUtil = WebClientUtil.getInstance();
        String html = webUtil.getHtmlPageResponse(url);
        Document dom = Jsoup.parse(html);
        List<Message> messages = new ArrayList<>();
        Elements elements = dom.getElementsByClass("block___wqUAz");
        for (Element ele : elements){
            Message message = new Message();
            String date = ele.select(".leftTime___2zf53 span").text();  //12-19 11:07
            long date1 = TimeUtil.getTimestampForDXY(date);
            if(date1 <= timeDXY){
                break;
            }
            message.setTime(date1);
            String title = ele.getElementsByClass("topicTitle___2ovVO").get(0).text();
            message.setTitle(title);
            String content = ele.getElementsByClass("topicContent___1KVfy").get(0).text();
            message.setContent(content);
            String source = ele.getElementsByClass("topicFrom___3xlna").get(0).text();
            message.setSource(source);
            messages.add(message);
        }
        if(messages.size()>0){
            timeDXY = messages.get(0).getTime();
            realMsgSpiderMapper.insertDXYMessage(messages);
        }
    }


    /**
     * 爬取阿里健康的实时播报
     */
    @Scheduled(cron = "0 10 0/2 * * ?")
    public void getALiData(){
        String url = "https://alihealth.taobao.com/medicalhealth/influenzamap";
        WebClientUtil webUtil  = WebClientUtil.getInstance();
        String html = webUtil.getHtmlPageResponse(url);
        Document dom = Jsoup.parse(html);
        List<Message> messages = new ArrayList<>();
        Elements elements = dom.select("div.each-timeline");
        for(int i = 0;i < 4;i++){
            Element element = elements.get(i);
            Message message   = new Message();
            String time = element.getElementsByClass("info-time").get(0).text();
            if(i == 0){
                time = time.replace("最新","");
            }
            long time1 = TimeUtil.getTimestampForALi(time);
            if(time1 <= timeALi){
                break;
            }
            message.setTime(time1);
            String title = element.getElementsByClass("info-title").get(0).text();
            message.setTitle(title);
            String content = element.getElementsByClass("info-desc").get(0).text();
            message.setContent(content);
            String source = element.getElementsByClass("info-source").get(0).text();
            message.setSource(source);
            messages.add(message);
        }
        if(messages.size()>0){
            timeALi = messages.get(0).getTime();
            realMsgSpiderMapper.insertALIMessage(messages);
        }
    }

    /**
     * 爬取搜狗的实时播报
     */
    @Scheduled(cron = "0 15 0/2 * * ?")
    public void getSGData(){
        String url = "http://sa.sogou.com/new-weball/page/sgs/epidemic";
        WebClientUtil webUtil  = WebClientUtil.getInstance();
        String html = webUtil.getHtmlPageResponse(url);
        Document dom = Jsoup.parse(html);
        List<Message> messages = new ArrayList<>();
        Elements elements = dom.select("div.mod");
        for (Element element : elements) {
            Message message = new Message();
            String time = element.select("span.t2").get(0).text();
            long time1 = TimeUtil.getTimestampForDXY(time);
            if(time1 <= timeSG){
                break;
            }
            message.setTime(time1);
            String title = element.select("a h3").get(0).text();
            message.setTitle(title);
            String content = element.select("p.info").get(0).text();
            message.setContent(content);
            String source = element.select("p.src").get(0).text();
            message.setSource(source);
            String newsUrl = element.select("a.content").get(0).attr("data-url");
            message.setUrl(newsUrl);
            System.out.println(message);
            messages.add(message);
        }
        if(messages.size()>0){
            timeSG = messages.get(0).getTime();
            realMsgSpiderMapper.insertSGMessage(messages);
        }
    }

}
