package com.example.demo.common.controller.api;

import com.example.demo.common.anno.RoleRequire;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.enums.RoleType;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.common.intercepter.RoleInterceptor;
import com.example.demo.common.model.CabinetModel;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.model.RecordModel;
import com.example.demo.common.model.UserInfo;
import com.example.demo.common.service.CabinetService;
import com.example.demo.common.service.ComponentService;
import com.example.demo.common.service.RecordService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @Title
 * @Description 管理员
 * @Author feelMoose
 * @Date 2023/9/24 23:01
 */

@RoleRequire(role = {RoleType.ADMIN, RoleType.ROOT})
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Resource
    private ComponentService componentService;
    @Resource
    private RecordService recordService;

    @PostMapping("/cabinet/storageInfo")
    public CabinetModel modifyCabinetStorage(Integer action,
                                             Integer cabinetId, Integer boxId,
                                             Integer componentIndex, Integer size) {
        UserInfo userInfo = RoleInterceptor.userHolder.get();
        return switch (action) {
            case 0 ->
                    componentService.takeComponent(userInfo, cabinetId, boxId, ComponentType.getByIndex(componentIndex), size);
            case 1 ->
                    componentService.addComponent(userInfo, cabinetId, boxId, ComponentType.getByIndex(componentIndex), size);
            case 2 ->
                    componentService.modifyComponent(userInfo, cabinetId, boxId, ComponentType.getByIndex(componentIndex), size);
            default -> throw new GlobalRunTimeException(ErrorEnum.PARAM_ERROR, "action invalid");
        };
    }

    @GetMapping("/records")
    public PageModel<RecordModel> getRecord(Integer cabinetId, Integer boxId,
                                            @RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "10") Integer pageSize) {
        return recordService.getRecord(cabinetId, boxId, pageNum, pageSize);
    }

}
