package com.pj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pj.dao.DepartmentDao;
import com.pj.dao.DiteDao;
import com.pj.entity.Department;
import com.pj.entity.Dite;
import com.pj.service.DepartmentService;
import com.pj.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 科室表(Department)表服务实现类
 *
 * @author makejava
 * @since 2023-07-11 18:21:04
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentDao departmentDao;
    @Resource
    private DiteDao diteDao;

    /**
     * 科室管理分页
     * @param page
     * @param limit
     * @param name
     * @return
     */
    @Override
    public PageInfo<Department> page(int page, int limit, String name) {
        if (name==null||name==""){
            name="%";
        }
        PageHelper.startPage(page,limit);
        List<Department> departments = this.selectBydepartmentname(name);
        for (Department department : departments) {
            Integer type = department.getType();
            Dite dite = diteDao.queryById(type);
            department.setTypeName(dite.getName());
        }
        PageInfo<Department> page1=new PageInfo<>(departments);
        return page1;
    }
    /**
     * 根据名称模糊查询
     * @param name
     * @return
     */
    @Override
    public List<Department> selectBydepartmentname(String name) {
        List<Department> departments = departmentDao.selectBydepartmentname(name);
        return departments;
    }

    /**
     * 查询所有科室
     * @return
     */
    @Override
    public List<Department> all() {
        List<Department> all = departmentDao.all();
        return all;
    }

    /***
     * 门珍科室展示
     * @param id
     * @return
     */
    @Override
    public Department queryById(int id) {
        Department department = departmentDao.queryById(id);
        return department;
    }

    /**
     * 修改
     * @param department
     * @return
     */
    @Override
    public int update(Department department) {
        int update = departmentDao.update(department);
        return update;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(int id) {
        int i = departmentDao.deleteById(id);
        return i;
    }

    /**
     * 新增科室的同时新增字典
     * @param department
     * @return
     */
    @Override
    @Transactional
    public Result add(Department department) {
        Department department1 = departmentDao.searchAllByName(department.getName());
        Result result=new Result();
        if (department1!=null){
            result.setMessage("该科室已存在");
            result.setStatus(Result.RESPONSE_FAIL);
            return result;
        }else {
            Dite dite = new Dite();
            dite.setTypeno("department_type");
            dite.setName(department.getName());
            dite.setFlog(1);
            dite.setHierarchy(5);
            dite.setRemark("科室管理");
            diteDao.insert(dite);
            department.setType(dite.getId());
            department.setCreateDatetime(new Date());
            int insert = departmentDao.insert(department);
            result.setMessage("新增成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
            return result;
        }



    }
}
