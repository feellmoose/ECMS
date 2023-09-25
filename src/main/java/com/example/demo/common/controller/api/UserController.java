package com.example.demo.common.controller.api;

import com.example.demo.common.anno.RoleRequire;
import com.example.demo.common.enums.RoleType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title
 * @Description 拿取元件
 * @Author feelMoose
 * @Date 2023/9/24 23:01
 */

@RoleRequire(role = {RoleType.USER,RoleType.ROOT})
@RestController
@RequestMapping("/api/user")
public class UserController {
    //TODO 主要实现功能:1.查看存储柜信息 存储信息 2. 3.配合admin拿组件
}
