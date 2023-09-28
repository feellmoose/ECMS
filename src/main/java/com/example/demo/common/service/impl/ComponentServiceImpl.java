package com.example.demo.common.service.impl;

import com.example.demo.common.entity.Box;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.entity.User;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.service.ComponentService;
import com.example.demo.mqtt.model.data.ReplyMessageData;
import org.springframework.stereotype.Service;

@Service
public class ComponentServiceImpl implements ComponentService {
    @Override
    public void saveComponent(User user, Cabinet cabinet, Box box, ComponentType componentType, Integer size) {

    }

    @Override
    public void optSuccess(ReplyMessageData replyMessageData) {

    }

    @Override
    public void optFailure(ReplyMessageData replyMessageData) {

    }

    @Override
    public void putComponent(User user, Cabinet cabinet, Box box, ComponentType componentType, Integer size) {

    }
}
