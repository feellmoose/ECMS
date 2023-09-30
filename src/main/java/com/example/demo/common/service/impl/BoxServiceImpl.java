package com.example.demo.common.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.entity.Box;
import com.example.demo.common.mapper.BoxMapper;
import com.example.demo.common.model.BoxStorageModel;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.service.BoxService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxServiceImpl implements BoxService {
    private static final int DEFAULT_ACTION_TYPE = 1;
    @Resource
    private BoxMapper boxMapper;

    @Override
    public PageModel<Box> getBoxes(Integer cabinetId, Integer pageNum, Integer pageSize) {
        IPage<Box> boxPage = boxMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.lambdaQuery(Box.class)
                .eq(Box::getCabinetId, cabinetId));
        return new PageModel<>(boxPage.getRecords(), pageNum, pageSize, (int) boxPage.getTotal());
    }
//todo
    @Override
    public void addBoxForCabinet(Integer cabinetId, Integer boxId, Integer actionType) {
        //初始化记录
    }

    @Override
    public void addBranchBoxForCabinet(Integer cabinetId, List<Integer> boxId) {
        //初始化记录
    }


    @Override
    public void modifyBoxForCabinet(Integer id, Integer cabinetId, Integer boxId, Integer actionType) {

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
}
