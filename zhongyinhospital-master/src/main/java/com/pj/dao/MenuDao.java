package com.pj.dao;

import com.pj.dto.MenuDto;
import com.pj.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-05 13:49:22
 */
public interface MenuDao {

    @Select("select * from menu where menu_type=#{menuType}")
    List<MenuDto> seelectbytyoe(String menuType);

    @Select("select * from menu where menu_name=#{name}")
    Menu seelectbyname(String name);
    //查询所有菜单
     @Select("select * from menu")
    List<Menu> all();
     //菜单管理模糊查询
    List<Menu> all1(@Param("menuType") String menuType,@Param("menuName")String menuName);

    int insert(Menu menu);

    int update(Menu menu);

    int deleteById(int menuId);


}

