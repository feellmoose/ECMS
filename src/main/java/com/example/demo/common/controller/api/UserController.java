package com.example.demo.common.controller.api;

import com.example.demo.common.anno.RoleRequire;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.enums.RoleType;
import com.example.demo.common.intercepter.RoleInterceptor;
import com.example.demo.common.model.CabinetModel;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.model.UserInfo;
import com.example.demo.common.service.CabinetService;
import com.example.demo.common.service.ComponentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    private CabinetService cabinetService;
    @Resource
    private ComponentService componentService;

    @GetMapping("/cabinets")
    public PageModel<Cabinet> getCabinets(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10") Integer pageSize) {
        return cabinetService.getCabinets(pageNum, pageSize);
    }

    @GetMapping("/cabinet/storageInfo")
    public CabinetModel getCabinetStorage(Integer cabinetId) {
        return cabinetService.getCabinetStorage(cabinetId);
    }

    @PostMapping("/openBox")
    public String openBox(Integer cabinetId, Integer boxId,
                          @RequestParam(required = false, defaultValue = "0") Integer size,
                          String remark) {
        UserInfo userInfo = RoleInterceptor.userHolder.get();
        return componentService.getComponent(userInfo, cabinetId, boxId, remark, size);
    }

}
