package com.example.demo.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.enums.RecordState;
import com.example.demo.common.enums.RecordType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Record {
    @JsonIgnore
    @TableId(type = IdType.AUTO)
    private Integer id;
    @JsonIgnore
    private String messageId;
    @JsonIgnore
    private Integer type;
    @TableField(exist = false)
    private RecordType recordType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer userId;
    @JsonIgnore
    private Integer state;//状态
    @TableField(exist = false)
    private RecordState recordState;
    @TableField(value = "message_state")
    private String message;
    private String remark;
    @JsonIgnore
    private Integer boxGlobalId;
    @JsonIgnore
    private Integer componentIndex;
    @TableField(exist = false)
    private ComponentType.Component component;
    private Integer storageSize;
    private Date updateTime;

    public void fillParam(){
        this.setRecordState(RecordState.fromValue(this.state));
        this.setComponent(ComponentType.Index2componentMap.get(this.getComponentIndex()));
        this.setRecordType(RecordType.fromValue(this.type));
    }


}
