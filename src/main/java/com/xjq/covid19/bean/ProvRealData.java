package com.xjq.covid19.bean;

import java.io.Serializable;
import java.util.List;

/*
 *@author：徐家庆
 *@time：2020-12-24 14:33
 *@description：
 *
 */
public class ProvRealData implements Serializable{

    private String provinceName;   //省份名称
    private Integer confirm;        //确诊总数
    private Integer nowConfirm;     //现存确诊人数
    private Integer addConfirm;     //角昨日新增人数
    private Integer suspect;        //疑似人数
    private Integer dead;           //死亡人数
    private Integer heal;           //治愈人数
    private List<ProvRealData> children = null;  //省份下各市详情

    public ProvRealData() {
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Integer getNowConfirm() {
        return nowConfirm;
    }

    public void setNowConfirm(Integer nowConfirm) {
        this.nowConfirm = nowConfirm;
    }

    public Integer getAddConfirm() {
        return addConfirm;
    }

    public void setAddConfirm(Integer addConfirm) {
        this.addConfirm = addConfirm;
    }

    public Integer getSuspect() {
        return suspect;
    }

    public void setSuspect(Integer suspect) {
        this.suspect = suspect;
    }

    public Integer getDead() {
        return dead;
    }

    public void setDead(Integer dead) {
        this.dead = dead;
    }

    public Integer getHeal() {
        return heal;
    }

    public void setHeal(Integer heal) {
        this.heal = heal;
    }

    public List<ProvRealData> getChildren() {
        return children;
    }

    public void setChildren(List<ProvRealData> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ProvRealData{" +
                "provinceName='" + provinceName + '\'' +
                ", confirm=" + confirm +
                ", nowConfirm=" + nowConfirm +
                ", addConfirm=" + addConfirm +
                ", suspect=" + suspect +
                ", dead=" + dead +
                ", heal=" + heal +
                ", children=" + children +
                '}';
    }
}
