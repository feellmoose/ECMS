package com.example.demo.mqtt.async;

import com.example.demo.common.service.ComponentService;
import com.example.demo.common.utils.JsonUtil;
import com.example.demo.mqtt.model.OneChipMessageModel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class EcmsMqttCallback implements MqttCallback {
    @Resource
    private ComponentService componentService;

    @Override
    public void connectionLost(Throwable throwable) {
        log.error("mqtt connection lost: {}", throwable.getMessage());
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        String payload = Arrays.toString(mqttMessage.getPayload());
        OneChipMessageModel oneChipMessage = JsonUtil.fromJson(payload, OneChipMessageModel.class);
        if (!oneChipMessage.checkSignature()) {
            log.error("error signature received: {}", payload);
            return;
        }
        switch (oneChipMessage.getStatus()) {
            case -1 -> componentService.optFailure(oneChipMessage);
            case 0 -> componentService.optSuccess(oneChipMessage);
            default -> log.warn("error state of message: {}", oneChipMessage);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("deliveryComplete: {}", iMqttDeliveryToken.isComplete());
    }
}
