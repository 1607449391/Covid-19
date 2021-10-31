package com.xjq.covid19.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *@author：徐家庆
 *@time：2021-01-20 10:46
 *@description：
 *
 */
@Controller
public class VirusKpController {

    @RequestMapping("/nav/virusKp")
    public String linkVirusKp(){
        return "virusKp";
    }
}
