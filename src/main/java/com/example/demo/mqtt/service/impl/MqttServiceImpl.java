package com.example.demo.mqtt.service.impl;


import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.mqtt.service.MqttService;
import jakarta.annotation.Resource;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class MqttServiceImpl implements MqttService {

    @Resource
    private MqttClient mqttClient;

    private static final int qos = 1;

    @Override
    public void publish(String topic, MqttMessage message) {
        try {
            mqttClient.publish(topic,message.getPayload(),qos,true);
        } catch (MqttException e) {
            throw new GlobalRunTimeException(ErrorEnum.MQTT_ERROR, e.getMessage());
        }
    }

    @Override
    public void subscribe(String topic) {
        try {
            mqttClient.subscribe(topic,qos);
        } catch (MqttException e) {
            throw new GlobalRunTimeException(ErrorEnum.MQTT_ERROR, e.getMessage());
        }

    }
}
