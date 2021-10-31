package com.xjq.covid19.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjq.covid19.bean.PatientTrack;
import com.xjq.covid19.mapper.PatientTrackSpiderMapper;
import com.xjq.covid19.util.HttpClintUtil;
import com.xjq.covid19.util.URLCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *@author：徐家庆
 *@time：2021-02-22 16:01
 *@description：
 *
 */
@Service
public class PatientTrackSpider {

    @Autowired
    HttpClintUtil httpClintUtil;


    @Autowired
    PatientTrackSpiderMapper patientTrackSpiderMapper;

    private static  String urlPrefix = "https://active.163.com/service/form/v1/13330/view/1957.jsonp?page=";
    private static  String urlSuffix = "&pageSize=50";

    public void getPatientTrack(){

        for (int i = 1;i < 231;i++){
            List<PatientTrack> tracks = JsonProcess(i);
            patientTrackSpiderMapper.insertTrack(tracks);
        }

    }


    public List<PatientTrack> JsonProcess(int page){
        String url = urlPrefix+page+urlSuffix;
        String htmlJson = httpClintUtil.doGetPage(url);
        String regex = "\\[\\{.*\\}\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(htmlJson);
        String str = "";
        if (matcher.find()){
            str = matcher.group();
            //System.out.println(str);
        }

        JSONArray objects = JSON.parseArray(str);
        List<PatientTrack> trackBeans = new ArrayList<>();
        for (int i = 0;i<objects.size();i++){
            PatientTrack pt = new PatientTrack();
            JSONObject track = (JSONObject) objects.get(i);
            String province = track.getString("province");
            pt.setProvince(province);

            String city = track.getString("city");
            pt.setCity(city);

            String county = track.getString("county");
            pt.setCounty(county);

            String location = track.getString("location");
            if ("".equals(location)){
                pt.setLocation("暂无");
            }else {
                pt.setLocation(location);
            }

            String userName = track.getString("user_name");
            pt.setUserName(userName);

            String otherInfo = track.getString("other_info");
            if ("".equals(otherInfo)){
                pt.setOtherInfo("暂无");
            }else {
                pt.setOtherInfo(otherInfo);
            }

            String trackEncoder = track.getString("track");
            String trackDecoder = URLCodeUtil.getURLDecoderString(trackEncoder);
            pt.setTrack(trackDecoder);

            String pubTime = track.getString("pub_time");
            pt.setPubTime(pubTime);

            String source = track.getString("source");
            pt.setSource(source);

            String sourceUrl = track.getString("source_url");
            pt.setSourceUrl(sourceUrl);
            System.out.println(pt);
            trackBeans.add(pt);
        }

        return trackBeans;
    }


}
