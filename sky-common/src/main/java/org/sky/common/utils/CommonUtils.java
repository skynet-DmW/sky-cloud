package org.sky.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/25 14:13
 * @Description: 通用工具类
 */
public class CommonUtils {


    /**
     * 获取文件后缀
     * @param fileName 文件
     * @return
     */
    public static String getFileSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."),fileName.length());
    }


    /**
     * 获取订单号
     * 规则：年月日时分秒 + 4位随机数
     * @return
     */
    public static String getOrderNo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat format = new SimpleDateFormat("mmss");
        Date date = new Date();
        Integer dateNum = Integer.valueOf(format.format(date)) * 4 / 3;
        return dateFormat.format(date) + dateNum;
    }


    /**
     * 获取4位随机数
     * @return
     */
    public static String getRandom() {
        String s = String.valueOf(System.nanoTime());
        String nanoTime = StringUtils.substring(s, s.length() - 4, s.length());
        return nanoTime;
    }

}
