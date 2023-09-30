package com.example.demo.common.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.entity.Record;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.mapper.BoxMapper;
import com.example.demo.common.mapper.CabinetMapper;
import com.example.demo.common.mapper.RecordMapper;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.model.RecordModel;
import com.example.demo.common.service.RecordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Resource
    private RecordMapper recordMapper;

    @Override
    public PageModel<RecordModel> getRecord(Integer cabinetId, Integer boxId, Integer pageNum, Integer pageSize) {
        IPage<Record> recordPage = recordMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.lambdaQuery(Record.class)
                .eq(Record::getBoxId,boxId));
        List<RecordModel> recordModels = recordPage.getRecords().stream()
                .map(record -> new RecordModel(record, ComponentType.getByIndex(record.getComponentIndex())))
                .toList();
        return new PageModel<>(recordModels,pageNum,pageSize,(int) recordPage.getTotal());
    }

}
