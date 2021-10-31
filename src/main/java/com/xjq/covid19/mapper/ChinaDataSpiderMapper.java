package com.xjq.covid19.mapper;

import com.xjq.covid19.bean.ChinaData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2020-12-09 09:55
 *@description：
 *
 */
@Mapper
public interface ChinaDataSpiderMapper {



    //批量插入中国疫情历史数据
    public void insertChinaHistoryData(List<ChinaData> list);

    //批量插入中国疫情每天新增详细数目
    public void insertChinaDayAdd(List<ChinaData> list);

    //插入昨天疫情数据
    public  void insertYesterdayData(ChinaData chd);

    //插入昨天疫情增长数据
    public void insertYesterdayAddData(ChinaData chd);
}
