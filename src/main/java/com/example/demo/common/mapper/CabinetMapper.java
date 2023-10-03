package com.example.demo.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.common.entity.Cabinet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CabinetMapper extends BaseMapper<Cabinet> {
    void addBox(Integer cabinetId);
    void delBox(Integer cabinetId);
    void delAllBox(Integer cabinetId);

}
