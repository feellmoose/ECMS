package com.example.demo.common.controller.api;

import com.example.demo.common.anno.RoleRequire;
import com.example.demo.common.enums.RoleType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title
 * @Description 修改元件存储信息
 * @Author feelMoose
 * @Date 2023/9/24 23:01
 */

@RoleRequire(role = {RoleType.ADMIN,RoleType.ROOT})
@RestController
@RequestMapping("/api/storage")
public class StorageController {
    //TODO 主要实现功能:1.修改存储库信息







}
