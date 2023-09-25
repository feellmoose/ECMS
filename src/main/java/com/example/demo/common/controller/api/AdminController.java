package com.example.demo.common.controller.api;

import com.example.demo.common.anno.RoleRequire;
import com.example.demo.common.enums.RoleType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title
 * @Description 管理员
 * @Author feelMoose
 * @Date 2023/9/24 23:01
 */

@RoleRequire(role = {RoleType.ADMIN,RoleType.ROOT})
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    //TODO 主要实现功能:1.修改库存信息 2.查看存取信息 3.配合user拿组件
}