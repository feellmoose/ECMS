package com.example.demo;

import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.utils.JsonUtil;
import com.example.demo.mqtt.enums.ActionType;
import com.example.demo.mqtt.model.MqttMessageDetail;
import com.example.demo.mqtt.model.data.ReplyMessageData;
import com.example.demo.mqtt.model.data.RevokeMessageData;
import com.example.demo.mqtt.model.data.OpenMessageData;
import com.example.demo.mqtt.service.MqttService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Resource
    private MqttService mqttService;

    @Test
    void contextLoads() {
        MqttMessageDetail mqttMessageDetail = new MqttMessageDetail(new OpenMessageData(112354123,"x某某",1,5, ComponentType.XXX,10),ActionType.open);
        MqttMessageDetail r = new MqttMessageDetail(new RevokeMessageData(mqttMessageDetail.getMessageId()),ActionType.revoke);
        MqttMessageDetail o = new MqttMessageDetail(new ReplyMessageData(0,"开启成功", mqttMessageDetail.getMessageId()),ActionType.reply);
        System.out.println(JsonUtil.toJson(mqttMessageDetail));
        System.out.println(JsonUtil.toJson(r));
        System.out.println(JsonUtil.toJson(o));
    }

}
