package com.xjq.covid19.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *@author：徐家庆
 *@time：2021-02-26 17:00
 *@description：
 *
 */
public class LatAndLng {
    private static Random random = new Random();

    private static String ak = "kYiURpArqIoClUhKDOaHivPtk7lQntPw";

    private  static  HttpClintUtil httpClintUtil = new HttpClintUtil();

    private static String preStr = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=kYiURpArqIoClUhKDOaHivPtk7lQntPw&output=json&coordtype=wgs84ll&location=";
    public static void main(String[] args)  {
        BufferedWriter bfw = null;
        double lat = 0;
        double lng = 0;
        try {
            bfw = new BufferedWriter(new FileWriter(new File("E:\\javaeeTools\\latAndLng.txt")));
            for (int i = 0;i < 50000;i++){
                lat = getLatitude();
                lng = getLongitude();
                String jw = "{\"lat\":"+lat+",\"lng\":"+lng+"},";
                bfw.write(jw);
                bfw.newLine();
            }
            bfw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bfw!=null){
                try {
                    bfw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     *
     * 随机生成经度
     */
    public static double getLongitude(){
        int preffix = random.nextInt(62)+73;
        double suffix = random.nextDouble();
        return preffix+suffix;
    }

    /**
     *
     * 随机生成纬度
     */
    public static double getLatitude(){
        int preffix = random.nextInt(50)+3;
        double suffix = random.nextDouble();
        return preffix+suffix;
    }
}
