package com.example.demo.common.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.entity.Box;
import com.example.demo.common.entity.Record;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.enums.RecordState;
import com.example.demo.common.mapper.BoxMapper;
import com.example.demo.common.mapper.RecordMapper;
import com.example.demo.common.model.BoxStorageModel;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.model.UserInfo;
import com.example.demo.common.service.BoxService;
import com.example.demo.mqtt.enums.ActionType;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class BoxServiceImpl implements BoxService {
    private static final int DEFAULT_ACTION_TYPE = 0;
    @Resource
    private BoxMapper boxMapper;
    @Resource
    private RecordMapper recordMapper;

    @Override
    public PageModel<Box> getBoxes(Integer cabinetId, Integer pageNum, Integer pageSize) {
        IPage<Box> boxPage = boxMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.lambdaQuery(Box.class)
                .eq(Box::getCabinetId, cabinetId));
        return new PageModel<>(boxPage.getRecords(), pageNum, pageSize, (int) boxPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBoxForCabinet(UserInfo userInfo,Integer cabinetId , Integer boxId, Integer actionType) {
        actionType = actionType == null?DEFAULT_ACTION_TYPE:actionType;
        Record record = Record.builder()
                .boxGlobalId(boxId)
                .componentIndex(ComponentType.Empty.getIndex())
                .state(RecordState.build.getValue())
                .messageId("init-box")
                .remark("init-box")
                .storageSize(0)
                .userId(userInfo.getUser().getId())
                .messageState("init-box")
                .type(2)
                .build();
        boxMapper.insert(new Box(null,cabinetId,boxId,actionType));
        recordMapper.insert(record);
    }

    @Override
    public void addBranchBoxForCabinet(UserInfo userInfo,Integer cabinetId, List<Integer> boxId,Integer actionType) {
        int type = actionType == null?DEFAULT_ACTION_TYPE:actionType;
        List<Box> boxes = boxId.stream()
                .map(id -> new Box(null,cabinetId,id,type))
                .toList();
        List<Record> records = boxId.stream()
                        .map(id ->  Record.builder()
                                    .boxGlobalId(id)
                                    .componentIndex(ComponentType.Empty.getIndex())
                                    .state(RecordState.build.getValue())
                                    .messageId("init-box")
                                    .remark("init-box")
                                    .storageSize(0)
                                    .userId(userInfo.getUser().getId())
                                    .messageState("init-box")
                                    .type(2)
                                    .build()
                        ).toList();
        boxMapper.insertBranch(boxes);
        recordMapper.insertBranch(records);
    }

    @Override
    public void modifyBoxForCabinet(Integer id, Integer cabinetId, Integer boxId, Integer actionType) {
        boxMapper.updateById(new Box(id,cabinetId,boxId,actionType));
    }

    @Override
    public void delBox(Integer id) {
        boxMapper.deleteById(id);
    }

    @Override
    public void delBoxForCabinet(Integer cabinetId) {
        boxMapper.delete(Wrappers.lambdaQuery(Box.class)
                .eq(Box::getCabinetId,cabinetId));
    }

    @Override
    public List<BoxStorageModel> selectBoxStorageModels(Integer cabinetId) {
        return boxMapper.selectBoxStorageModels(cabinetId);
    }

    @Override
    public Integer countBoxes(Integer cabinet) {
        return  boxMapper.selectCount(Wrappers.lambdaQuery(Box.class)
                .eq(Box::getCabinetId,cabinet)).intValue();
    }

    @Override
    public Box getBox(Integer cabinetId, Integer boxId) {
        return boxMapper.selectOne(Wrappers.lambdaQuery(Box.class)
                .eq(Box::getCabinetId,cabinetId)
                .eq(Box::getBoxId,boxId));
    }

    @Override
    public Box getBox(Integer globalId) {
        return boxMapper.selectById(globalId);
    }
}
