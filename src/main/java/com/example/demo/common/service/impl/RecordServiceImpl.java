package com.example.demo.common.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.entity.Record;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.common.mapper.BoxMapper;
import com.example.demo.common.mapper.CabinetMapper;
import com.example.demo.common.mapper.RecordMapper;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.model.RecordModel;
import com.example.demo.common.service.BoxService;
import com.example.demo.common.service.RecordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {
    @Resource
    private RecordMapper recordMapper;
    @Resource
    private BoxService boxService;

    @Override
    public PageModel<RecordModel> getRecord(Integer cabinetId, Integer boxId, Integer pageNum, Integer pageSize) {
        Integer globalId = Optional.ofNullable(boxService.getBox(cabinetId, boxId))
                .orElseThrow(()->new GlobalRunTimeException(ErrorEnum.COMMON_ERROR,"no such box"))
                .getId();
        IPage<Record> recordPage = recordMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.lambdaQuery(Record.class)
                .eq(Record::getBoxGlobalId,globalId));
        List<RecordModel> recordModels = recordPage.getRecords().stream()
                .map(record -> new RecordModel(record, ComponentType.getByIndex(record.getComponentIndex())))
                .toList();
        return new PageModel<>(recordModels,pageNum,pageSize,(int) recordPage.getTotal());
    }

}
