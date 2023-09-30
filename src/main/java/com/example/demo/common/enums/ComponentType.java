package com.example.demo.common.enums;

import com.example.demo.common.exception.GlobalRunTimeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ComponentType {
    XXX(1,"xxx"),
    ;
    private final Integer index;
    private final String description;



    public static ComponentType getByIndex(Integer index) {
        for (ComponentType componentType:ComponentType.values()){
            if(index.equals(componentType.index)){
                return componentType;
            }
        }
        throw new GlobalRunTimeException(ErrorEnum.PARAM_ERROR,"component index invalid");
    }
}
