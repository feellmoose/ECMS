package com.example.demo.common.mapper.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.common.entity.Cabinet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CabinetMapper extends BaseMapper<Cabinet> {
}
