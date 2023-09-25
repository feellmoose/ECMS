package com.example.demo.common.exception;

import com.example.demo.common.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalRunTimeException extends RuntimeException{
    private ErrorEnum errorEnum;
    private String msg;
    public GlobalRunTimeException(ErrorEnum errorEnum){
        this.errorEnum = errorEnum;
    }

}
