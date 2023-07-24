package com.pj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pj.dao.RoleDao;
import com.pj.dao.RoleMenuDao;
import com.pj.dto.getAllDrug1;
import com.pj.entity.Role;
import com.pj.service.RoleService;
import com.pj.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 角色表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-07-05 13:49:21
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Resource
    private RoleMenuDao roleMenuDao;

    /**
     * 角色管理分页及模糊查询
     * @param getAllDrug1
     * @return
     */
    @Override
    public PageInfo<Role> page(getAllDrug1 getAllDrug1) {
        if (getAllDrug1.getRole()==""||getAllDrug1.getRole()==null){
            getAllDrug1.setRole("%");
        }
        PageHelper.startPage(getAllDrug1.getPage(),getAllDrug1.getLimit());
        List<Role> mohu = roleDao.mohu(getAllDrug1.getRole());
        PageInfo<Role> pageInfo=new PageInfo<>(mohu);

        return pageInfo;
    }

    /**
     * 注册页面的角色列表
     * @return
     */
    @Override
    public List<Role> findAll() {
        List<Role> findAll = roleDao.findAll();
        return findAll;
    }

    /**
     * 根据角色名查询id
     * @param role
     * @return
     */
    @Override
    public Role selectByRole(String role) {
        Role role1 = roleDao.selectByRole(role);
        return role1;
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @Override
    @Transactional
    public int asve(Role role) {
        role.setCreateDatetime(new Date());
        role.setDelFlag("0");
        int insert = roleDao.insert(role);
        int i = roleMenuDao.insertBatch(role.getId(), role.getMenuIds());
        return insert;
    }

    /**
     * 角色管理删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(int id) {
        Role role = roleDao.queryById(id);
        role.setStatus("2");
        int i = roleDao.update(role);
        return i;
    }

    /**
     * 角色管理修改
     * @param role
     * @return
     */
    @Override
    @Transactional
    public Result upd(Role role) {
        Result result=new Result();
        int update = roleDao.update(role);
        int del = roleMenuDao.del(role.getId());
        System.out.println(del);
        System.out.println(role.getMenuIds());
        int i = roleMenuDao.insertBatch(role.getId(), role.getMenuIds());
        System.out.println(i);
        if (update>0){
            result.setMessage("修改成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
        }else {
            result.setMessage("修改失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }
}
