package com.example.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    COMMON_ERROR(1000,"common error"),
    PARAM_ERROR(1001,"param error"),
    TOKEN_ERROR(1002,"token error"),
    PERMISSION_ERROR(1003,"permission error"),
    MQTT_ERROR(2000,"mqtt service error"),
    MQTT_TIME_OUT_ERROR(2001,"mqtt time out"),
    INTERNAL_SERVER_ERROR(500,"internal server error")
    ;

    private final Integer code;
    private final String defaultMsg;
}
