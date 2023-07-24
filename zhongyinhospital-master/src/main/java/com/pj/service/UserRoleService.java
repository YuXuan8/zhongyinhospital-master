package com.pj.service;

import com.pj.entity.UserRole;

import java.util.List;

/**
 * 用户角色表(UserRole)表服务接口
 *
 * @author makejava
 * @since 2023-07-05 13:49:20
 */
public interface UserRoleService {

    List<UserRole> setRoleIdRoles(Integer uid);

    int insert(UserRole userRole);

}
