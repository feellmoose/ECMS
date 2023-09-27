package com.example.demo.common.service;

import com.example.demo.common.entity.Box;
import com.example.demo.common.entity.Cabinet;

import java.util.List;

public interface CabinetService {
    void addCabinet(Cabinet cabinet, List<Box> boxList);

    void delCabinet(Integer cabinetId);

}
