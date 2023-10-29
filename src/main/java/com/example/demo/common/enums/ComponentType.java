package com.example.demo.common.enums;

import com.example.demo.common.exception.GlobalRunTimeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum ComponentType {
    Empty(0,"empty_box",""),
    XXX(1,"xxx","")
    ;
    private final Integer index;
    private final String description;
    private final String url;
    public static ComponentType getByIndex(Integer index) {
        for (ComponentType componentType:ComponentType.values()){
            if(index.equals(componentType.index)){
                return componentType;
            }
        }
        throw new GlobalRunTimeException(ErrorEnum.PARAM_ERROR,"component index invalid");
    }

    public final static List<Component> componentList = Arrays.stream(ComponentType.values())
            .map(componentType -> new Component(componentType.name(),componentType.index, componentType.description, componentType.url))
            .toList();
    public final static Map<Integer,Component> Index2componentMap = componentList.stream()
            .collect(Collectors.toMap(Component::getIndex,component -> component));


    @AllArgsConstructor
    @Data
    public static class Component{
        private final String name;
        private final Integer index;
        private final String description;
        private final String url;
    }
}
