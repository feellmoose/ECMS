package com.example.demo.common.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.common.entity.Record;
import com.example.demo.common.entity.User;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.enums.RecordState;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.common.mapper.RecordMapper;
import com.example.demo.common.model.UserInfo;
import com.example.demo.common.service.BoxService;
import com.example.demo.common.service.ComponentService;
import com.example.demo.common.service.TransactionalComponentService;
import com.example.demo.common.utils.JsonUtil;
import com.example.demo.mqtt.enums.ActionType;
import com.example.demo.mqtt.model.MqttMessageDetail;
import com.example.demo.mqtt.model.data.ReplyMessageData;
import com.example.demo.mqtt.model.data.RevokeMessageData;
import com.example.demo.mqtt.service.MqttService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;


@Service
@Slf4j
public class ComponentServiceImpl implements ComponentService {
    @Resource
    private MqttService mqttService;
    @Resource
    private RecordMapper recordMapper;
    @Resource
    private BoxService boxService;
    @Resource
    private TransactionalComponentService transactionalComponentService;

    private static final String topic = "topic/box";
    private static final String NOT_OPEN_MESSAGE = "update-success-with-out-open";

    @Override
    public String getComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, String remark, Integer size) {
        synchronized (this) {
            Map<String, Object> map = transactionalComponentService.getComponent(userInfo, cabinetId, boxId, remark, size);
            return doWithOutTransaction((Record) map.get("record"), (MqttMessageDetail) map.get("messageDetail"));
        }
    }

    @Override
    public String modifyComponent(UserInfo userInfo, Integer cabinetId, Integer boxId, ComponentType componentType, String remark, Integer size) {
        synchronized (this) {
            Map<String, Object> map = transactionalComponentService.modifyComponent(userInfo, cabinetId, boxId, componentType, remark, size);
            return doWithOutTransaction((Record) map.get("record"), (MqttMessageDetail) map.get("messageDetail"));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String modifyWithOutOpen(UserInfo userInfo, Integer cabinetId, Integer boxId, ComponentType componentType, String remark, Integer size) {
        Integer globalId = Optional.ofNullable(boxService.getBox(cabinetId, boxId))
                .orElseThrow(() -> new GlobalRunTimeException(ErrorEnum.COMMON_ERROR, "no such box"))
                .getId();
        Record oldRecord = recordMapper.selectOne(Wrappers.lambdaQuery(Record.class)
                .eq(Record::getBoxGlobalId, globalId)
                .orderByDesc(Record::getId)
                .last("limit 1 for update"));
        if (oldRecord == null) {
            throw new GlobalRunTimeException(ErrorEnum.COMMON_ERROR, "no such storage recorded");
        }
        int type = 3;
        Integer oldSize = oldRecord.getStorageSize();
        if (oldSize > size) {
            type = 0;
        }
        if (oldSize < size) {
            type = 1;
        }
        if (componentType == null) {
            componentType = ComponentType.getByIndex(oldRecord.getComponentIndex());
        }
        User user = userInfo.getUser();
        Record record = Record.builder()
                .userId(user.getId())
                .messageId(NOT_OPEN_MESSAGE)
                .componentIndex(componentType.getIndex())
                .state(RecordState.success.getValue())
                .remark(remark)
                .storageSize(size)
                .boxGlobalId(globalId)
                .type(type)
                .build();
        recordMapper.insert(record);
        return NOT_OPEN_MESSAGE;
    }


    @Override
    public String optSuccess(ReplyMessageData replyMessageData, Record old) {
        old.setState(RecordState.success.getValue());
        old.setMessage(replyMessageData.getDetail());
        recordMapper.updateById(old);
        return replyMessageData.getDetail();
    }

    @Override
    public String optFailure(ReplyMessageData replyMessageData, Record old) {
        old.setState(RecordState.failure.getValue());
        old.setMessage(replyMessageData.getDetail());
        recordMapper.updateById(old);
        throw new GlobalRunTimeException(ErrorEnum.COMMON_ERROR, replyMessageData.getDetail());
    }

    private String doWithOutTransaction(Record record, MqttMessageDetail messageDetail) {
        //前面第一次插入已经成功了，这里是针对每个record单独的流程，所以不需要上锁也是可以的。
        MqttMessageDetail mqttMessageDetail;
        try {
            IMqttToken token = mqttService.subscribeWithResponse(topic);
            token.waitForCompletion(5000);
            String payload = Arrays.toString(token.getResponse().getPayload());
            mqttMessageDetail = JsonUtil.fromJson(payload, MqttMessageDetail.class);
        } catch (MqttException e) {
            if (e.getReasonCode() == 32000) {
                mqttService.publish(topic, new MqttMessageDetail(new RevokeMessageData(messageDetail.getMessageId()), ActionType.revoke));
                record.setState(RecordState.timeout.getValue());
                recordMapper.updateById(record);
                throw new GlobalRunTimeException(ErrorEnum.MQTT_ERROR, "call back timeout");
            } else {
                throw new GlobalRunTimeException(ErrorEnum.MQTT_ERROR, "call back error");
            }
        }
        if (!mqttMessageDetail.checkSignature()) {
            log.error("error signature received: {}", mqttMessageDetail);
            throw new GlobalRunTimeException(ErrorEnum.COMMON_ERROR, "error signature received");
        }
        ReplyMessageData replyMessageData = (ReplyMessageData) mqttMessageDetail.getData();
        return switch (replyMessageData.getStatus()) {
            case -1 -> optFailure(replyMessageData, record);
            case 0 -> optSuccess(replyMessageData, record);
            default -> {
                log.warn("error status of message: {}", mqttMessageDetail);
                throw new GlobalRunTimeException(ErrorEnum.COMMON_ERROR, "error status of message");
            }
        };

    }

}
