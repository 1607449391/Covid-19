package com.xjq.covid19.mapper;

import com.xjq.covid19.bean.PatientTrack;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2021-02-25 10:16
 *@description：
 *
 */
@Mapper
public interface PatiTrackMapper {


    public List<PatientTrack> getIndexData(@Param("start") int start, @Param("pageSize") int pageSize);

    public List<PatientTrack> getSeacherData(@Param("province") String province,
                                             @Param("city") String city,
                                             @Param("country") String country,
                                             @Param("start") int start,
                                             @Param("pageSize") int pageSize);
}
