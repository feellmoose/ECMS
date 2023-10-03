package com.example.demo.common.enums;

import com.example.demo.common.exception.GlobalRunTimeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum RecordState {
    build(0),
    waiting(1),
    success(2),
    failure(3),
    timeout(4)
    ;
    private final Integer value;
    public static RecordState fromValue(Integer value){
        for (RecordState recordState:values()) {
            if(recordState.value.equals(value)){
                return recordState;
            }
        }
        throw new GlobalRunTimeException(ErrorEnum.COMMON_ERROR,"error record state");
    }
}
