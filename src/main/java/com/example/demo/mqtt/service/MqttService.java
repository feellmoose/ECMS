package com.example.demo.mqtt.service;

import com.example.demo.mqtt.model.MqttMessageDetail;
import com.example.demo.mqtt.model.data.MessageData;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;

public interface MqttService {

    void publish(String topic, MqttMessageDetail messageDetail);

    IMqttToken subscribeWithResponse(String topic);

    MqttClient mqttClient();

}
