package com.example.demo.common.controller.api;

import com.example.demo.common.model.LoginInfoModel;
import com.example.demo.common.model.UserInfo;
import com.example.demo.common.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/")
    public LoginInfoModel login(@RequestParam String unionId,
                                @RequestParam String password,
                                @RequestParam Integer type){
        UserInfo userInfo = loginService.login(unionId, password, type);
        return LoginInfoModel.getByUserInfo(userInfo);
    }

    @PostMapping("/register")
    public LoginInfoModel register(@RequestParam String unionId,
                                   @RequestParam String password,
                                   @RequestParam String name,
                                   @RequestParam String email){
        UserInfo userInfo = loginService.register(unionId, password, name, email);
        return LoginInfoModel.getByUserInfo(userInfo);
    }

}
