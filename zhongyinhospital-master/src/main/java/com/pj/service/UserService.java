package com.pj.service;

import com.github.pagehelper.PageInfo;
import com.pj.dto.PageUser;
import com.pj.dto.UserDto;
import com.pj.entity.User;
import com.pj.util.MenuDto;
import com.pj.util.Result;

import java.util.List;

/**
 * 医护人员信息表(User)表服务接口
 *
 * @author makejava
 * @since 2023-07-03 15:14:10
 */

public interface UserService {

    int save(User user);
    //管理员管理
    PageInfo<User> page(PageUser pageUser);
    //根据邮箱查询用户信息
    User selectByEmail(String email);
    //查询用户的角色
    List<MenuDto> role(String email);
    //注册
    int insert(User user,int id,String description);
    //注册时修改用户信息
    int update(User user);
    //根据科室查找医生
    List<UserDto> departmentId(int departmentId, int departmentType);
    Result upd(User user);

    Result del(int id);

}
