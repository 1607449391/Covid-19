package com.xjq.covid19.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xjq.covid19.bean.ChinaData;
import com.xjq.covid19.mapper.ChinaDataSpiderMapper;
import com.xjq.covid19.util.HttpClintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/*
 *@author：徐家庆
 *@time：2020-12-09 09:57
 *@description：
 *
 */
@Service
public class ChinaDataSpider {
    @Autowired
    ChinaDataSpiderMapper chinaDataMapper;

    @Autowired
    HttpClintUtil httpClintUtil;



    /**
     * 批量插入中国疫情历史数据
     * https://view.inews.qq.com/g2/getOnsInfo?name=disease_other
     */
    public void insertChinaHistoryData(){
        String html = httpClintUtil.doGetPage("https://view.inews.qq.com/g2/getOnsInfo?name=disease_other");
        String data = JSON.parseObject(html).getString("data");
        JSONObject jsonObject = JSON.parseObject(data);
        //解析，插入历史数据
       /* String chinaDayList = jsonObject.getString("chinaDayList");
        List<ChinaHistoryData> objects = JSON.parseArray(chinaDayList, ChinaHistoryData.class);
        for (ChinaHistoryData chd:objects){
            String[] strs = chd.getDate().split("\\.");   //.在正则里面有特殊含义，需要进行转义
            String date = "2020"+strs[0]+strs[1];
            chd.setDataId(Integer.parseInt(date));
        }
        chinaDataMapper.insertChinaHistoryData(objects);*/


        //解析，插入每天新增数据
        String chinaDayAddList = jsonObject.getString("chinaDayAddList");
        List<ChinaData> days = JSON.parseArray(chinaDayAddList, ChinaData.class);
        days.remove(days.size()-1);
        for (ChinaData chd:days){
            String[] strs = chd.getDate().split("\\.");   //.在正则里面有特殊含义，需要进行转义
            String date = "2020"+strs[0]+strs[1];
            chd.setDataId(Integer.parseInt(date));
        }
        chinaDataMapper.insertChinaDayAdd(days);
    }


    /**
     * 每天早上凌晨五点钟自动抓取昨日数据
     * https://view.inews.qq.com/g2/getOnsInfo?name=disease_other
     */

    @Scheduled(cron = "0 0 5 * * ?")
    public void insertYesterdayData(){
        String html = httpClintUtil.doGetPage("https://view.inews.qq.com/g2/getOnsInfo?name=disease_other");
        String data = JSON.parseObject(html).getString("data");
        JSONObject jsonObject = JSON.parseObject(data);
        //解析昨天的数据
        String chinaDayList = jsonObject.getString("chinaDayList");
        List<ChinaData> objects = JSON.parseArray(chinaDayList, ChinaData.class);
        ChinaData yesterday = objects.get(objects.size() - 1);
        //获取昨天的日期
        Long currentTime = System.currentTimeMillis();
        Long yesterDay = currentTime - 24*60*60*1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date(yesterDay));

        yesterday.setDataId(Integer.parseInt(format));
        //插入数据
        chinaDataMapper.insertYesterdayData(yesterday);

        //解析昨天新增数据
        String chinaDayAddList = jsonObject.getString("chinaDayAddList");
        List<ChinaData> days = JSON.parseArray(chinaDayAddList, ChinaData.class);
        ChinaData yesterdayAdd = days.get(days.size() - 1);

        yesterdayAdd.setDataId(Integer.parseInt(format));

        chinaDataMapper.insertYesterdayAddData(yesterdayAdd);
    }



}
