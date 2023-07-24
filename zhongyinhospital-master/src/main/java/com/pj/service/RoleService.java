package com.pj.service;

import com.github.pagehelper.PageInfo;
import com.pj.dto.getAllDrug1;
import com.pj.entity.Role;
import com.pj.util.Result;

import java.util.List;

/**
 * 角色表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-07-05 13:49:21
 */
public interface RoleService {
    PageInfo<Role> page(getAllDrug1 getAllDrug1);

    List<Role> findAll();

    Role selectByRole(String role);


    int asve(Role role);

    int deleteById(int id);

    Result upd(Role role);


}
