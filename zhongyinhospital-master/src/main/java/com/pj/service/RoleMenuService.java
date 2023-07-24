package com.pj.service;

import com.pj.entity.RoleMenu;

import java.util.List;

/**
 * 角色和菜单关联表(RoleMenu)表服务接口
 *
 * @author makejava
 * @since 2023-07-05 13:49:20
 */
public interface RoleMenuService {
    List<RoleMenu> setMenuIdMenus(Integer roleId);


}
