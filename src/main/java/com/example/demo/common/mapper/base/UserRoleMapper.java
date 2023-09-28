package com.example.demo.common.mapper.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.common.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<Record> {
}
