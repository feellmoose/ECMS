package com.example.demo.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String messageId;
    private Integer type;
    private Integer userId;
    private Integer state;
    private String messageState;
    private Integer storageId;
    private Integer remark;
    private Integer boxId;
    private Integer componentId;
    private Integer storageSize;
    private Date updateTime;
}
