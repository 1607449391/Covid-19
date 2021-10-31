package com.xjq.covid19.controller;

import com.xjq.covid19.bean.CountryData;
import com.xjq.covid19.bean.MapData;
import com.xjq.covid19.bean.WordData;
import com.xjq.covid19.service.WordDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/*
 *@author：徐家庆
 *@time：2021-01-21 11:11
 *@description：
 *
 */
@Controller
public class WordDataController {

    @Autowired
    WordDataService wordDataService;

    @RequestMapping("/nav/wordVirus")
    public String linkWordVirus(Model model){
        WordData wordRealData = wordDataService.getWordRealData();
        model.addAttribute("wordData",wordRealData);
        List<CountryData> countrysData = wordDataService.getCountrysData();
        model.addAttribute("countrysData",countrysData);
        return "wordVirus";
    }

    @RequestMapping("/getWordMapData")
    @ResponseBody
    public List<MapData> getWordMapData(){
        return wordDataService.getWordMapData();
    }


    @RequestMapping("/getHistoryData")
    @ResponseBody
    public List<WordData> getWordHisData(){
        return wordDataService.getWordHisData();
    }

}
