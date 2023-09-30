package com.example.demo.mqtt.model.data;

import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.exception.GlobalRunTimeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenMessageData implements MessageData{
    private Integer userId;//谁
    private String username;
    private Integer cabinetId;//在哪个柜子（目前都是0）
    private Integer boxId;//第几个箱子
    private String detail;

    public OpenMessageData(Integer userId, String username, Integer cabinetId, Integer boxId, ComponentType componentType, Integer num,Integer type) {
        this.userId = userId;
        this.username = username;
        this.cabinetId = cabinetId;
        this.boxId = boxId;
        this.detail = switch (type){
            case 0 -> "取" + componentType.getDescription() + "元件" + num + "个";
            case 1 -> "放" + componentType.getDescription() + "元件" + num + "个";
            default -> throw new GlobalRunTimeException(ErrorEnum.COMMON_ERROR,"error type to record");
        };
    }
}
