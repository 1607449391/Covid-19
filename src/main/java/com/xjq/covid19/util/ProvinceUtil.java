package com.xjq.covid19.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@author：徐家庆
 *@time：2020-12-08 21:59
 *@description：
 *
 */
@Component
public class ProvinceUtil {

    public String[] provinces = new String[]{"北京","天津","河北","山西","内蒙古","辽宁","吉林","黑龙江",
            "上海","江苏","浙江","安徽","福建","江西","山东","河南","湖北","湖南","广东","广西",
            "海南","重庆","四川","贵州","云南","西藏","陕西","甘肃","青海","宁夏","新疆","台湾","澳门","香港"};
    public static Map<String,Integer> provinceCode = new HashMap<>();
    public static Map<String,String> provinceJsMap = new HashMap<String,String>();

    public ProvinceUtil(){
            provinceJsMap.put("浙江", "zhejiang.js");
            provinceJsMap.put("安徽", "anhui.js");
            provinceJsMap.put("澳门", "aomen.js");
            provinceJsMap.put("北京", "beijing.js");
            provinceJsMap.put("重庆", "chongqing.js");
            provinceJsMap.put("福建", "fujian.js");
            provinceJsMap.put("甘肃", "gansu.js");
            provinceJsMap.put("广东", "guangdong.js");
            provinceJsMap.put("广西", "guangxi.js");
            provinceJsMap.put("贵州", "guizhou.js");
            provinceJsMap.put("海南", "hainan.js");
            provinceJsMap.put("河北", "hebei.js");
            provinceJsMap.put("黑龙江", "heilongjiang.js");
            provinceJsMap.put("河南", "henan.js");
            provinceJsMap.put("湖北", "hubei.js");
            provinceJsMap.put("湖南", "hunan.js");
            provinceJsMap.put("江苏", "jiangsu.js");
            provinceJsMap.put("江西", "jiangxi.js");
            provinceJsMap.put("吉林", "jilin.js");
            provinceJsMap.put("辽宁", "liaoning.js");
            provinceJsMap.put("内蒙古", "neimenggu.js");
            provinceJsMap.put("宁夏", "ningxia.js");
            provinceJsMap.put("青海", "qinghai.js");
            provinceJsMap.put("山东", "shandong.js");
            provinceJsMap.put("上海", "shanghai.js");
            provinceJsMap.put("山西", "shanxi.js");
            provinceJsMap.put("陕西", "shaanxi.js");
            provinceJsMap.put("四川", "sichuan.js");
            provinceJsMap.put("台湾", "taiwan.js");
            provinceJsMap.put("天津", "tianjin.js");
            provinceJsMap.put("香港", "xianggang.js");
            provinceJsMap.put("新疆", "xinjiang.js");
            provinceJsMap.put("西藏", "xizang.js");
            provinceJsMap.put("云南", "yunnan.js");
        }


   /* public ProvinceUtil(){

        BufferedReader br = null;
        try {
            String root = System.getProperty("user.dir");
            String FileName="province.txt";
            String filePath = root+"\\src\\main\\resources\\"+FileName;
            FileReader fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null){
                String[] strs = line.split(":");
                if(!provinceCode.containsKey(strs[1])){
                    provinceCode.put(strs[1],Integer.parseInt(strs[0]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
