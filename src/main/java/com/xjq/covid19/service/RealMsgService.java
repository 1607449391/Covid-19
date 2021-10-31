package com.xjq.covid19.service;

import com.xjq.covid19.bean.Message;
import com.xjq.covid19.mapper.GetRealMsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2021-01-20 11:10
 *@description：
 *
 */
@Service
public class RealMsgService {

    @Autowired
    GetRealMsgMapper getRealMsgMapper;

    public List<Message> getRealMsgIndex(){
        return getRealMsgMapper.getRealMsgIndex();
    }

    public List<Message> getMoreMessages(int start,int offset){
        return getRealMsgMapper.getMoreMessages(start,offset);
    }
}
