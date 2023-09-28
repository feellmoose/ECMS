package com.example.demo.common.controller.api;

import com.example.demo.common.anno.RoleRequire;
import com.example.demo.common.entity.Box;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.enums.RoleType;
import com.example.demo.common.model.PageModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description 修改元件存储信息
 * @Author feelMoose
 * @Date 2023/9/24 23:01
 */

@RoleRequire(role = {RoleType.ADMIN,RoleType.ROOT})
@RestController
@RequestMapping("/api/cabinet")
public class CabinetController {

    @GetMapping("")
    public PageModel<Cabinet> getCabinets(Integer pageNum, Integer pageSize){
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
    @GetMapping("")
    public PageModel<Box> getBoxes(Integer cabinetId, Integer pageNum, Integer pageSize){
        //与盒子一起创建完毕
        //与盒子自动编号
        return null;
    }

    public Box addBoxForCabinet(Integer cabinetId,Integer boxId){
        return null;
    }

    public void modifyBoxForCabinet(Integer id ,Integer cabinetId,Integer boxId){

    }

    public void delBoxForCabinet(Integer id){

    }

}
