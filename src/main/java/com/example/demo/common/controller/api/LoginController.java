package com.example.demo.common.controller.api;

import com.example.demo.common.model.LoginInfoModel;
import com.example.demo.common.model.UserInfo;
import com.example.demo.common.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Title
 * @Description 登录鉴权
 * @Author feelMoose
 * @Date 2023/9/24 23:01
 */

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Resource
    private LoginService loginService;


    @PostMapping("")
    public LoginInfoModel login(String code){
        UserInfo userInfo = loginService.login(code);
        return LoginInfoModel.getByUserInfo(userInfo);
    }

    @PostMapping("/register")
    public LoginInfoModel register(String username,String logId, String code){
        UserInfo userInfo = loginService.register(username, logId, code);
        return LoginInfoModel.getByUserInfo(userInfo);
    }

}
