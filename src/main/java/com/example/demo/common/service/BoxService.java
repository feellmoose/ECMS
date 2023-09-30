package com.example.demo.common.service;

import com.example.demo.common.entity.Box;
import com.example.demo.common.model.BoxStorageModel;
import com.example.demo.common.model.PageModel;

import java.util.List;
import java.util.Map;


public interface BoxService {
    PageModel<Box> getBoxes(Integer cabinetId, Integer pageNum, Integer pageSize);

    void addBoxForCabinet(Integer cabinetId, Integer boxId, Integer actionType);

    void addBranchBoxForCabinet(Integer cabinetId, List<Integer> boxId);

    void modifyBoxForCabinet(Integer id, Integer cabinetId, Integer boxId, Integer actionType);

    void delBox(Integer id);
    void delBoxForCabinet(Integer cabinetId);

    List<BoxStorageModel> selectBoxStorageModels(Integer cabinetId);

    Integer countBoxes(Integer cabinet);
}