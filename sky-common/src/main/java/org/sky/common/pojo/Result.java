package org.sky.common.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.sky.common.constant.Constants;

import java.io.Serializable;

/**
 * @Date: 2018/12/19 13:26
 * @Description: 返回结果类
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class Result<T> implements Serializable {

    public static final long serialVersionUID = 42L;

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


    public Result(String msg) {
        this.msg = msg;
    }


    public Result(T data) {
        this.data = data;
    }


    public Result(Long total, T data) {
        this.total = total;
        this.data = data;
    }


    /**
     * 返回成功信息
     * @return
     */
    public static Result<String> successMsg() {
        return new Result(Constants.SUCCESS);
    }


    /**
     * 返回错误信息
     * @return
     */
    public static Result<String> failMsg() {
        return new Result(Constants.FAIL);
    }



    /**
     * 返回自定义信息
     * @param msg
     * @return
     */
    public static Result<String> msg(String msg) {
        return new Result(msg);
    }


    /**
     * 返回自定义数据
     * @param data
     * @return
     */
    public static<T> Result<T> data(T data) {
        return new Result(data);
    }


    /**
     * 返回查询总数量跟自定义数据
     * @param data
     * @return
     */
    public static<T> Result<T> data(Long total, T data) {
        return new Result(total, data);
    }
}
