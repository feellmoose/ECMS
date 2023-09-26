package com.example.demo.mqtt.config;

import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.mqtt.async.EcmsMqttCallback;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MqttServiceConfiguration {
    @Value("${mqtt.broker}")
    private String broker;
    @Value("${mqtt.username}")
    private String username;
    @Value("${mqtt.password}")
    private String password;
    @Value("${mqtt.clientId}")
    private String clientId;

    @Bean
    public MqttClient mqttClient(MqttCallback mqttCallback){
        log.info("mqtt server broker: {}",broker);
        log.info("mqtt server username: {}",username);
        log.info("mqtt server connecting...");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(10000);//10秒超时
        options.setUserName(username);
        options.setKeepAliveInterval(1000);//1秒心跳间隔
        options.setPassword(password.toCharArray());
        MqttClient mqttClient = null;
        try {
            mqttClient = new MqttClient(broker,clientId,new MemoryPersistence());
            mqttClient.setCallback(mqttCallback);
            mqttClient.connect(options);
        } catch (MqttException e) {
            throw new GlobalRunTimeException(ErrorEnum.MQTT_ERROR,e.getMessage());
        }
        log.info("mqtt server connect success");
        return mqttClient;
    }

}
