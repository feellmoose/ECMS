package com.example.demo.common.model.response;

import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.exception.GlobalRunTimeException;
import lombok.Data;

@Data
public class GlobalResponse<T> {
    private Boolean success;
    private Integer code;
    private String msg;
    private T data;
    private GlobalResponse(T data){
        this.success = true;
        this.code = null;
        this.msg = null;
        this.data = data;
    }
    private GlobalResponse(GlobalRunTimeException e){
        this.success = false;
        this.code = e.getErrorEnum().getCode();
        this.msg = e.getErrorEnum().getDefaultMsg()+", "+e.getMsg();
        this.data = null;
    }
    private GlobalResponse(ErrorEnum errorEnum){
        this.success = false;
        this.code = errorEnum.getCode();
        this.msg = errorEnum.getDefaultMsg();
        this.data = null;
    }

    public static <T> GlobalResponse<T> success(T data){
        return new GlobalResponse<>(data);
    }
    public static <T> GlobalResponse<T> failure(GlobalRunTimeException e){
        return new GlobalResponse<>(e);
    }
    public static <T> GlobalResponse<T> failure(ErrorEnum e){
        return new GlobalResponse<>(e);
    }

}