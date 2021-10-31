package com.xjq.covid19.spider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjq.covid19.bean.*;
import com.xjq.covid19.util.HttpClintUtil;
import com.xjq.covid19.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 *@author：徐家庆
 *@time：2020-12-23 16:43
 *@description：
 *
 */
@Service
public class RealDataSpider {

    @Autowired
    HttpClintUtil httpClintUtil;

    private static String TXhtml = "";

    private static String WYhtml = "";

    private static String WYWordData = "";

    public RealDataSpider() {
        HttpClintUtil http = new HttpClintUtil();
        String url  = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
        String html = http.doGetPage(url);
        TXhtml = JSONObject.parseObject(html).getString("data");

        String wyurl  = "https://interface.sina.cn/news/wap/fymap2020_data.d.json";
        String wyhtml = http.doGetPage(wyurl);
        WYhtml = JSONObject.parseObject(wyhtml).getString("data");

        String wywordUrl = "https://c.m.163.com/ug/api/wuhan/app/data/list-total?t=161122038658";
        String wyWordHtml = http.doGetPage(wywordUrl);
        WYWordData = JSONObject.parseObject(wyWordHtml).getString("data");

    }

    /**
     * 定时爬取腾讯的json数据 每隔两小时40分更新一次
     */
    @Scheduled(cron = "0 40 0/2 * * ?")
    public void getTXJsonData(){
        String url  = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
        String html = httpClintUtil.doGetPage(url);
        TXhtml = JSONObject.parseObject(html).getString("data");
        //System.out.println(TXhtml);
    }

    /**
     * 定时获取网易的JSon数据  每隔两小时40分更新一次
     */
    @Scheduled(cron = "0 42 0/2 * * ?")
    public void getWYJsonData(){
        String url  = "https://interface.sina.cn/news/wap/fymap2020_data.d.json";
        String html = httpClintUtil.doGetPage(url);
        WYhtml = JSONObject.parseObject(html).getString("data");
        //System.out.println(WYhtml);
    }

    /**
     * 定时获取网易的JSon数据  每隔两小时40分更新一次
     */
    @Scheduled(cron = "0 44 0/2 * * ?")
    public void getWYWordDataJson(){
        String url  = "https://c.m.163.com/ug/api/wuhan/app/data/list-total?t=161122038658";
        String html = httpClintUtil.doGetPage(url);
        WYWordData = JSONObject.parseObject(html).getString("data");
        //System.out.println(WYhtml);
    }


    /**
     * 解析json数据中的中国疫情概况。
     */
    @CachePut(cacheNames = "ChinaTotal",keyGenerator = "myKeyGenerator",condition = "#result!=null")
    @Scheduled(cron = "0 46 0/2 * * ? ")
    public ChinaData getChinaTotal(){

        JSONObject jsonObject = JSONObject.parseObject(TXhtml);
        ChinaData chinaData = new ChinaData();
        String lastUpdateTime = jsonObject.getString("lastUpdateTime");
        chinaData.setDate(lastUpdateTime);
        JSONObject chinaTotal = jsonObject.getJSONObject("chinaTotal");
        chinaData.setConfirm(chinaTotal.getInteger("confirm"));
        chinaData.setHeal(chinaTotal.getInteger("heal"));
        chinaData.setDead(chinaTotal.getInteger("dead"));
        chinaData.setNowConfirm(chinaTotal.getInteger("nowConfirm"));
        chinaData.setSuspect(chinaTotal.getInteger("suspect"));
        chinaData.setNowSevere(chinaTotal.getInteger("nowSevere"));
        chinaData.setImportedCase(chinaTotal.getInteger("importedCase"));
        chinaData.setNoInfect(chinaTotal.getInteger("noInfect"));
        return chinaData;
    }

    /**
     * 解析json数据中的中国疫情实时增量。
     */
    @CachePut(cacheNames = "ChinaAdd",keyGenerator = "myKeyGenerator",condition = "#result!=null")
    @Scheduled(cron = "0 48 0/2 * * ? ")
    public ChinaData getChinaAdd(){

        JSONObject jsonObject = JSONObject.parseObject(TXhtml);
        ChinaData chinaData = new ChinaData();
        JSONObject chinaAdd = jsonObject.getJSONObject("chinaAdd"); //获取实时增量JSon对象
        chinaData.setConfirm(chinaAdd.getInteger("confirm"));
        chinaData.setHeal(chinaAdd.getInteger("heal"));
        chinaData.setDead(chinaAdd.getInteger("dead"));
        chinaData.setNowConfirm(chinaAdd.getInteger("nowConfirm"));
        chinaData.setSuspect(chinaAdd.getInteger("suspect"));
        chinaData.setNowSevere(chinaAdd.getInteger("nowSevere"));
        chinaData.setImportedCase(chinaAdd.getInteger("importedCase"));
        chinaData.setNoInfect(chinaAdd.getInteger("noInfect"));
        return chinaData;
    }

    /**
     * 获取各省市的实时数据
     * @return
     */
    @CachePut(cacheNames = "provinceData",keyGenerator = "myKeyGenerator",condition = "#result!=null")
    @Scheduled(cron = "0 50 0/2 * * ? ")
    public List<ProvRealData> getProvinceData(){
        JSONArray objects = JSONObject.parseObject(TXhtml).getJSONArray("areaTree");
        JSONObject o = (JSONObject) objects.get(0);
        JSONArray provinces = o.getJSONArray("children");   //获取所有省份数据
        List<ProvRealData> ProvinceList = new ArrayList<>();
        for (int i = 0;i < provinces.size();i++){           //遍历所有省份数据
            JSONObject province = provinces.getJSONObject(i);
            ProvRealData provRealData = ProcessProvinceData(province);  //解析省份数据
            JSONArray citys = province.getJSONArray("children");    //获取当前省的所有市数据
            List<ProvRealData> cityList = new ArrayList<>();
            for (int j = 0;j < citys.size();j++){                     //遍历所有城市
                JSONObject city = citys.getJSONObject(j);
                ProvRealData cityData = ProcessProvinceData(city);    //解析城市数据
                cityList.add(cityData);
            }
            provRealData.setChildren(cityList);
            ProvinceList.add(provRealData);
        }


        return ProvinceList;
    }

    /**
     * 解析全球疫情总体概况
     * @return
     */
    @CachePut(cacheNames = "GlobalTotal",keyGenerator = "myKeyGenerator",condition = "#result!=null")
    @Scheduled(cron = "0 52 0/2 * * ? ")
    public WordData getGlobalTotal(){
        JSONObject WYJson = JSONObject.parseObject(WYhtml);
        String date = WYJson.getString("mtime");
        WordData wordData = new WordData();
        wordData.setDateId(TimeUtil.getTimestampForGlobal("2020-12-12 00:00:00"));
        String othertotal1 = WYJson.getString("othertotal");
        JSONObject othertotal = JSONObject.parseObject(othertotal1);

        wordData.setConfirm(othertotal.getInteger("certain"));
        wordData.setSuspect(othertotal.getInteger("uncertain"));
        wordData.setDead(othertotal.getInteger("die"));
        wordData.setHeal(othertotal.getInteger("recure"));
        wordData.setConfirmInc(othertotal.getInteger("certain_inc"));
        wordData.setSuspectInc(othertotal.getInteger("uncertain_inc"));
        wordData.setDeadInc(othertotal.getInteger("die_inc"));
        wordData.setHealInc(othertotal.getInteger("recure_inc"));
        wordData.setNowConfirm(othertotal.getInteger("ecertain"));
        wordData.setNowConfirmInc(othertotal.getInteger("ecertain_inc"));
        return wordData;
    }
    @CachePut(cacheNames = "CountryData",keyGenerator = "myKeyGenerator",condition = "#result!=null")
    @Scheduled(cron = "0 54 0/2 * * ? ")
    public List<CountryData> getCountryDataOfWord(){
        JSONObject WYJSson = JSONObject.parseObject(WYhtml);
        JSONArray countrys = WYJSson.getJSONArray("otherlist");
        List<CountryData> countrysList = new ArrayList<>();
        for (int i = 0;i < countrys.size();i++){
            CountryData countryData = new CountryData();
            JSONObject country = countrys.getJSONObject(i);
            countryData.setCountryName(country.getString("name"));
            countryData.setCountryCode(country.getString("citycode"));
            countryData.setConfirm(country.getInteger("conNum"));
            countryData.setSuspect(country.getInteger("susNum"));
            countryData.setHeal(country.getInteger("cureNum"));
            countryData.setDead(country.getInteger("deathNum"));
            countryData.setConfirmAdd(country.getInteger("conadd"));
            countryData.setSuspectAdd(country.getInteger("susadd"));
            countryData.setHealAdd(country.getInteger("cureadd"));
            countryData.setDeadAdd(country.getInteger("deathadd"));
            countryData.setNowConfirm(country.getInteger("econNum"));
            countrysList.add(countryData);

        }
        return countrysList;
    }

    @CachePut(cacheNames = "wordMapData",keyGenerator = "myKeyGenerator",condition = "#result!=null")
    @Scheduled(cron = "0 56 0/2 * * ? ")
    public List<MapData> getwordMapData(){
        JSONObject WYJSson = JSONObject.parseObject(WYWordData);
        JSONArray countrys = WYJSson.getJSONArray("areaTree");
        List<MapData> wordMaps = new ArrayList<>();
        for (int i = 0;i < countrys.size();i++){
            JSONObject country = countrys.getJSONObject(i);
            MapData map = new MapData();
            map.setName(country.getString("name"));
            JSONObject totalJson = JSONObject.parseObject(country.getString("total"));
            map.setValue(totalJson.getInteger("confirm"));
            wordMaps.add(map);

        }
        return wordMaps;
    }


    public ProvRealData ProcessProvinceData(JSONObject province){
        ProvRealData provRealData = new ProvRealData();
        provRealData.setProvinceName(province.getString("name"));
        Integer addConfirm = province.getJSONObject("today").getInteger("confirm");
        provRealData.setAddConfirm(addConfirm);
        JSONObject total = province.getJSONObject("total");
        provRealData.setConfirm(total.getInteger("confirm"));
        provRealData.setNowConfirm(total.getInteger("nowConfirm"));
        provRealData.setSuspect(total.getInteger("suspect"));
        provRealData.setDead(total.getInteger("dead"));
        provRealData.setHeal(total.getInteger("heal"));
        return provRealData;
    }



}
