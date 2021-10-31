package com.xjq.covid19.mapper;

import com.xjq.covid19.bean.Province;
import com.xjq.covid19.bean.ProvinceData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2020-12-09 21:12
 *@description：
 *
 */
@Mapper
public interface ProvDataSpiderMapper {
    //批量插入省市代码
    public void insertProvinceCode(List<Province> proCode);

    //批量插入各省市的历史数据
    public void insertProvinceHistoryData(List<ProvinceData> list);

}
