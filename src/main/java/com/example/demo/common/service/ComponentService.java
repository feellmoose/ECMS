package com.example.demo.common.service;

import com.example.demo.common.entity.Box;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.entity.User;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.mqtt.model.data.ReplyMessageData;

public interface ComponentService {

    void saveComponent(User user, Cabinet cabinet, Box box, ComponentType componentType, Integer size);

    void optSuccess(ReplyMessageData replyMessageData);

    void optFailure(ReplyMessageData replyMessageData);

    void putComponent(User user, Cabinet cabinet, Box box, ComponentType componentType, Integer size);

}
