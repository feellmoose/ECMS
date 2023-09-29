package com.example.demo.common.controller.api;

import com.example.demo.common.anno.RoleRequire;
import com.example.demo.common.entity.Box;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.enums.RoleType;
import com.example.demo.common.model.PageModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("")
    public PageModel<Cabinet> getCabinets(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10")Integer pageSize){
        //与盒子一起创建完毕
        //与盒子自动编号
        return null;
    }

    @PostMapping("/add")
    public Cabinet addCabinet(String location, String description, Integer boxSize){
        //与盒子一起创建完毕
        //与盒子自动编号
        return null;
    }

    @PostMapping("/update")
    public void modifyCabinet(Integer id ,String location,String description){

    }

    @PostMapping("/del")
    public void delCabinet(Integer id){
        //与盒子一起删除
    }
    @GetMapping("/box")
    public PageModel<Box> getBoxes(Integer cabinetId,
                                   @RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10")Integer pageSize){
        //与盒子一起创建完毕
        //与盒子自动编号
        return null;
    }
    @PostMapping("/box/add")
    public Box addBoxForCabinet(Integer cabinetId,Integer boxId,Integer actionType){
        return null;
    }
    @PostMapping("/box/update")
    public void modifyBoxForCabinet(Integer id ,Integer cabinetId,Integer boxId,Integer actionType){

    }
    @PostMapping("/box/del")
    public void delBoxForCabinet(Integer id){

    }

}
