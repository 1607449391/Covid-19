package com.xjq.covid19.controller;

import com.xjq.covid19.bean.Message;
import com.xjq.covid19.service.RealMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2021-01-20 10:48
 *@description：
 *
 */
@Controller
public class RealMsgController {

    @Autowired
    RealMsgService realMsgService;

    /**
     * 获取前10条数据
     * @param model
     * @return
     */
    @RequestMapping("/nav/realMsg")
    public String linkRealMsg(Model model){
        List<Message> messages = realMsgService.getRealMsgIndex();
        model.addAttribute("messages",messages);
        return "realMsg";
    }

    /**
     * 获取更多消息，每次请求10条数据
     * @param pageNo    页码
     * @return
     */
    @RequestMapping("/getMoreMsg")
    @ResponseBody
    public List<Message> getMoreMessages(int pageNo){
        System.out.println(pageNo);
        int start = pageNo*10;
        int offset = 10;
        return realMsgService.getMoreMessages(start,offset);
    }
}
