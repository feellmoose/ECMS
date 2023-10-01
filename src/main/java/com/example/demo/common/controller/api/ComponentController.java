package com.example.demo.common.controller.api;

import com.example.demo.common.enums.ComponentType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ComponentController {
    private static final List<ComponentType> componentList = Arrays.stream(ComponentType.values()).toList();

    @GetMapping("/component")
    List<ComponentType> getValidComponent(){
        return componentList;
    }

}
