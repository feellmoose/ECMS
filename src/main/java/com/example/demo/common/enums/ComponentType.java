package com.example.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ComponentType {
    XXX(1,"xxx"),
    ;
    private final Integer index;
    private final String description;
}
