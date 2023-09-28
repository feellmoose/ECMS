package com.example.demo.mqtt.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevokeMessageData implements MessageData{
    private String parentId;
}
