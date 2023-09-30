package com.example.demo.mqtt.service.impl;


import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.common.utils.JsonUtil;
import com.example.demo.mqtt.async.EcmsMqttCallback;
import com.example.demo.mqtt.model.MqttMessageDetail;
import com.example.demo.mqtt.model.data.MessageData;
import com.example.demo.mqtt.service.MqttService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

import static com.example.demo.mqtt.config.MqttServiceConfiguration.*;

@Component
@Slf4j
public class MqttServiceImpl implements MqttService {

    @Resource
    private EcmsMqttCallback ecmsMqttCallback;
    private MqttClient mqttClient = getMqttClient();

    private static final int qos = 1;

    @Override
    public void publish(String topic, MqttMessageDetail messageDetail) {
        checkConnection();
        try {
            mqttClient.publish(topic, JsonUtil.toJson(messageDetail).getBytes(), qos, true);
        } catch (MqttException e) {
            throw new GlobalRunTimeException(ErrorEnum.MQTT_ERROR, e.getMessage());
        }
    }

    @Override
    public IMqttToken subscribeWithResponse(String topic) {
        checkConnection();
        IMqttToken token;
        try {
            token = mqttClient.subscribeWithResponse(topic, qos);
        } catch (MqttException e) {
            throw new GlobalRunTimeException(ErrorEnum.MQTT_ERROR, e.getMessage());
        }
        return token;
    }


    private MqttClient getMqttClient() {
        if(mqttClient!=null&& mqttClient.isConnected()){
            return mqttClient;
        }
        log.info("mqtt server broker: {}", broker);
        log.info("mqtt server username: {}", username);
        log.info("mqtt server connecting...");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(10000);//10秒超时
        options.setUserName(username);
        options.setKeepAliveInterval(1000);//1秒心跳间隔
        options.setPassword(password.toCharArray());
        MqttClient mqttClient = null;
        try {
            mqttClient = new MqttClient(broker, clientId, new MemoryPersistence());
            mqttClient.setCallback(ecmsMqttCallback);
            mqttClient.connect(options);
        } catch (MqttException e) {
            log.warn("mqtt server connect failed");
            return null;
        }
        log.info("mqtt server connect success");
        return mqttClient;
    }

    public MqttClient mqttClient(){
        checkConnection();
        return mqttClient;
    }

    private void checkConnection() {
        if (mqttClient == null) {
            mqttClient = getMqttClient();
            if (mqttClient == null) {
                throw new GlobalRunTimeException(ErrorEnum.MQTT_ERROR, "mqtt server connect failed");
            }
        }
        if (!mqttClient.isConnected()) {
            try {
                mqttClient.reconnect();
            } catch (MqttException e) {
                throw new GlobalRunTimeException(ErrorEnum.PARAM_ERROR, e.getMessage());
            }
        }
    }
}
