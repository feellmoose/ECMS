package com.example.demo.common.model;

import com.example.demo.common.utils.JWTUtil;
import com.example.demo.common.utils.JsonUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfoModel {
    @JsonInclude(NON_NULL)
    private String token;
    @JsonInclude(NON_NULL)
    private UserInfo userInfo;//id & logId & roles

    @SneakyThrows
    public static LoginInfoModel getByUserInfo(@NonNull UserInfo userInfo) {
        Map<String,String> payload = new HashMap<>();
        payload.put("userInfo", JsonUtil.toJson(userInfo));
        String token = JWTUtil.generateToken(payload);
        return new LoginInfoModel(token,userInfo);
    }

    
}
