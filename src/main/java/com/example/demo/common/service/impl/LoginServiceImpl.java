package com.example.demo.common.service.impl;

import com.example.demo.common.model.UserInfo;
import com.example.demo.common.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public UserInfo login(String code) {
        return null;
    }

    @Override
    public UserInfo register(String username, String logId, String code) {
        return null;
    }
}
