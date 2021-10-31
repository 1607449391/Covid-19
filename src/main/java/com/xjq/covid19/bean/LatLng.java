package com.xjq.covid19.bean;

/*
 *@author：徐家庆
 *@time：2021-02-26 17:02
 *@description：
 *
 */
public class LatLng {

    private double Latitude;   //纬度
    private double longitude;   //经度

    public LatLng() {
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LatLng{" +
                "Latitude=" + Latitude +
                ", longitude=" + longitude +
                '}';
    }
}
