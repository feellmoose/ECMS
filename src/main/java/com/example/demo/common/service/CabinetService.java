package com.example.demo.common.service;

import com.example.demo.common.entity.Box;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.model.CabinetModel;
import com.example.demo.common.model.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CabinetService {
    PageModel<Cabinet> getCabinets( Integer pageNum, Integer pageSize);
    CabinetModel getCabinetStorage(Integer cabinetId);
    void addCabinet(String location, String description, Integer boxSize);
    void delCabinet(Integer cabinetId);
    void modifyCabinet(Integer id ,String location,String description);

}
