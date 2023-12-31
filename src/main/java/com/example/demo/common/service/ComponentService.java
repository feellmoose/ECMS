package com.example.demo.common.service;

import com.example.demo.common.entity.Record;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.model.UserInfo;
import com.example.demo.mqtt.model.data.ReplyMessageData;

import java.util.List;

public interface ComponentService {
    String getComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, String remark, Integer size);
    String modifyWithOutOpen(UserInfo userInfo, Integer cabinetId, Integer boxId, ComponentType componentType, String remark, Integer size);
    String modifyComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, ComponentType componentType, String remark, Integer size);

    String optSuccess(ReplyMessageData replyMessageData, Record old);

    String optFailure(ReplyMessageData replyMessageData, Record old);

}
