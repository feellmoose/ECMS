package com.example.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    COMMON_ERROR(1000,"common error"),
    PARAM_ERROR(1001,"param error"),
    MQTT_ERROR(2000,"mqtt service error"),
    MQTT_TIME_OUT_ERROR(2001,"mqtt time out"),

    ;

    private final Integer code;
    private final String defaultMsg;
}
