package com.example.demo.common.service;

import com.example.demo.common.entity.Box;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.entity.User;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.mqtt.model.OneChipMessageModel;

public interface ComponentService {

    void saveComponent(User user, Cabinet cabinet, Box box, ComponentType componentType, Integer size);

    void optSuccess(OneChipMessageModel oneChipMessage);

    void optFailure(OneChipMessageModel oneChipMessage);

    void putComponent(User user, Cabinet cabinet, Box box, ComponentType componentType, Integer size);

}
