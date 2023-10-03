package com.example.demo.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.common.enums.RecordType;
import com.example.demo.mqtt.enums.ActionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Box {
    @JsonIgnore
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer cabinetId;
    private Integer boxId;
    private Integer actionType;

}
