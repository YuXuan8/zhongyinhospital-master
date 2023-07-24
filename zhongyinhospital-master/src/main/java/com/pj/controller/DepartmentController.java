package com.pj.controller;

import com.github.pagehelper.PageInfo;
import com.pj.dto.getAllDrug1;
import com.pj.entity.Department;
import com.pj.entity.Dite;
import com.pj.service.DepartmentService;
import com.pj.service.DiteService;
import com.pj.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 科室表(Department)表控制层
 *
 * @author makejava
 * @since 2023-07-11 18:21:04
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    /**
     * 服务对象
     */
    @Resource
    private DepartmentService departmentService;
    @Resource
    private DiteService diteService;

    /**
     * 查询所有科室添加到下拉列表
     * @return
     */
    @RequestMapping("/getDepartmente")
    public Result getDepartmente(){
        Result result=new Result();
        List<Department> all = departmentService.all();
        result.setData(all);
        return result;
    }

    /**
     * 科室管理分页
     * @param getAllDrug1
     * @return
     */
    @GetMapping("/page")
    public Result page(getAllDrug1 getAllDrug1){
        Result result=new Result();
        PageInfo<Department> page = departmentService.page(getAllDrug1.getPage(), getAllDrug1.getLimit(), getAllDrug1.getRole());
        result.setData(page);
        result.setTotal(page.getTotal());
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;
    }

    /**
     * 修改科室信息
     * @param department
     * @return
     */
    @PutMapping
    public Result upd(@RequestBody Department department){
        Result result=new Result();
        String typeName = department.getTypeName();
        Dite byname = diteService.byname(typeName);
        department.setType(byname.getId());
        int update = departmentService.update(department);
        if (update>0){
            result.setMessage("修改成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
        }else {
            result.setMessage("修改失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return  result;
    }

    /**
     * 删除科室id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result upd(@PathVariable("id")int id){
      Result result=new Result();
        int i = departmentService.deleteById(id);
        if (i>0){
            result.setMessage("删除成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
        }else {
            result.setMessage("删除失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return  result;
    }

    /**
     * 新增科室
     * @param department
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Department department){
        Result add = departmentService.add(department);
        return add;
    }


}

