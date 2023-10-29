package com.example.demo.common.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.common.entity.User;
import com.example.demo.common.entity.UserRole;
import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.enums.RoleType;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.common.mapper.UserMapper;
import com.example.demo.common.mapper.UserRoleMapper;
import com.example.demo.common.model.UserInfo;
import com.example.demo.common.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;


    @Override
    public UserInfo login(String unionId, String password, Integer type) {
        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class)
                .eq(User::getUnion_id,unionId));
        checkPassword(user,password);
        List<UserRole> userRoles = userRoleMapper.selectList(Wrappers.lambdaQuery(UserRole.class)
                .eq(UserRole::getUserId,user.getId()));
        List<RoleType> roleTypes = userRoles.stream()
                .map(role -> RoleType.getType(role.getRole()))
                .toList();
        return new UserInfo(user,roleTypes);
    }

    @Override
    public UserInfo register(String unionId, String password, String name, String email) {
        User user = new User(null,name,unionId,email,password,null);
        encryptPassword(user);
        userMapper.insert(user);
        userRoleMapper.insert(new UserRole(null, user.getId(), RoleType.USER.getType()));
        return new UserInfo(user,List.of(RoleType.USER));
    }

    void checkPassword(User user,String password){
        if(!user.getPassword().equals(password)){
            throw new GlobalRunTimeException(ErrorEnum.COMMON_ERROR,"error password");
        }
    }

    void encryptPassword(User user){

    }


}
