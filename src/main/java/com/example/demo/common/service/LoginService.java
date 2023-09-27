package com.example.demo.common.service;

import com.example.demo.common.model.UserInfo;

public interface LoginService {
    UserInfo login(String code);
    UserInfo register(String username,String logId);
}
