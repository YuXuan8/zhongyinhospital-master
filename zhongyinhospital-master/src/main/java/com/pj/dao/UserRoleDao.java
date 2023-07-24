package com.pj.dao;

import com.pj.entity.UserRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 用户角色表(UserRole)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-05 13:49:19
 */
public interface UserRoleDao {
    int deletbyuid(int uid);
    //新增用户角色权限信息
    int insert(UserRole userRole);

    @Select("select * from user_role where uid=#{uid}")
    List<UserRole> setRoleIdRoles(Integer uid);




}

