package com.example.demo.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Box {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer cabinetId;
    private Integer boxId;
    private Integer actionType;
}
