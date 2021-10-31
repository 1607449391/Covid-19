package com.xjq.covid19.controller;

import com.xjq.covid19.bean.PatientTrack;
import com.xjq.covid19.service.PatiTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2021-02-25 10:13
 *@description：
 *
 */
@Controller
public class PatiTrackController {

    @Autowired
    PatiTrackService patiTrackService;

    @RequestMapping("/nav/track")
    public String trackIndex(){
        return "travelPath";
    }



    @RequestMapping("/getIndexData")
    @ResponseBody
    public List<PatientTrack> getIndexData(int start,int pageSize){

        return patiTrackService.getIndexData(start, pageSize);
    }

    @RequestMapping("/getSeacherData")
    @ResponseBody
    public Object getSeacherData(String province,String city,String country,int start,int pageSize){

         List<PatientTrack> tracks = patiTrackService.getSeacherData(province, city, country, start, pageSize);
         if (tracks.size()>0){
             return tracks;
         }else {
             return 0;
         }
    }




}
