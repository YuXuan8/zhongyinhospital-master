package com.pj.dao;

import com.pj.entity.Department;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 科室表(Department)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-11 18:21:04
 */
public interface DepartmentDao {
    //模糊查询
    @Select("select * from department where name like concat('%',#{name},'%')")
    List<Department> selectBydepartmentname(String name);

    @Select("select * from department")
    List<Department> all();
    //门真科室
     Department queryById(int id);

    /**
     * 修改
     * @param department
     * @return
     */
     int update(Department department);

    /**
     * 删除
     * @param id
     * @return
     */
     int deleteById(int id);

    /**
     * 新增科室
     * @param department
     * @return
     */
     int insert(Department department);
    /**
     * 根据姓名查询科室是否存在
     */
    @Select("select * from department where name=#{name}")
    Department searchAllByName(String name);
}

