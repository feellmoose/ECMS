package com.example.demo.common.enums;

import com.example.demo.common.exception.GlobalRunTimeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum RecordType {
    get(0),
    put(1),
    init(2),
    un_change(3);
    private final Integer type;
    public static RecordType fromValue(Integer value){
        for (RecordType recordType : values()) {
            if(recordType.getType().equals(value)){
                return recordType;
            }
        }
        throw new GlobalRunTimeException(ErrorEnum.COMMON_ERROR,"error record state");
    }

}
