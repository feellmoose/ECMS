package com.example.demo;

import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.utils.JsonUtil;
import com.example.demo.mqtt.model.ServerMessageModel;
import com.example.demo.mqtt.service.MqttService;
import jakarta.annotation.Resource;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Resource
    private MqttService mqttService;

    @Test
    void contextLoads() {
        MqttMessage message = new MqttMessage();
        message.setQos(1);
        message.setPayload(JsonUtil.toJson(new ServerMessageModel(1000, "root", 1, 1, ComponentType.XXX, 10)).getBytes());
        mqttService.publish("topic/test", message);
        System.out.println(JsonUtil.toJson(new ServerMessageModel(1000, "root", 1, 1, ComponentType.XXX, 10)));
    }

}
