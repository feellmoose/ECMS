package com.example.demo.mqtt.async;

import com.example.demo.common.service.CabinetService;
import com.example.demo.common.utils.JsonUtil;
import com.example.demo.mqtt.model.OneChipMessageModel;
import com.example.demo.mqtt.model.ServerMessageModel;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class EcmsMqttCallback implements MqttCallback {
    private CabinetService cabinetService;

    @Override
    public void connectionLost(Throwable throwable) {
        log.error("mqtt connection lost: {}", throwable.getMessage());
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        String payload = Arrays.toString(mqttMessage.getPayload());
        OneChipMessageModel oneChipMessage = JsonUtil.fromJson(payload, OneChipMessageModel.class);
        ServerMessageModel serverMessage = getParentMessage(oneChipMessage.getParentId());
        if (!oneChipMessage.checkSignature() || serverMessage == null) {
            log.error("error signature received: {}", payload);
            return;
        }
        switch (oneChipMessage.getStatus()) {
            case -1 -> log.error("open failed: {}", payload);
            case 0 -> {
                //TODO 返回给前端
                //TODO 修改sql操作为成功
            }
            default -> {
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("deliveryComplete: {}", iMqttDeliveryToken.isComplete());
    }

    private ServerMessageModel getParentMessage(String parentId) {
        //TODO
        return null;
    }
}
