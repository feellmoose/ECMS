package com.example.demo.common.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.common.mapper.CabinetMapper;
import com.example.demo.common.model.BoxStorageModel;
import com.example.demo.common.model.CabinetModel;
import com.example.demo.common.model.PageModel;
import com.example.demo.common.model.UserInfo;
import com.example.demo.common.service.BoxService;
import com.example.demo.common.service.CabinetService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CabinetServiceImpl implements CabinetService {
    @Resource
    private CabinetMapper cabinetMapper;
    @Resource
    private BoxService boxService;

    @Override
    public PageModel<Cabinet> getCabinets(Integer pageNum, Integer pageSize) {
        IPage<Cabinet> page = cabinetMapper.selectPage(new Page<>(pageNum, pageSize), null);
        return new PageModel<>(page.getRecords(), pageNum, pageSize, (int) page.getTotal());
    }

    @Override
    public CabinetModel getCabinetStorage(Integer cabinetId) {
        Cabinet cabinet = cabinetMapper.selectById(cabinetId);
        List<BoxStorageModel> boxStorageModels = boxService.selectBoxStorageModels(cabinetId);
        return new CabinetModel(cabinet, boxStorageModels);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCabinet(UserInfo userInfo,String location, String description, Integer boxSize,Integer actionType) {
        Cabinet cabinet = new Cabinet(null, location, description, boxSize);
        cabinetMapper.insert(cabinet);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < boxSize; i++) {
            list.add(i, i + 1);
        }
        boxService.addBranchBoxForCabinet(userInfo,cabinet.getId(), list,actionType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delCabinet(Integer cabinetId) {
        boxService.delBoxForCabinet(cabinetId);
        cabinetMapper.deleteById(cabinetId);
    }

    @Override
    @Transactional
    public void modifyCabinet(Integer id, String location, String description, Integer boxSize) {
        if (boxSize != null) {
            Integer localSize = boxService.countBoxes(id);
            if (localSize > boxSize) {
                throw new GlobalRunTimeException(ErrorEnum.PARAM_ERROR, "box size to small,less size is" + localSize);
            }
        }
        cabinetMapper.updateById(new Cabinet(id, location, description, boxSize));
    }

}
