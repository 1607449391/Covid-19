package com.xjq.covid19.mapper;

import com.xjq.covid19.bean.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2021-01-20 10:55
 *@description：
 *
 */
@Mapper
public interface GetRealMsgMapper {

    public List<Message> getRealMsgIndex();

    public List<Message> getMoreMessages(@Param("start") int start, @Param("offset") int offset);

}
