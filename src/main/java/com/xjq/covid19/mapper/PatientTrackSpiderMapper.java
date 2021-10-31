package com.xjq.covid19.mapper;

import com.xjq.covid19.bean.PatientTrack;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2021-02-22 17:08
 *@description：
 *
 */
@Mapper
public interface PatientTrackSpiderMapper {

    public void insertTrack(List<PatientTrack> tracks);
}
