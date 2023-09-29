package com.example.demo.common.service.impl;

import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.model.CabinetModel;
import com.example.demo.common.model.UserInfo;
import com.example.demo.common.service.ComponentService;
import com.example.demo.mqtt.model.data.ReplyMessageData;
import org.springframework.stereotype.Service;

@Service
public class ComponentServiceImpl implements ComponentService {


    @Override
    public CabinetModel addComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, ComponentType componentType, Integer size) {
        return null;
    }

    @Override
    public CabinetModel takeComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, ComponentType componentType, Integer size) {
        return null;
    }

    @Override
    public CabinetModel modifyComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, ComponentType componentType, Integer size) {
        return null;
    }

    @Override
    public void optSuccess(ReplyMessageData replyMessageData) {

    }

    @Override
    public void optFailure(ReplyMessageData replyMessageData) {

    }

}
