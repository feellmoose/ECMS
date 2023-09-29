package com.example.demo.common.service;

import com.example.demo.common.entity.Box;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.entity.User;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.model.CabinetModel;
import com.example.demo.common.model.UserInfo;
import com.example.demo.mqtt.model.data.ReplyMessageData;

public interface ComponentService {
    CabinetModel addComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, ComponentType componentType, Integer size);
    CabinetModel takeComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, ComponentType componentType,Integer size);
    CabinetModel modifyComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, ComponentType componentType,Integer size);

    void optSuccess(ReplyMessageData replyMessageData);

    void optFailure(ReplyMessageData replyMessageData);

}
