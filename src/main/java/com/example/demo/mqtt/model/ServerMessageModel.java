package com.example.demo.mqtt.model;

import com.example.demo.common.enums.ComponentType;
import com.example.demo.mqtt.utils.MessageUtil;
import lombok.Data;

@Data
public class ServerMessageModel {
    private String messageId;
    private Integer userId;//谁
    private String username;
    private Integer cabinetId;//在哪个柜子（目前都是0）
    private Integer boxId;//第几列
    private String detail;
    private long timestamp;//时间戳
    private String signature;//MD5签名（时间戳加盐）

    public ServerMessageModel(Integer userId, String username, Integer cabinetId, Integer boxId, ComponentType componentType, Integer num) {
        this.userId = userId;
        this.username = username;
        this.cabinetId = cabinetId;
        this.boxId = boxId;
        this.detail = "取" + componentType.getDescription() + "元件" + num + "个";
        this.timestamp = System.currentTimeMillis() / 1000;
        this.messageId = MessageUtil.getRandomHex();
        this.signature = MessageUtil.bytesToHex(MessageUtil.digest(this.timestamp, this.messageId)).substring(8, 24);
    }


}
