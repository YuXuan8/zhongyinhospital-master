package com.pj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pj.dao.MenuDao;
import com.pj.dto.MenuDto;
import com.pj.entity.Menu;
import com.pj.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-07-05 13:49:22
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<MenuDto> seelectbytyoe(String menuType) {
        List<MenuDto> seelectbytyoe = menuDao.seelectbytyoe(menuType);
        return seelectbytyoe;
    }

    /**
     *查询菜单项是否存在
     * @param name
     * @return
     */
    @Override
    public Menu seelectbyname(String name) {
        Menu seelectbyname = menuDao.seelectbyname(name);
        return seelectbyname;
    }

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<Menu> all() {
        List<Menu> all = menuDao.all();
        return all;
    }
    /**
     * 菜单管理分页和模糊查询
     * @param menuDto
     * @return
     */
    @Override
    public PageInfo<Menu> page(MenuDto menuDto) {
        PageHelper.startPage(menuDto.getPage(),menuDto.getLimit());
        List<Menu> menus = menuDao.all1(menuDto.getMenuType(), menuDto.getMenuName());
        PageInfo<Menu> menuPageInfo= new PageInfo<>(menus);
        return menuPageInfo;
    }

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @Override
    public int insert(Menu menu) {
        int insert = menuDao.insert(menu);
        return insert;
    }

    @Override
    public int update(Menu menu) {
        int update = menuDao.update(menu);
        return update;
    }

    @Override
    public int deleteById(int menuId) {
        int i = menuDao.deleteById(menuId);
        return i;
    }
}
