package com.xjq.covid19.bean;

/*
 *@author：徐家庆
 *@time：2021-01-24 17:40
 *@description：
 *
 */
public class PieData {

    private String name;

    private Integer value1;

    private Float value2;

    public PieData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue1() {
        return value1;
    }

    public void setValue1(Integer value1) {
        this.value1 = value1;
    }

    public Float getValue2() {
        return value2;
    }

    public void setValue2(Float value2) {
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "PieData{" +
                "name='" + name + '\'' +
                ", value1=" + value1 +
                ", value2=" + value2 +
                '}';
    }
}
