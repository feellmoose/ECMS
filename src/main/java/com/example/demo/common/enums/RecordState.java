package com.example.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

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
}
