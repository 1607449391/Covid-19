package com.xjq.covid19.mapper;

import com.xjq.covid19.bean.WordData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2020-12-14 19:23
 *@description：
 *
 */
@Mapper
public interface WordDataSpiderMapper {

    public void  insertWordHistData(List<WordData> list);
}
