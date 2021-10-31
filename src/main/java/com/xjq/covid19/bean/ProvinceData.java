package com.xjq.covid19.bean;

/*
 *@author：徐家庆
 *@time：2020-12-09 20:36
 *@description：
 *
 */
public class ProvinceData {
    private Integer id;
    private Integer date;           //时间
    private String provinceName;   //省份
    private Integer confirm;        //确诊总数
    private Integer dead;           //死亡病例总数
    private Integer heal;           //治愈病例数
    private Integer newConfirm;     //新增确诊数
    private Integer newHeal;        //新增治愈人数
    private Integer newDead;        // 新增死亡人数
    private Integer importedCase;   //境外输入总数
    private float deadRate;         //死亡率
    private float healRate;         //治愈率

    public ProvinceData() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
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

    public Integer getNewConfirm() {
        return newConfirm;
    }

    public void setNewConfirm(Integer newConfirm) {
        this.newConfirm = newConfirm;
    }

    public Integer getNewHeal() {
        return newHeal;
    }

    public void setNewHeal(Integer newHeal) {
        this.newHeal = newHeal;
    }

    public Integer getNewDead() {
        return newDead;
    }

    public void setNewDead(Integer newDead) {
        this.newDead = newDead;
    }

    public Integer getImportedCase() {
        return importedCase;
    }

    public void setImportedCase(Integer importedCase) {
        this.importedCase = importedCase;
    }

    @Override
    public String toString() {
        return "ProvinceHistoryData{" +
                "date=" + date +
                ", provinceName=" + provinceName +
                ", confirm=" + confirm +
                ", dead=" + dead +
                ", heal=" + heal +
                ", newConfirm=" + newConfirm +
                ", newHeal=" + newHeal +
                ", newDead=" + newDead +
                ", importedCase=" + importedCase +
                '}';
    }
}
