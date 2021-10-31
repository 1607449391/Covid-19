package com.xjq.covid19.mapper;

import com.xjq.covid19.bean.WordData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2021-01-22 14:56
 *@description：
 *
 */
@Mapper
public interface GetWordDataMapper {

    public List<WordData> getWordHisData();
}
