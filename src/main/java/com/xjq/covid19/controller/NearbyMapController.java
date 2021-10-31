package com.xjq.covid19.controller;

import com.alibaba.fastjson.JSON;
import com.xjq.covid19.util.HttpClintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/*
 *@author：徐家庆
 *@time：2021-02-26 15:59
 *@description：
 *
 */
@Controller
public class NearbyMapController {

    @Autowired
    HttpClintUtil httpClintUtil;

    private String ak = "Q6CpreCLcDw9fFYtxdgyfl8BIniPDApA";

    @RequestMapping("/nav/nearbyMap")
    public String getIndex(){

        return "nearbyMap";
    }
}
