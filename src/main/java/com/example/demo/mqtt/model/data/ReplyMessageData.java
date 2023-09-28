package com.example.demo.mqtt.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyMessageData implements MessageData{
    private Integer status;
    private String detail;
    private String parentId;
}
