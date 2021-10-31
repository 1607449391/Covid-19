package com.xjq.covid19.bean;

/*
 *@author：徐家庆
 *@time：2021-02-22 16:02
 *@description：
 *
 */
public class PatientTrack {
    private Integer id;
    private String province;
    private String city;
    private String county;
    private String location;
    private String userName;
    private String otherInfo;
    private String track;
    private String pubTime;
    private String source;
    private String sourceUrl;

    public PatientTrack() {
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "PatientTrack{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", location='" + location + '\'' +
                ", userName='" + userName + '\'' +
                ", otherInfo='" + otherInfo + '\'' +
                ", track='" + track + '\'' +
                ", pubTime='" + pubTime + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
