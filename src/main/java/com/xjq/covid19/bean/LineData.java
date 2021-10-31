package com.xjq.covid19.bean;

import java.util.List;

/*
 *@author：徐家庆
 *@time：2021-01-16 17:02
 *@description：
 *              折线图数据对象
 */
public class LineData {

    List<String> date;              //时间数据集
    List<Integer> confirmList;      //每日确诊人数数据集
    List<Integer> nofectList;      //每日无症状感染人数数据集
    List<Integer> deadList;         //每日死亡人数数据集
    List<Integer> healList;         //每日治愈人数数据集
    List<Integer> importList;       //每日境外输入人数数据集

    public LineData() {
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<Integer> getConfirmList() {
        return confirmList;
    }

    public void setConfirmList(List<Integer> confirmList) {
        this.confirmList = confirmList;
    }

    public List<Integer> getNofectList() {
        return nofectList;
    }

    public void setNofectList(List<Integer> nofectList) {
        this.nofectList = nofectList;
    }

    public List<Integer> getDeadList() {
        return deadList;
    }

    public void setDeadList(List<Integer> deadList) {
        this.deadList = deadList;
    }

    public List<Integer> getHealList() {
        return healList;
    }

    public void setHealList(List<Integer> healList) {
        this.healList = healList;
    }

    public List<Integer> getImportList() {
        return importList;
    }

    public void setImportList(List<Integer> importList) {
        this.importList = importList;
    }

    @Override
    public String toString() {
        return "LineData{" +
                "date=" + date +
                ", confirmList=" + confirmList +
                ", suspectList=" + nofectList +
                ", deadList=" + deadList +
                ", healList=" + healList +
                ", importList=" + importList +
                '}';
    }
}
