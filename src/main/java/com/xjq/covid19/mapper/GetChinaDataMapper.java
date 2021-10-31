package com.xjq.covid19.mapper;

import com.xjq.covid19.bean.EverydayData;
import com.xjq.covid19.bean.PieData;
import com.xjq.covid19.bean.ProvinceData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2021-01-16 17:36
 *@description：
 *
 */
@Mapper
public interface GetChinaDataMapper {

    //查询中国每天是新增数据
    public List<EverydayData> getChinaEveryDayAdd();

    //查询中国每天总量数据
    public List<EverydayData> getChinaEveryDayTotal();


    //查询确诊总数Top5
    public List<PieData> getConfirmTopFive();

    //查询治愈率Top5
    public List<PieData> getHealTopFive();

    //获取省份历史数据
    public List<ProvinceData> getProvHistoryData(String provinceName);
}
