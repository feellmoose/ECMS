package com.example.demo.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.common.entity.Box;
import com.example.demo.common.model.BoxStorageModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoxMapper extends BaseMapper<Box> {
    List<BoxStorageModel> selectBoxStorageModels(Integer cabinetId);
}
