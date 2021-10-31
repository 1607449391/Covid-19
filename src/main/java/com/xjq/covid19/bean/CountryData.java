package com.xjq.covid19.bean;

import java.io.Serializable;

/*
 *@author：徐家庆
 *@time：2020-12-24 17:01
 *@description：
 *
 */
public class CountryData implements Serializable{

    private String countryName;
    private String countryCode;
    private Integer confirm;
    private Integer suspect;
    private Integer heal;
    private Integer dead;
    private Integer confirmAdd;
    private Integer suspectAdd;
    private Integer healAdd;
    private Integer deadAdd;
    private Integer nowConfirm;


    public CountryData() {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Integer getSuspect() {
        return suspect;
    }

    public void setSuspect(Integer suspect) {
        this.suspect = suspect;
    }

    public Integer getHeal() {
        return heal;
    }

    public void setHeal(Integer heal) {
        this.heal = heal;
    }

    public Integer getDead() {
        return dead;
    }

    public void setDead(Integer dead) {
        this.dead = dead;
    }

    public Integer getConfirmAdd() {
        return confirmAdd;
    }

    public void setConfirmAdd(Integer confirmAdd) {
        this.confirmAdd = confirmAdd;
    }

    public Integer getSuspectAdd() {
        return suspectAdd;
    }

    public void setSuspectAdd(Integer suspectAdd) {
        this.suspectAdd = suspectAdd;
    }

    public Integer getHealAdd() {
        return healAdd;
    }

    public void setHealAdd(Integer healAdd) {
        this.healAdd = healAdd;
    }

    public Integer getDeadAdd() {
        return deadAdd;
    }

    public void setDeadAdd(Integer deadAdd) {
        this.deadAdd = deadAdd;
    }

    public Integer getNowConfirm() {
        return nowConfirm;
    }

    public void setNowConfirm(Integer nowConfirm) {
        this.nowConfirm = nowConfirm;
    }

    @Override
    public String toString() {
        return "CountryData{" +
                "countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", confirm=" + confirm +
                ", suspect=" + suspect +
                ", heal=" + heal +
                ", dead=" + dead +
                ", confirmAdd=" + confirmAdd +
                ", suspectAdd=" + suspectAdd +
                ", healAdd=" + healAdd +
                ", deadAdd=" + deadAdd +
                ", nowConfirm=" + nowConfirm +
                '}';
    }
}
