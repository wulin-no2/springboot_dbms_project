package com.lina.exception;

import com.lina.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlogalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result ex(Exception e){
        e.printStackTrace();
        return Result.error("Global error. Contact system manager.");
    }
}
