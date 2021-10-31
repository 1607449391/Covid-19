package com.xjq.covid19.bean;

/*
 *@author：徐家庆
 *@time：2020-12-09 10:17
 *@description：
 *
 */
public class Province {
    //省市代码
    private Integer code;
    //省市名称
    private String province;


    public Province() {
    }

    public Province(Integer code, String province) {
        this.code = code;
        this.province = province;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
