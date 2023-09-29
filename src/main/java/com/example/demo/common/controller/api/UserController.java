package com.example.demo.common.controller.api;

import com.example.demo.common.anno.RoleRequire;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.entity.Record;
import com.example.demo.common.enums.RoleType;
import com.example.demo.common.intercepter.RoleInterceptor;
import com.example.demo.common.model.CabinetModel;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.model.RecordModel;
import com.example.demo.common.model.UserInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title
 * @Description 拿取元件
 * @Author feelMoose
 * @Date 2023/9/24 23:01
 */

@RoleRequire(role = {RoleType.ADMIN, RoleType.USER, RoleType.ROOT})
@RestController
@RequestMapping("/api/user")
public class UserController {
    //TODO 查看存储柜信息
    @GetMapping("/cabinets")
    public PageModel<Cabinet> getCabinets(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10")Integer pageSize) {
        return null;
    }

    //TODO 查看存储信息
    @GetMapping("/cabinet/storageInfo")
    public CabinetModel getCabinetStorage(Integer cabinetId) {
        return null;
    }

    //TODO 配合admin拿组件
    @GetMapping("/openBox")
    public String openBox(Integer cabinetId,Integer boxId) {
        UserInfo userInfo = RoleInterceptor.userHolder.get();
        return null;
    }

}
