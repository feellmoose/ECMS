package com.example.demo.common.controller.api;

import com.example.demo.common.anno.RoleRequire;
import com.example.demo.common.enums.RoleType;
import com.example.demo.common.model.CabinetModel;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.model.RecordModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    //TODO 修改库存信息
    @PostMapping("/cabinet/storageInfo")
    public CabinetModel modifyCabinetStorage() {
        //需求：添加库存，减少库存，库存降为0？
        return null;
    }


    //TODO 查看存取信息
    @GetMapping("/records")
    public PageModel<RecordModel> getRecord(Integer cabinetId, Integer boxId,
                                            @RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "10")Integer pageSize) {
        return null;
    }
    //TODO 配合user拿组件
}
