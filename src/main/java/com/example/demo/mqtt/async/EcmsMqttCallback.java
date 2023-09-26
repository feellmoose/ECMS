package com.example.demo.mqtt.async;

import com.example.demo.mqtt.model.MessageModel;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class EcmsMqttCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable throwable) {
        log.error("mqtt connection lost: {}", throwable.getMessage());
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        String payload = Arrays.toString(mqttMessage.getPayload());
        MessageModel messageModel = MessageModel.decodeMessage(payload);
        //TODO


    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("deliveryComplete: {}", iMqttDeliveryToken.isComplete());
    }
}
