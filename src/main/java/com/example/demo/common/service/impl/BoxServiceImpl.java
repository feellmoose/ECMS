package com.example.demo.common.service.impl;

import com.example.demo.common.entity.Box;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.service.BoxService;
import org.springframework.stereotype.Service;

@Service
public class BoxServiceImpl implements BoxService {
    @Override
    public PageModel<Box> getBoxes(Integer cabinetId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public void addBoxForCabinet(Integer cabinetId, Integer boxId, Integer actionType) {

    }

    @Override
    public void modifyBoxForCabinet(Integer id, Integer cabinetId, Integer boxId, Integer actionType) {

    }

    @Override
    public void delBoxForCabinet(Integer id) {

    }
}
