package com.example.demo.mqtt.async;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EcmsMqttCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable throwable) {
        log.error("mqtt connection lost: {}", throwable.getMessage());
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        log.info("mqtt filter: message handle success.");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("deliveryComplete: {}", iMqttDeliveryToken.isComplete());
    }
}
