package com.xjq.covid19.service;

import com.xjq.covid19.bean.ChinaData;
import com.xjq.covid19.bean.ProvRealData;
import com.xjq.covid19.spider.RealDataSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/* *@author：徐家庆
 *@time：2021-01-16 11:02
 *@description：
 *
 */
@Service
public class ChinaDataService {

    @Autowired
    RealDataSpider realDataSpider;
    /**
     * 从redis缓存中取国内疫情实时总量数据
     * @return
     */
    @Cacheable(cacheNames = "ChinaTotal",keyGenerator = "myKeyGenerator")
    public ChinaData getChinaRealTotalData(){
        return realDataSpider.getChinaTotal();
    }

    /**
     * 从redis缓存中取国内疫情实时增量数据
     * @return
     */
    @Cacheable(cacheNames = "ChinaAdd",keyGenerator = "myKeyGenerator")
    public ChinaData getChinaRealAddData(){
        return realDataSpider.getChinaAdd();
    }

    /**
     * 从redis缓存中取国内各省市疫情实时数据
     * @return
     */
    @Cacheable(cacheNames = "provinceData",keyGenerator = "myKeyGenerator")
    public List<ProvRealData> getProvinceRealData(){
        return realDataSpider.getProvinceData();
    }


}
