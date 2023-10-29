package com.example.demo.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.common.entity.Record;
import com.example.demo.common.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
