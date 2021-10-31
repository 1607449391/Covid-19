package com.xjq.covid19.bean;

/*
 *@author：徐家庆
 *@time：2021-01-16 16:28
 *@description：
 *          地图数据对象
 */
public class MapData {
    private String name;    //地区名称
    private Integer value;      //感染人数

    public MapData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MapData{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
