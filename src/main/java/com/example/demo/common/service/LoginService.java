package com.example.demo.common.service;

import com.example.demo.common.model.LoginInfoModel;
import com.example.demo.common.model.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface LoginService {
    UserInfo login(String unionId, String password, Integer type);
    UserInfo register(String unionId, String password, String name, String email);
}
