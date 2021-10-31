package com.xjq.covid19.bean;

import java.io.Serializable;

/*
 *@author：徐家庆
 *@time：2021-01-16 17:41
 *@description：
 *
 */
public class EverydayData implements Serializable{

    private Integer date;       //日期
    private Integer confirm;    //确诊人数
    private Integer nofect;    //无症状感染人数
    private Integer dead;       //死亡人数
    private Integer heal;       //治愈人数
    private Integer importCase; //境外输入人数

    public EverydayData() {
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Integer getNofect() {
        return nofect;
    }

    public void setNofect(Integer nofect) {
        this.nofect = nofect;
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

    public Integer getImportCase() {
        return importCase;
    }

    public void setImportCase(Integer importCase) {
        this.importCase = importCase;
    }

    @Override
    public String toString() {
        return "EverydayData{" +
                "date=" + date +
                ", confirm=" + confirm +
                ", suspect=" + nofect +
                ", dead=" + dead +
                ", heal=" + heal +
                ", importCase=" + importCase +
                '}';
    }
}
