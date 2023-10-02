package com.example.demo.common.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    public void setValue() {
        broker = pBroker;
        username = pUsername;
        password = pPassword;
        clientId = pClientId;
    }
}
