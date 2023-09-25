package com.example.demo.common.enums;

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
}
