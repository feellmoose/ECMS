package com.example.demo.common.controller.api;

import com.example.demo.common.enums.ComponentType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ComponentController {

    @GetMapping("/component")
    List<ComponentType.Component> getValidComponent(){
        return ComponentType.componentList;
    }

}
