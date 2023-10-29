package com.example.demo.common.enums;

import com.example.demo.common.exception.GlobalRunTimeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    ROOT(0,"超管"),
    ADMIN(1,"管理员"),
    USER(2,"用户")
    ;
    private final Integer type;
    private final String description;

    static public RoleType getType(Integer role){
        for (RoleType roleType: values()) {
            if(roleType.type.equals(role)){
                return roleType;
            }
        }
        throw new GlobalRunTimeException(ErrorEnum.PARAM_ERROR,"no match roleType");
    }

}
