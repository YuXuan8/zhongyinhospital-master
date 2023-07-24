package com.pj.controller;

import com.github.pagehelper.PageInfo;
import com.pj.dto.getAllDrug1;
import com.pj.entity.Role;
import com.pj.service.RoleService;
import com.pj.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 角色表(Role)表控制层
 *
 * @author makejava
 * @since 2023-07-05 13:49:21
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    /**
     * 服务对象
     */
    @Autowired
    private RoleService roleService;

    /**
     * 注册页面查询所有角色
     *
     * @return
     */
    @GetMapping("/getRole")
    public Result roles() {
        Result result = new Result();
//        查询所有角色
        List<Role> all = roleService.findAll();
        result.setData(all);
        return result;
    }

    @GetMapping("/getRole1")
    public Result roles1() {
        Result result = new Result();
//        查询所有角色
        List<Role> all = roleService.findAll();
        List<HashMap<String, Object>> result1 = new ArrayList<>();
        for (Role role : all) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", role.getId());
            map.put("title", role.getRemark());
            result1.add(map);

        }
        result.setData(result1);
        return result;
    }

    /**
     * 角色管理及模糊查询
     *
     * @param getAllDrug1
     * @return
     */
    @GetMapping("/page")
    public Result page(getAllDrug1 getAllDrug1) {
        PageInfo<Role> page = roleService.page(getAllDrug1);
        Result result = new Result();
        result.setStatus(Result.RESPONSE_SUCCESS);
        result.setData(page);
        result.setTotal(page.getTotal());
        return result;
    }

    @PostMapping
    public Result save(@RequestBody Role role) {
        Result result = new Result();
        int asve = roleService.asve(role);
        if (asve > 0) {
            result.setMessage("新增成功");
            result.setStatus(Result.RESPONSE_SUCCESS);
        } else {
            result.setMessage("新增失败");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }

    @DeleteMapping("/{id}")
    public Result save(@PathVariable("id") int id) {
        Result result = new Result();
        int asve = roleService.deleteById(id);
        if (asve > 0) {
            result.setMessage("删除");
            result.setStatus(Result.RESPONSE_SUCCESS);
        } else {
            result.setMessage("删除");
            result.setStatus(Result.RESPONSE_FAIL);
        }
        return result;
    }

    /**
     * 校色管理编辑
     *
     * @param role
     * @return
     */
    @PutMapping
    public Result upd(@RequestBody Role role) {
        Result upd = roleService.upd(role);
        return upd;
    }


}

