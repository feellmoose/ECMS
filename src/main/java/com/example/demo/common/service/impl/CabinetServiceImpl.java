package com.example.demo.common.service.impl;

import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.model.CabinetModel;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.service.CabinetService;
import org.springframework.stereotype.Service;

@Service
public class CabinetServiceImpl implements CabinetService {
    @Override
    public PageModel<Cabinet> getCabinets(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public CabinetModel getCabinetStorage(Integer cabinetId) {
        return null;
    }

    @Override
    public void addCabinet(String location, String description, Integer boxSize) {

    }

    @Override
    public void delCabinet(Integer cabinetId) {

    }

    @Override
    public void modifyCabinet(Integer id, String location, String description) {

    }

}
