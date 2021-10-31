package com.xjq.covid19.mapper;

import com.xjq.covid19.bean.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2020-12-19 21:44
 *@description：
 *
 */
@Mapper
public interface RealMsgSpiderMapper {
    public void insertWYMessage(List<Message> list);
    public void insertDXYMessage(List<Message> list);
    public void insertALIMessage(List<Message> list);
    public void insertSGMessage(List<Message> list);
}
