package com.pj.service.impl;

import com.pj.dao.RoleMenuDao;
import com.pj.entity.RoleMenu;
import com.pj.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2023-07-05 13:49:20
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Autowired
    private RoleMenuDao roleMenuDao;


    @Override
    public List<RoleMenu> setMenuIdMenus(Integer roleId) {
        List<RoleMenu> roleMenus = roleMenuDao.setMenuIdMenus(roleId);
        return roleMenus;
    }
}
