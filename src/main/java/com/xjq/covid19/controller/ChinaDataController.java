package com.xjq.covid19.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjq.covid19.bean.*;
import com.xjq.covid19.service.ChinaChartDataService;
import com.xjq.covid19.service.ChinaDataService;
import com.xjq.covid19.util.ProvinceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@author：徐家庆
 *@time：2021-01-16 10:58
 *@description：
 *
 */
@Controller
public class ChinaDataController {


    @Autowired
    ChinaDataService chinaDataService;
    @Autowired
    ChinaChartDataService chartDataService;

    private  static   List<ProvRealData> provRealData;

    private static List<MapData> chinaTotalMapData;

    private static   Map<String,ProvRealData> provinceData;
    /**
     * 获取国内疫情实时数据
     * @return
     */


    @RequestMapping("/covid19")
    public String setIndex(Model model){
        ChinaData cdTotal = chinaDataService.getChinaRealTotalData();
        ChinaData cdAdd = chinaDataService.getChinaRealAddData();
        List<ProvRealData> provRealDataList = chinaDataService.getProvinceRealData();
        provRealData = provRealDataList;
        model.addAttribute("chinaRealTotalData",cdTotal);
        model.addAttribute("chinaRealAddData",cdAdd);
        model.addAttribute("provRealData",provRealDataList);
        return "index";
    }

    /**
     * 从redis缓存获取每个省市现存确诊人数，绘制疫情地图
     * @return
     */
    @RequestMapping("/getChinaMap")
    @ResponseBody
    public List<MapData> getProvinceMapData(){
        List<ProvRealData> provRealDataList = chinaDataService.getProvinceRealData();
        List<MapData> chinaMap = new ArrayList<>();
        List<MapData> chinaTotalMap = new ArrayList<>();
        for(ProvRealData prov:provRealDataList){
            MapData mapData = new MapData();
            MapData totalMap = new MapData();
            mapData.setName(prov.getProvinceName());
            mapData.setValue(prov.getNowConfirm());
            chinaMap.add(mapData);
            totalMap.setName(prov.getProvinceName());
            totalMap.setValue(prov.getConfirm());
            chinaTotalMap.add(totalMap);    //同时保存实时确诊总数数据集，减少第二次请求数据
        }
        chinaTotalMapData = chinaTotalMap;
       //JSONArray chinaMapJson = (JSONArray) JSONArray.toJSON(chinaMap);
        chinaMap.addAll(chinaTotalMap);
        return chinaMap;
    }

    /**
     * 直接从静态变量中获取各省市累计确诊总人数
     * @return
     */
    @RequestMapping("/getChinaTotalMap")
    @ResponseBody
    public List<MapData> getChinaTotalMap(){
        return chinaTotalMapData;    //直接从静态变量中取值，减少访问redis缓存的次数
    }

    /**
     * 获取中国每日新增数据，绘制每日新增折线图
     * @return
     */
    @RequestMapping("/getAddLineData")
    @ResponseBody
    public LineData getProvinceAddLineData(){
        LineData chinaEveryDayAdd = chartDataService.getChinaEveryDayAdd();
        return chinaEveryDayAdd;
    }

    /**
     * 获取中国每日总量数据，绘制每日总量折线图
     * @return
     */
    @RequestMapping("/getTotalLineData")
    @ResponseBody
    public LineData getProvinceTotalLineData(){
        LineData chinaEveryDayTotal = chartDataService.getChinaEveryDayTotal();
        return chinaEveryDayTotal;
    }

    /**
     * 获取中国各省市的实时数据
     * @return
     */
    @RequestMapping("/getProvinceData")
    @ResponseBody
    public String getPrvinceData(){
        JSONArray provsJson = (JSONArray) JSONArray.toJSON(provRealData);
        return provsJson.toString();
    }

    /**
     * 获取确诊总数Top5 和治愈率Top5
     * @return
     */
    @RequestMapping("/getTop")
    @ResponseBody
    public List<PieData> getTopFive(){
        return chartDataService.getTopFive();
    }


    /**
     * 获取特定省份的实时数据
     * @param provinceName
     * @param model
     * @return
     */
    @RequestMapping("/showProvince")
    public String showProvince(String provinceName,Model model){
       // System.out.println(provRealData);
        if(provinceData == null){
            Map<String,ProvRealData> provMap = new HashMap<>();
            for (int i = 0;i < provRealData.size();i++) {
                ProvRealData prov = provRealData.get(i);
                provMap.put(prov.getProvinceName(),prov);
            }
            provinceData = provMap;
        }
        model.addAttribute("provinceJs", ProvinceUtil.provinceJsMap.get(provinceName));
        model.addAttribute("province",provinceData.get(provinceName));
        return "showProvince";
    }

    /**
     * 获取特定省份的所有市区的疫情数据，绘制省份确诊地图
     * @param provinceName
     * @return
     */
    @RequestMapping("/getProvMap")
    @ResponseBody
    public List<MapData> getProvMap(String provinceName){

        ProvRealData provRealData = provinceData.get(provinceName);
        List<MapData> cityList = new ArrayList<>();
        for(ProvRealData prl: provRealData.getChildren()) {
            String pname = prl.getProvinceName();
            if (pname.contains("境外输入")||pname.contains("待确认")||pname.contains("来")) {
                continue;
            }
            MapData mapData = new MapData();
            if(provinceName.equals("北京")||provinceName.equals("上海")||provinceName.equals("重庆")){
                mapData.setName(pname+"区");
            }else if (pname.contains("区")||pname.contains("市")){
                mapData.setName(pname);
            } else {
                mapData.setName(pname+"市");
            }
            mapData.setValue(prl.getConfirm());
            cityList.add(mapData);
        }
        return cityList;
    }

    /**
     * 获取特定省份的历史数据，绘制疫情折线图
     * @return
     */
    @RequestMapping("/getProvHistorydata")
    @ResponseBody
    public List<ProvinceData> getProvHistoryData(String provinceName){
        return chartDataService.getProvHistoryData(provinceName);
    }

}
