package com.example.demo.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Box {
    private Integer id;
    private Integer cabinetId;
    private Integer boxGlobalId;
    private Integer actionType;
}
