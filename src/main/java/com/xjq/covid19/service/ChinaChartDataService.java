package com.xjq.covid19.service;

import com.xjq.covid19.bean.EverydayData;
import com.xjq.covid19.bean.LineData;
import com.xjq.covid19.bean.PieData;
import com.xjq.covid19.bean.ProvinceData;
import com.xjq.covid19.mapper.GetChinaDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 *@author：徐家庆
 *@time：2021-01-16 18:04
 *@description：
 *
 */
@Service
public class ChinaChartDataService {

    @Autowired
    GetChinaDataMapper getChinaDataMapper;

    /**
     * 获取中国每日新增数据集合
     * @return
     */
    public LineData getChinaEveryDayAdd(){
        List<EverydayData> chinaEveryDayAdd = getChinaDataMapper.getChinaEveryDayAdd();
        return processListData(chinaEveryDayAdd);
    }

    /**
     * 获取中国每日总量数据集合
     * @return
     */
    public LineData getChinaEveryDayTotal(){
        List<EverydayData> chinaEveryDayTotal = getChinaDataMapper.getChinaEveryDayTotal();
        return processListData(chinaEveryDayTotal);
    }

    /**
     * 获取TOP
     * @param
     * @return
     */
    public List<PieData> getTopFive(){
        List<PieData> confirmTop = getChinaDataMapper.getConfirmTopFive();
        List<PieData> healTop = getChinaDataMapper.getHealTopFive();
        confirmTop.addAll(healTop);
        return confirmTop;
    }



    public LineData processListData(List<EverydayData> days){
        List<String> date = new ArrayList<>();
        List<Integer> confirms = new ArrayList<>();
        List<Integer> nofects = new ArrayList<>();
        List<Integer> deads = new ArrayList<>();
        List<Integer> heals = new ArrayList<>();
        List<Integer> importCases = new ArrayList<>();
        for (EverydayData edd:days){
            date.add(String.valueOf(edd.getDate()));
            confirms.add(edd.getConfirm());
            nofects.add(edd.getNofect());
            deads.add(edd.getDead());
            heals.add(edd.getHeal());
            importCases.add(edd.getImportCase());
        }
        LineData lineData = new LineData();
        lineData.setDate(date);
        lineData.setConfirmList(confirms);
        lineData.setNofectList(nofects);
        lineData.setDeadList(deads);
        lineData.setHealList(heals);
        lineData.setImportList(importCases);
        LineData chinaLine = new LineData();
        chinaLine.setDate(date);
        chinaLine.setConfirmList(confirms);
        chinaLine.setImportList(importCases);
        chinaLine.setHealList(heals);
        chinaLine.setDeadList(deads);
        chinaLine.setNofectList(nofects);
        return chinaLine;
    }


    /**
     * 查询特定省份的历史数据
     * @return
     */
    public List<ProvinceData> getProvHistoryData(String provinceName){
        return getChinaDataMapper.getProvHistoryData(provinceName);
    }

}
