package com.example.demo.mqtt.service;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MqttService {

    void publish(String topic,MqttMessage message);

    void subscribe(String topic);

}
