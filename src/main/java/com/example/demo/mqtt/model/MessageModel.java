package com.example.demo.mqtt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageModel {
    private String userId;
    private int size;
    private int componentId;
    private int cabinetId;
    private int optionType;
    private LocalDateTime timestamp;

    //TODO 确定和编写测试转换
    public static String encodeMessage(MessageModel messageModel){
        return null;
    }

    public static MessageModel decodeMessage(String string){
        return null;
    }

}
