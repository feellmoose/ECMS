package com.example.demo.common.service;

import com.example.demo.common.entity.Record;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.model.RecordModel;
import org.springframework.web.bind.annotation.RequestParam;

public interface RecordService {

    PageModel<Record> getRecord(Integer cabinetId, Integer boxId, Integer pageNum, Integer pageSize);

}
