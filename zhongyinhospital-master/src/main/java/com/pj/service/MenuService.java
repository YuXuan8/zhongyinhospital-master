package com.pj.service;

import com.github.pagehelper.PageInfo;
import com.pj.dto.MenuDto;
import com.pj.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-07-05 13:49:22
 */
public interface MenuService {
    //查询所有父菜单
    List<MenuDto> seelectbytyoe(String menuType);
    Menu seelectbyname(String name);

    List<Menu> all();

    //菜单管理分页及模糊查询
    PageInfo<Menu> page(MenuDto menuDto);

    int insert(Menu menu);

    int update(Menu menu);

    int deleteById(int menuId);
}
