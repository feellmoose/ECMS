package com.example.demo.common.service;

import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.model.UserInfo;

import java.util.Map;

public interface TransactionalComponentService {
    Map<String,Object> getComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, String remark, Integer size);
    Map<String,Object> modifyComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, ComponentType componentType, String remark, Integer size);

}
