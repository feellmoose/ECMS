package com.example.demo.mqtt.config;

import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.mqtt.async.EcmsMqttCallback;
import jakarta.annotation.PostConstruct;
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
    private String pBroker;
    @Value("${mqtt.username}")
    private String pUsername;
    @Value("${mqtt.password}")
    private String pPassword;
    @Value("${mqtt.client-id}")
    private String pClientId;

    public static String broker;
    public static String username;
    public static String password;
    public static String clientId;


    @PostConstruct
    public void setValue(){
        broker = pBroker;
        username = pUsername;
        password = pPassword;
        clientId = pClientId;
    }
}
