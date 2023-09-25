package com.example.demo.common.model;

import com.example.demo.common.entity.User;
import com.example.demo.common.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private User user;//id & logId
    private List<RoleType> roles;
}
