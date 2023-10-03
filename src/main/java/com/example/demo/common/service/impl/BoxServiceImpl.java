package com.example.demo.common.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.entity.Box;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.entity.Record;
import com.example.demo.common.enums.ComponentType;
import com.example.demo.common.enums.RecordState;
import com.example.demo.common.mapper.BoxMapper;
import com.example.demo.common.mapper.CabinetMapper;
import com.example.demo.common.mapper.RecordMapper;
import com.example.demo.common.model.BoxStorageModel;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.model.Storage;
import com.example.demo.common.model.UserInfo;
import com.example.demo.common.service.BoxService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoxServiceImpl implements BoxService {
    private static final int DEFAULT_ACTION_TYPE = 0;
    @Resource
    private BoxMapper boxMapper;
    @Resource
    private RecordMapper recordMapper;
    @Resource
    private CabinetMapper cabinetMapper;

    @Override
    public PageModel<Box> getBoxes(Integer cabinetId, Integer pageNum, Integer pageSize) {
        IPage<Box> boxPage = boxMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.lambdaQuery(Box.class)
                .eq(Box::getCabinetId, cabinetId));
        return new PageModel<>(boxPage.getRecords(), pageNum, pageSize, (int) boxPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBoxForCabinet(UserInfo userInfo, Integer cabinetId, Integer boxId, Integer actionType) {
        actionType = actionType == null ? DEFAULT_ACTION_TYPE : actionType;
        Box box = new Box(null, cabinetId, boxId, actionType);
        boxMapper.insert(box);
        Record record = Record.builder()
                .boxGlobalId(box.getId())
                .componentIndex(ComponentType.Empty.getIndex())
                .state(RecordState.build.getValue())
                .messageId("init-box")
                .remark("init-box")
                .storageSize(0)
                .userId(userInfo.getUser().getId())
                .messageState("init-box")
                .type(2)
                .build();
        cabinetMapper.addBox(cabinetId);
        recordMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBranchBoxForCabinet(UserInfo userInfo, Integer cabinetId, List<Integer> boxId, Integer actionType) {
        int type = actionType == null ? DEFAULT_ACTION_TYPE : actionType;
        List<Box> boxes = boxId.stream()
                .map(id -> new Box(null, cabinetId, id, type))
                .toList();
        boxMapper.insertBranch(boxes);
        List<Integer> boxIds = boxMapper.selectList(Wrappers.lambdaQuery(Box.class)
                        .eq(Box::getCabinetId, cabinetId)).stream()
                .map(Box::getId).toList();
        List<Record> records = boxIds.stream()
                .map(box -> Record.builder()
                        .boxGlobalId(box)
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
        recordMapper.insertBranch(records);
    }

    @Override
    public void modifyBoxForCabinet(Integer id, Integer cabinetId, Integer boxId, Integer actionType) {
        boxMapper.updateById(new Box(id, cabinetId, boxId, actionType));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delBox(Integer id) {
        Box box = boxMapper.selectById(id);
        boxMapper.deleteById(id);
        cabinetMapper.delBox(box.getCabinetId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delBoxForCabinet(Integer cabinetId) {
        boxMapper.delete(Wrappers.lambdaQuery(Box.class)
                .eq(Box::getCabinetId, cabinetId));
        cabinetMapper.delAllBox(cabinetId);
    }

    @Override
    public List<BoxStorageModel> selectBoxStorageModels(Integer cabinetId) {
        List<BoxStorageModel> boxStorageModels = new ArrayList<>();
        List<Box> boxes = boxMapper.selectList(Wrappers.lambdaQuery(Box.class)
                .eq(Box::getCabinetId,cabinetId));
        for (Box box: boxes) {
            Record record = recordMapper.selectOne(Wrappers.lambdaQuery(Record.class)
                    .eq(Record::getBoxGlobalId,box.getBoxId())
                    .orderByDesc(Record::getId)
                    .last("limit 1"));
            Storage storage = new Storage(null,ComponentType.Index2componentMap.get(record.getComponentIndex()),record.getStorageSize());
            boxStorageModels.add(new BoxStorageModel(box,storage));
        }
        return boxStorageModels;
    }

    @Override
    public Integer countBoxes(Integer cabinet) {
        return boxMapper.selectCount(Wrappers.lambdaQuery(Box.class)
                .eq(Box::getCabinetId, cabinet)).intValue();
    }

    @Override
    public Box getBox(Integer cabinetId, Integer boxId) {
        return boxMapper.selectOne(Wrappers.lambdaQuery(Box.class)
                .eq(Box::getCabinetId, cabinetId)
                .eq(Box::getBoxId, boxId));
    }

    @Override
    public Box getBox(Integer globalId) {
        return boxMapper.selectById(globalId);
    }
}
