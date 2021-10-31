package com.xjq.covid19.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjq.covid19.bean.WordData;
import com.xjq.covid19.mapper.WordDataSpiderMapper;
import com.xjq.covid19.util.HttpClintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
 *@author：徐家庆
 *@time：2020-12-14 19:23
 *@description：
 *
 */
@Service
public class WordDataSpider {
    @Autowired
    HttpClintUtil httpClintUtil;

    @Autowired
    WordDataSpiderMapper wordDataMapper;

    /**
     *插入海外历史数据
     */
    public void insertHistoryData(){
        String url = "https://interface.sina.cn/news/wap/fymap2020_data.d.json";
        String html = httpClintUtil.doGetPage(url);
        List<WordData> list = ProcessHtml(html,true);
        wordDataMapper.insertWordHistData(list);
    }

    /**
     * 每天17:30开始插入昨日数据
     */
    @Scheduled(cron = "0 30 17 * * ?")
    public void insertYesterdayData(){
        String url = "https://interface.sina.cn/news/wap/fymap2020_data.d.json";
        String html = httpClintUtil.doGetPage(url);
        List<WordData> list = ProcessHtml(html,false);
        wordDataMapper.insertWordHistData(list);
    }
    public List<WordData> ProcessHtml(String html, boolean isHistory){
        JSONObject htmlJson = JSON.parseObject(html);
        String data = htmlJson.getString("data");
        JSONObject dataJson = JSON.parseObject(data);
        String otherhistorylist = dataJson.getString("otherhistorylist");
        System.out.println(otherhistorylist);
        JSONArray objects = JSON.parseArray(otherhistorylist);
        List<WordData> wordDatas = new ArrayList<>();
        int index = 1;
        int start = 0;
        if(isHistory == true){
            start = 0;
            index = objects.size();
        }
        for (int i = start;i < index;i++){
            WordData whd = new WordData();
            JSONObject historysJson = objects.getJSONObject(i);
            whd.setConfirm(historysJson.getInteger("certain"));
            whd.setSuspect(historysJson.getInteger("uncertain"));
            whd.setDead(historysJson.getInteger("die"));
            whd.setHeal(historysJson.getInteger("recure"));
            whd.setConfirmInc(historysJson.getInteger("certain_inc"));
            whd.setSuspectInc(historysJson.getInteger("uncertain_inc"));
            whd.setDeadInc(historysJson.getInteger("die_inc"));
            whd.setHealInc(historysJson.getInteger("recure_inc"));
            String[] dates = historysJson.getString("date").split("\\.");
           // String dateId = "2021" + dates[0] + dates[1];
            String dateId = dates[0] + dates[1] + dates[2];
            whd.setDateId(Integer.parseInt(dateId));
            float confirm = whd.getConfirm();
            float dead = whd.getDead();
            float heal = whd.getHeal();
            BigDecimal deadBd = new BigDecimal(dead/confirm*100);
            BigDecimal healBd = new BigDecimal(heal/confirm*100);
            whd.setDeadRate(deadBd.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue());
            whd.setHealRate(healBd.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue());
            wordDatas.add(whd);
        }
        return wordDatas;
    }

}
