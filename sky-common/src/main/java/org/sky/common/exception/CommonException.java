package org.sky.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 */
@Getter
@Setter
public class CommonException extends RuntimeException {


    private int code;

    private String msg;

    public CommonException() {
        this.code = 500;
        this.msg = super.getMessage();
    }

    public CommonException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}