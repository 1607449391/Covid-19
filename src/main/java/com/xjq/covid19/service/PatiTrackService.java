package com.xjq.covid19.service;

import com.xjq.covid19.bean.PatientTrack;
import com.xjq.covid19.mapper.PatiTrackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2021-02-25 10:35
 *@description：
 *
 */
@Service
public class PatiTrackService {

    @Autowired
    private PatiTrackMapper patiTrackMapper;

    public List<PatientTrack> getIndexData(int start,int pageSize){
        return patiTrackMapper.getIndexData(start,pageSize);
    }


    public List<PatientTrack> getSeacherData(String province,String city,String country,
                                             int strat,int pageSize){
        return patiTrackMapper.getSeacherData(province, city, country, strat, pageSize);
    }

}
