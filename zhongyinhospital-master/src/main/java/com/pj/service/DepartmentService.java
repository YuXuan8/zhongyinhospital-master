package com.pj.service;

import com.github.pagehelper.PageInfo;
import com.pj.entity.Department;
import com.pj.util.Result;

import java.util.List;

/**
 * 科室表(Department)表服务接口
 *
 * @author makejava
 * @since 2023-07-11 18:21:04
 */
public interface DepartmentService {
    PageInfo<Department>  page(int page,int limit,String name);
    //根据姓名模糊查询
    List<Department> selectBydepartmentname(String name);
    //查询所有科室
    List<Department> all();
    //门珍科室
    Department queryById(int id);

    int update(Department department);

    int deleteById(int id);

    Result add(Department department);
}
