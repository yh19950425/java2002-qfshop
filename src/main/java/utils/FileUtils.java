package com.java.yh.utils;

public class FileUtils {

    public static String getExName(String filename){
        String[] split = filename.split("\\.");

        if (split.length==1){
            return "";
        }else {
            int length = split.length;

            return "."+split[length-1];
        }
    }

}