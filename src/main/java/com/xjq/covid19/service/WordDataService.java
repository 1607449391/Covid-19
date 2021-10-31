package com.xjq.covid19.service;

import com.xjq.covid19.bean.CountryData;
import com.xjq.covid19.bean.MapData;
import com.xjq.covid19.bean.WordData;
import com.xjq.covid19.mapper.GetWordDataMapper;
import com.xjq.covid19.spider.RealDataSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2021-01-21 11:15
 *@description：
 *
 */
@Service
public class WordDataService {

    @Autowired
    RealDataSpider realDataSpider;

    @Autowired
    GetWordDataMapper getWordDataMapper;

    /**
     * 从redis缓存中获取全球疫情数据
     * @return
     */
    @Cacheable(cacheNames = "GlobalTotal",keyGenerator = "myKeyGenerator")
    public WordData getWordRealData(){
        return realDataSpider.getGlobalTotal();
    }

    /**
     * 从redis缓存中获取个全球各国的疫情数据
     * @return
     */
    @Cacheable(cacheNames = "CountryData",keyGenerator = "myKeyGenerator")
    public List<CountryData> getCountrysData(){
        return realDataSpider.getCountryDataOfWord();
    }

    @Cacheable(cacheNames = "wordMapData",keyGenerator = "myKeyGenerator")
    public List<MapData> getWordMapData(){
        return realDataSpider.getwordMapData();
    }


    public List<WordData> getWordHisData() {

        return getWordDataMapper.getWordHisData();
    }
}
