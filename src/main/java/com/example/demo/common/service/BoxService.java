package com.example.demo.common.service;

import com.example.demo.common.entity.Box;
import com.example.demo.common.model.PageModel;


public interface BoxService {
    PageModel<Box> getBoxes(Integer cabinetId, Integer pageNum, Integer pageSize);

    void addBoxForCabinet(Integer cabinetId, Integer boxId, Integer actionType);

    void modifyBoxForCabinet(Integer id, Integer cabinetId, Integer boxId, Integer actionType);

    void delBoxForCabinet(Integer id);
}