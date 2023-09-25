package com.example.demo.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonUtil {
    public final static ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static <T> String toJson(T obj){
        return objectMapper.writeValueAsString(obj);
    }

    @SneakyThrows
    public static <T> T fromJson(String json,Class<T> tClass){
        return objectMapper.readValue(json,tClass);
    }

}
