package com.xjq.covid19.bean;

import java.io.Serializable;

/*
 *@author：徐家庆
 *@time：2020-12-14 19:18
 *@description：
 *
 */
public class WordData implements Serializable {

    private Integer dateId;
    private Integer confirm;
    private Integer suspect;
    private Integer dead;
    private Integer heal;
    private Integer confirmInc;
    private Integer suspectInc;
    private Integer deadInc;
    private Integer healInc;
    private Integer nowConfirm;
    private Integer nowConfirmInc;
    private float deadRate;
    private float healRate;

    private int flag  = 1;

    public WordData() {
    }

    public Integer getNowConfirm() {
        return nowConfirm;
    }

    public void setNowConfirm(Integer nowConfirm) {
        this.nowConfirm = nowConfirm;
    }

    public Integer getNowConfirmInc() {
        return nowConfirmInc;
    }

    public void setNowConfirmInc(Integer nowConfirmInc) {
        this.nowConfirmInc = nowConfirmInc;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
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

    public Integer getConfirmInc() {
        return confirmInc;
    }

    public void setConfirmInc(Integer confirmInc) {
        this.confirmInc = confirmInc;
    }

    public Integer getSuspectInc() {
        return suspectInc;
    }

    public void setSuspectInc(Integer suspectInc) {
        this.suspectInc = suspectInc;
    }

    public Integer getDeadInc() {
        return deadInc;
    }

    public void setDeadInc(Integer deadInc) {
        this.deadInc = deadInc;
    }

    public Integer getHealInc() {
        return healInc;
    }

    public void setHealInc(Integer healInc) {
        this.healInc = healInc;
    }

    public float getDeadRate() {
        return deadRate;
    }

    public void setDeadRate(float deadRate) {
        this.deadRate = deadRate;
    }

    public float getHealRate() {
        return healRate;
    }

    public void setHealRate(float healRate) {
        this.healRate = healRate;
    }

    @Override
    public String toString() {
        return "WordHistoryData{" +
                "dateId=" + dateId +
                ", confirm=" + confirm +
                ", suspect=" + suspect +
                ", dead=" + dead +
                ", heal=" + heal +
                ", confirmInc=" + confirmInc +
                ", suspectInc=" + suspectInc +
                ", deadInc=" + deadInc +
                ", healInc=" + healInc +
                ", deadRate=" + deadRate +
                ", healRate=" + healRate +
                '}';
    }
}
