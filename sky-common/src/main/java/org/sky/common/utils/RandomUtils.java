package org.sky.common.utils;

/**
 * @Auther: 赵明明
 * @Date: 2018/11/12 9:54
 * @Description: 随机数工具类
 */
public class RandomUtils {


    /**
     * 获取四位随机数
     * @return
     */
    public static String getFourRandom() {
        double random = Math.random() * 9000 + 1000;
        return String.valueOf((int) random);
    }

}
