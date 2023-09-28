package com.example.demo.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer boxId;
    private Integer componentId;
    private Integer storageSize;
    private Integer storageUnit;
}
