package org.sky.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.sky.common.pojo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class SuperExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Result> handlerException(Exception eception){
        log.error("捕捉到异常：{}，信息：{}", eception, eception.getMessage());
        if (eception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) eception;
            BindingResult bindingResult = e.getBindingResult();
            return this.binding(bindingResult);
        } else if (eception instanceof BindException) {
            BindException e = (BindException) eception;
            BindingResult bindingResult = e.getBindingResult();
            return this.binding(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.failMsg());
    }


    /**
     * 获取错误信息
     * @param bindingResult
     * @return
     */
    private ResponseEntity<Result> binding(BindingResult bindingResult) {
        String retVal = null;
        while (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError allError : allErrors) {
                retVal = allError.getDefaultMessage();
                break;
            }
        }
        return ResponseEntity.badRequest().body(Result.msg(retVal));
    }

}