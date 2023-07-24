package com.pj.dao;

import com.pj.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-05 13:49:21
 */
public interface RoleDao {

    @Select("select * from role where status!='2' and remark like concat('%',#{remark},'%') ")
    List<Role> mohu(String remark);
    //注册查询所有角色权限
    @Select("select * from role")
    List<Role> findAll();
    //根据角色名称插叙id
    @Select("select * from role where remark=#{remark}")
    Role selectByRole(String remark);

    int insert(Role role);


    int deleteById(int id);

    int update(Role role);


   Role queryById(int id);



}

