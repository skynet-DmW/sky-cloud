package org.sky.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 日期Util类
 */
@Slf4j
public class DateUtils {


    private static final DateTimeFormatter yyyyMMddHHmmssFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter yyyyMMddFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final ZoneId zoneId = ZoneOffset.systemDefault();


    /**
     * 获取时间戳
     */
    public static long getTimestamp() {
        return LocalDateTime.now().atZone(zoneId).toEpochSecond();
    }


    /**
     * 获取时间字符串
     *
     * @param date
     * @return
     */
    public static String yyyyMMddHHmmssFormat(LocalDateTime date) {
        return yyyyMMddHHmmssFormat.format(date);
    }


    /**
     * 获取时间字符串
     *
     * @return
     */
    public static LocalDateTime yyyyMMddHHmmssParse(String date) {
        return LocalDateTime.parse(date, yyyyMMddHHmmssFormat);
    }


    /**
     * 获取时间字符串
     *
     * @param date
     * @return
     */
    public static String yyyyMMddFormat(LocalDate date) {
        return yyyyMMddFormat.format(date);
    }


   /**
     * 获取时间字符串
     *
     * @param date
     * @return
     */
    public static LocalDate yyyyMMddParse(String date) {
        return LocalDate.parse(date, yyyyMMddFormat);
    }


    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int differentDaysByMillisecond(LocalDateTime beginDate,LocalDateTime endDate) {
        int days = (int) ((endDate.atZone(zoneId).toEpochSecond() - beginDate.atZone(zoneId).toEpochSecond()) / (3600*24));
        return days;
    }


}
