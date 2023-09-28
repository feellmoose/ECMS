package com.example.demo.mqtt.model;

import com.example.demo.mqtt.enums.ActionType;
import com.example.demo.mqtt.model.data.MessageData;
import com.example.demo.mqtt.utils.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MqttMessageDetail {
    private String messageId;
    private ActionType action;
    private MessageData data;
    private long timestamp;
    private String signature;

    public boolean checkSignature() {
        String local = MessageUtil.bytesToHex(MessageUtil.digest(timestamp, messageId)).substring(8, 24);
        return local.equals(this.signature);
    }

    public MqttMessageDetail(MessageData data,ActionType action) {
        this.data = data;
        this.timestamp = System.currentTimeMillis() / 1000;
        this.messageId = MessageUtil.getRandomHex();
        this.signature = MessageUtil.bytesToHex(MessageUtil.digest(this.timestamp, this.messageId)).substring(8, 24);
        this.action = action;
    }

}
