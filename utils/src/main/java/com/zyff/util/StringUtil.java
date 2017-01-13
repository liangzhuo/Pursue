package com.zyff.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by liangz on 2015/12/26.
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str){
        if(str != null){
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否非空
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    /**
     * 根据分隔符进行分割
     * @param content
     * @param splitString
     * @return
     */
    public static String[] splitString(String content, String splitString) {
        return content.split(splitString);
    }
}