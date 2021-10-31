package com.xjq.covid19.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjq.covid19.bean.ProvinceData;
import com.xjq.covid19.mapper.ProvDataSpiderMapper;
import com.xjq.covid19.util.HttpClintUtil;
import com.xjq.covid19.util.ProvinceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *@author：徐家庆
 *@time：2020-12-09 20:10
 *@description：
 *
 */
@Service
public class ProvinceDataSpider {

    @Autowired
    ProvinceUtil provinceUtil;

    @Autowired
    HttpClintUtil httpClintUtil;
    @Autowired
    ProvDataSpiderMapper provinceDataMapper;


    public void insertProvinceHistoryData(){

        for (String prov:provinceUtil.provinces){
            String url = "https://api.inews.qq.com/newsqa/v1/query/pubished/daily/list?province="+prov;
            String html = httpClintUtil.doGetPage(url);
            String data = JSON.parseObject(html).getString("data");
            List<ProvinceData> historyData = processData(data, prov,true);
            provinceDataMapper.insertProvinceHistoryData(historyData);
        }
    }

    /**
     * 每天23点定时插入昨天各省份数据
     */
    @Scheduled(cron = "0 0 23 * * ?")
    public void insertProYesterdayData(){
        for (String prov:provinceUtil.provinces){
            String url = "https://api.inews.qq.com/newsqa/v1/query/pubished/daily/list?province="+prov;
            String html = httpClintUtil.doGetPage(url);
            String data = JSON.parseObject(html).getString("data");
            List<ProvinceData> historyData = processData(data, prov,false);

            provinceDataMapper.insertProvinceHistoryData(historyData);
        }
    }

    /**
     * 处理响应的页面数据
     * @param data
     * @param prov
     * @param isHistory: 如果为true 表示解析的为历史数据  false 表示解析昨日各省市数据
     * @return
     */

    public List<ProvinceData> processData(String data, String prov, boolean isHistory) {
        List<ProvinceData> phd = new ArrayList<>();
        JSONArray objects = JSON.parseArray(data);
        int j = objects.size()-1;
        if(isHistory==true){
            j = 0;
        }
        for (int i = j; i < objects.size(); i++) {
            ProvinceData pro_his_data = new ProvinceData();
            JSONObject jsonObject = objects.getJSONObject(i);
            String[] dates = jsonObject.getString("date").split("\\.");
            String dateId = "2021" + dates[0] + dates[1];
            pro_his_data.setDate(Integer.parseInt(dateId));
            pro_his_data.setProvinceName(prov);
            pro_his_data.setConfirm(jsonObject.getInteger("confirm"));
            pro_his_data.setDead(jsonObject.getInteger("dead"));
            pro_his_data.setHeal(jsonObject.getInteger("heal"));
            pro_his_data.setNewConfirm(jsonObject.getInteger("newConfirm"));
            pro_his_data.setNewDead(jsonObject.getInteger("newDead"));
            pro_his_data.setNewHeal(jsonObject.getInteger("newHeal"));
            float confirm = pro_his_data.getConfirm();
            if(confirm == 0){
                pro_his_data.setDeadRate(0f);
                pro_his_data.setHealRate(0f);
            }else{
                float dead = pro_his_data.getDead();
                float heal = pro_his_data.getHeal();
                BigDecimal deadBd = new BigDecimal(dead/confirm*100);
                BigDecimal healBd = new BigDecimal(heal/confirm*100);
                pro_his_data.setDeadRate(deadBd.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue());
                pro_his_data.setHealRate(healBd.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue());
            }
            String description = jsonObject.getString("description");
            StringBuilder builder = new StringBuilder(description);
            StringBuilder reverse = builder.reverse();
            String reg = "[0-9]+";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(reverse);
            if(matcher.find()) {
                String group = reverse.substring(matcher.start(),matcher.end());
                pro_his_data.setImportedCase(Integer.parseInt(group));
            }else {
                pro_his_data.setImportedCase(0);
            }

            phd.add(pro_his_data);

        }
        return phd;
    }
}




