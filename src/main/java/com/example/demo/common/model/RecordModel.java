package com.example.demo.common.model;

import com.example.demo.common.entity.Record;
import com.example.demo.common.enums.ComponentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordModel {
    private Record record;
    private ComponentType componentType;

}
