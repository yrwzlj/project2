package com.yrw.util;

import com.alibaba.excel.EasyExcel;

import java.io.*;
import java.util.List;
import java.util.Random;

public class FileUtil {
    //把一个文件中的内容读取成一个String字符串
    public static String getStr(File jsonFile) {
        String jsonStr = "";
        try {
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static <T>  void exportXls(List<T> list, Class<T> tClass,String name){
        String path = "./" + name + ".xls";
        File file = new File(path);
        Random random = new Random();
        while (file.exists()) {
            path = changePath(path);
            file = new File(path);
        }
        EasyExcel.write(path, tClass).sheet(name).doWrite(list);
    }

    private static String changePath(String path) {
        String[] split = path.split("\\.");
        String result = "";
        Random random = new Random();

        split[1] += random.nextInt(9);
        result = result + "." + split[1] + "." + split[2];

        return result;
    }
}
