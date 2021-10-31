package com.xjq.covid19.bean;

/*
 *@author：徐家庆
 *@time：2020-12-09 11:49
 *@description：
 *
 */
public class ChinaData {

    private Integer confirm;    //确诊数
    private String date;        //日期
    private Integer nowSevere;  //现存
    private Integer noInfect;   //无症状感染者数量
    private float healRate;     //治愈率
    private Integer heal;       //治愈人数
    private Integer importedCase;//境外输入人数
    private Integer dead;       //死亡人数
    private Integer nowConfirm; //
    private Integer suspect;    //疑似病例
    private float deadRate;     //死亡率
    private Integer dataId;     //完整日期
    private Integer infect;

    public ChinaData() {
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getInfect() {
        return infect;
    }

    public void setInfect(Integer infect) {
        this.infect = infect;
    }

    public Integer getNowSevere() {
        return nowSevere;
    }

    public void setNowSevere(Integer nowSevere) {
        this.nowSevere = nowSevere;
    }

    public Integer getNoInfect() {
        return noInfect;
    }

    public void setNoInfect(Integer noInfect) {
        this.noInfect = noInfect;
    }

    public float getHealRate() {
        return healRate;
    }

    public void setHealRate(float healRate) {
        this.healRate = healRate;
    }

    public Integer getHeal() {
        return heal;
    }

    public void setHeal(Integer heal) {
        this.heal = heal;
    }

    public Integer getImportedCase() {
        return importedCase;
    }

    public void setImportedCase(Integer importedCase) {
        this.importedCase = importedCase;
    }

    public Integer getDead() {
        return dead;
    }

    public void setDead(Integer dead) {
        this.dead = dead;
    }

    public Integer getNowConfirm() {
        return nowConfirm;
    }

    public void setNowConfirm(Integer nowConfirm) {
        this.nowConfirm = nowConfirm;
    }

    public Integer getSuspect() {
        return suspect;
    }

    public void setSuspect(Integer suspect) {
        this.suspect = suspect;
    }

    public float getDeadRate() {
        return deadRate;
    }

    public void setDeadRate(float deadRate) {
        this.deadRate = deadRate;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    @Override
    public String toString() {
        return "ChinaData{" +
                "confirm=" + confirm +
                ", date='" + date + '\'' +
                ", nowSevere=" + nowSevere +
                ", noInfect=" + noInfect +
                ", healRate=" + healRate +
                ", heal=" + heal +
                ", importedCase=" + importedCase +
                ", dead=" + dead +
                ", nowConfirm=" + nowConfirm +
                ", suspect=" + suspect +
                ", deadRate=" + deadRate +
                ", dataId=" + dataId +
                ", infect=" + infect +
                '}';
    }
}
