package com.pj.service.impl;

import com.pj.dao.UserRoleDao;
import com.pj.entity.UserRole;
import com.pj.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 用户角色表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2023-07-05 13:49:20
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<UserRole> setRoleIdRoles(Integer uid) {
        List<UserRole> userRoles = userRoleDao.setRoleIdRoles(uid);
        return userRoles;
    }

    /**
     * 新增角色权限信息
     * @param userRole
     * @return
     */
    @Override
    public int insert(UserRole userRole) {
        int insert = userRoleDao.insert(userRole);
        return insert;
    }
}
