package com.example.demo.common.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.common.entity.Record;
import com.example.demo.common.entity.User;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.enums.RecordState;
import com.example.demo.common.enums.RecordType;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.common.mapper.RecordMapper;
import com.example.demo.common.model.UserInfo;
import com.example.demo.common.service.BoxService;
import com.example.demo.common.service.TransactionalComponentService;
import com.example.demo.mqtt.enums.ActionType;
import com.example.demo.mqtt.model.MqttMessageDetail;
import com.example.demo.mqtt.model.data.OpenMessageData;
import com.example.demo.mqtt.service.MqttService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class TransactionalComponentServiceImpl implements TransactionalComponentService {
    @Resource
    private MqttService mqttService;
    @Resource
    private RecordMapper recordMapper;
    @Resource
    private BoxService boxService;
    private static final String topic = "topic/box";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> getComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, String remark, Integer size) {
        Integer globalId = Optional.ofNullable(boxService.getBox(cabinetId, boxId))
                .orElseThrow(() -> new GlobalRunTimeException(ErrorEnum.COMMON_ERROR, "no such box"))
                .getId();
        Record record = recordMapper.selectOne(Wrappers.lambdaQuery(Record.class)
                .eq(Record::getBoxGlobalId, globalId)
                .last("limit 1 for update"));
        if (record == null) {
            throw new GlobalRunTimeException(ErrorEnum.COMMON_ERROR, "no such storage recorded");
        }
        if (record.getStorageSize() < size) {
            throw new GlobalRunTimeException(ErrorEnum.COMMON_ERROR, "no enough component");
        }
        return modifyComponent(userInfo, cabinetId, boxId, globalId, ComponentType.getByIndex(record.getComponentIndex()), remark, size, RecordType.get.getType());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> modifyComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, ComponentType componentType, String remark, Integer size) {
        Integer globalId = Optional.ofNullable(boxService.getBox(cabinetId, boxId))
                .orElseThrow(() -> new GlobalRunTimeException(ErrorEnum.COMMON_ERROR, "no such box"))
                .getId();
        Record record = recordMapper.selectOne(Wrappers.lambdaQuery(Record.class)
                .eq(Record::getBoxGlobalId, globalId)
                .last("limit 1 for update"));
        if (record == null) {
            throw new GlobalRunTimeException(ErrorEnum.COMMON_ERROR, "no such storage recorded");
        }
        int type = 3;
        Integer oldSize = record.getStorageSize();
        if (oldSize > size) {
            type = 0;
        }
        if (oldSize < size) {
            type = 1;
        }
        if (componentType == null) {
            componentType = ComponentType.getByIndex(record.getComponentIndex());
        }
        return modifyComponent(userInfo, cabinetId, boxId, globalId, componentType, remark, size, type);
    }

    private Map<String, Object> modifyComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, Integer globalId, ComponentType componentType, String remark, Integer size, Integer type) {
        User user = userInfo.getUser();
        OpenMessageData data = new OpenMessageData(user.getId(), user.getName(), cabinetId, boxId, componentType, size, type);
        MqttMessageDetail messageDetail = new MqttMessageDetail(data, ActionType.open);
        mqttService.publish(topic, messageDetail);
        Record record = Record.builder()
                .userId(user.getId())
                .messageId(messageDetail.getMessageId())
                .componentIndex(componentType.getIndex())
                .state(RecordState.waiting.getValue())
                .remark(remark)
                .storageSize(size)
                .boxGlobalId(globalId)
                .type(type)
                .build();
        recordMapper.insert(record);
        return Map.of("record", record, "messageDetail", messageDetail);
    }

}
