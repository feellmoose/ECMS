package com.example.demo.mqtt.model;

import com.example.demo.mqtt.utils.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneChipMessageModel {
    private String messageId;
    private Integer status;
    private String detail;
    private String parentId;
    private long timestamp;
    private String signature;

    public boolean checkSignature() {
        String local = MessageUtil.bytesToHex(MessageUtil.digest(timestamp, messageId)).substring(8, 24);
        return local.equals(this.signature);
    }

}
