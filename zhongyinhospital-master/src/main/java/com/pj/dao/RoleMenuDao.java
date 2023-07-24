package com.pj.dao;

import com.pj.entity.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 角色和菜单关联表(RoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-05 13:49:20
 */
public interface RoleMenuDao {
    @Select("select rm.* from role_menu rm join menu m on rm.menu_id=m.menu_id where rm.role_id=#{roleId} and m.parent_id!=0")
    List<RoleMenu> setMenuIdMenus(Integer roleId);

    int insertBatch(@Param("roleId") Integer roleId,@Param("menuIds") List<Integer> menuIds);

    @Delete("delete from role_menu where role_id=#{roleId}")
    int del(Integer roleId);
}

