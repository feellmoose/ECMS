package com.example.demo.common.service.impl;

import com.example.demo.common.model.PageModel;
import com.example.demo.common.model.RecordModel;
import com.example.demo.common.service.RecordService;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {
    @Override
    public PageModel<RecordModel> getRecord(Integer cabinetId, Integer boxId, Integer pageNum, Integer pageSize) {
        return null;
    }
}
