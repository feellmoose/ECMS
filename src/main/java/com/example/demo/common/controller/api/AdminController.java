package com.example.demo.common.controller.api;

import com.example.demo.common.anno.RoleRequire;
import com.example.demo.common.entity.Record;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.enums.RoleType;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.common.intercepter.RoleInterceptor;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.model.UserInfo;
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
    public String modifyCabinetStorage(Integer cabinetId, Integer boxId, Integer componentIndex,
                                       @RequestParam(required = false, defaultValue = "0") Integer size,
                                       @RequestParam(required = false, defaultValue = "") String remark,
                                       @RequestParam(required = false, defaultValue = "true") Boolean open) {
        UserInfo userInfo = RoleInterceptor.userHolder.get();
        if (componentIndex == null) {
            throw new GlobalRunTimeException(ErrorEnum.PARAM_ERROR, "invalid component");
        }
        if (!size.equals(0) && componentIndex.equals(ComponentType.Empty.getIndex())) {
            throw new GlobalRunTimeException(ErrorEnum.PARAM_ERROR);
        }
        if (open) {
            return componentService.modifyComponent(userInfo, cabinetId, boxId, ComponentType.getByIndex(componentIndex), remark, size);
        } else {
            return componentService.modifyWithOutOpen(userInfo, cabinetId, boxId, ComponentType.getByIndex(componentIndex), remark, size);
        }
    }

    @GetMapping("/records")
    public PageModel<Record> getRecord(Integer cabinetId, Integer boxId,
                                       @RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return recordService.getRecord(cabinetId, boxId, pageNum, pageSize);
    }

}
