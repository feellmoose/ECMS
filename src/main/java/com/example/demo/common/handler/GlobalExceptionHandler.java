package com.example.demo.common.handler;

import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.common.model.response.GlobalResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(GlobalRunTimeException.class)
    public <T> GlobalResponse<T> globalResponse(GlobalRunTimeException e){
        return GlobalResponse.failure(e);
    }

}
