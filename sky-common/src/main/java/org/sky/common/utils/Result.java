package org.sky.common.utils;


import org.sky.common.constant.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 赵明明
 * @Date: 2018/10/18 9:39
 * @Description: 返回信息
 */
public class Result {


    /**
     * 返回自定义信息
     * @param msg
     * @return
     */
    public static Object setMsg(Object msg) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", msg);
        return resultMap;
    }


    /**
     * 返回自定义数据
     * @param data
     * @return
     */
    public static Object setData(Object data) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", data);
        return resultMap;
    }


    /**
     * 返回查询总数量跟自定义数据
     * @param data
     * @return
     */
    public static Object setData(Long total, Object data) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("data", data);
        return resultMap;
    }


    /**
     * 返回错误信息
     * @return
     */
    public static Object errorMsg() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", Constants.FAIL);
        return resultMap;
    }


    /**
     * 返回成功信息
     * @return
     */
    public static Object successMsg() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", Constants.SUCCESS);
        return resultMap;
    }


}
