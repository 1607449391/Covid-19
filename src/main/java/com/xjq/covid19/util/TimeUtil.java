package com.xjq.covid19.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 *@author：徐家庆
 *@time：2020-12-19 20:01
 *@description：
 *
 */
public class TimeUtil {


    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
    private static Calendar calendar = Calendar.getInstance();

    /**
     * 解析yyyy.MM.dd hh:mm:ss 格式的数据返回时间戳
     * @param date
     * @return   时间戳
     */
    public static long getTimestampForWY(String date){
        long time = 0;
        try {
            time = sdf1.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 解析MM-dd hh:mm格式的数据返回时间戳
     * @param date
     * @return   时间戳
     */
    public static long getTimestampForDXY(String date) {
        long time = 0;
        String date1 = calendar.get(Calendar.YEAR)+"-"+date+":01";
        try {
            time = sdf2.parse(date1).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 解析2020年12月19日 18:38 格式的数据返回时间戳
     * @param date
     * @return   时间戳
     */
    public static long getTimestampForALi(String date) {
        long time = 0;
        date = date+":02";
        try {
            time = sdf3.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
    /**
     * 解析2020-12-19日 18:38:00 格式的数据返回时间戳
     * @param date
     * @return   时间戳
     */
    public static int getTimestampForGlobal(String date) {
        int time = 0;
        try {
            time = (int) sdf2.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
}
