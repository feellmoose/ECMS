package com.example.demo.common.controller.api;

import com.example.demo.common.anno.RoleRequire;
import com.example.demo.common.entity.Box;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.entity.User;
import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.enums.RoleType;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.common.intercepter.RoleInterceptor;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.model.UserInfo;
import com.example.demo.common.service.BoxService;
import com.example.demo.common.service.CabinetService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @Title
 * @Description 修改元件存储信息
 * @Author feelMoose
 * @Date 2023/9/24 23:01
 */

@RoleRequire(role = {RoleType.ROOT})
@RestController
@RequestMapping("/api/cabinet")
public class CabinetController {

    @Resource
    private CabinetService cabinetService;
    @Resource
    private BoxService boxService;

    @GetMapping("")
    public PageModel<Cabinet> getCabinets(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10") Integer pageSize) {
        return cabinetService.getCabinets(pageNum, pageSize);
    }

    @PostMapping("/add")
    public String addCabinet(String location, String description, Integer boxSize, @RequestParam(required = false) Integer actionType) {
        UserInfo userInfo = RoleInterceptor.userHolder.get();
        cabinetService.addCabinet(userInfo,location, description, boxSize,actionType);
        return "ok";
    }

    @PostMapping("/update")
    public String modifyCabinet(Integer id, String location, String description, Integer boxSize) {
        cabinetService.modifyCabinet(id, location, description, boxSize);
        return "ok";
    }

    @PostMapping("/del")
    public String delCabinet(Integer id) {
        cabinetService.delCabinet(id);
        return "ok";
    }

    @GetMapping("/box")
    public PageModel<Box> getBoxes(Integer cabinetId,
                                   @RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        if(cabinetId == null){
            throw new GlobalRunTimeException(ErrorEnum.PARAM_ERROR,"cabinet Id should not be empty");
        }
        return boxService.getBoxes(cabinetId, pageNum, pageSize);
    }

    @PostMapping("/box/add")
    public String addBoxForCabinet(Integer cabinetId,
                                   Integer boxId,
                                   @RequestParam(required = false) Integer actionType) {
        UserInfo userInfo = RoleInterceptor.userHolder.get();
        boxService.addBoxForCabinet(userInfo, cabinetId, boxId, actionType);
        return "ok";
    }

    @PostMapping("/box/update")
    public String modifyBoxForCabinet(Integer id, Integer cabinetId, Integer boxId, @RequestParam(required = false) Integer actionType) {
        boxService.modifyBoxForCabinet(id, cabinetId, boxId, actionType);
        return "ok";
    }

    @PostMapping("/box/del")
    public String delBox(Integer id) {
        boxService.delBox(id);
        return "ok";
    }

}
