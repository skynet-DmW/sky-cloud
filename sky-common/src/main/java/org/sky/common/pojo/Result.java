package org.sky.common.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.sky.common.constant.Constants;

import java.io.Serializable;

/**
 * @Date: 2018/12/19 13:26
 * @Description: 返回结果类
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    public static final long serialVersionUID = 42L;


    /**
     * 状态码
     */
    private int code;


    /**
     * 消息
     */
    private String msg;


    /**
     * 数据
     */
    private T data;


    /**
     * 总数
     */
    private Long total;


    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }


    public Result(int code, Long total, T data) {
        this.code = code;
        this.total = total;
        this.data = data;
    }


    /**
     * 返回成功信息
     * @return
     */
    public static Result<String> successMsg() {
        return new Result(Constants.ONE, Constants.SUCCESS);
    }


    /**
     * 返回错误信息
     * @return
     */
    public static Result<String> failMsg() {
        return new Result(Constants.ZERO, Constants.FAIL);
    }



    /**
     * 返回自定义信息
     * @param msg
     * @return
     */
    public static Result<String> msg(String msg) {
        return new Result(Constants.ZERO, msg);
    }


    /**
     * 返回自定义数据
     * @param data
     * @return
     */
    public static<T> Result<T> data(T data) {
        return new Result(Constants.ONE, data);
    }


    /**
     * 返回查询总数量跟自定义数据
     * @param data
     * @return
     */
    public static<T> Result<T> data(Long total, T data) {
        return new Result(Constants.ONE, total, data);
    }
}
