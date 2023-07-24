package com.pj.controller;

import com.pj.entity.RoleMenu;
import com.pj.service.RoleMenuService;
import com.pj.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色和菜单关联表(RoleMenu)表控制层
 *
 * @author makejava
 * @since 2023-07-05 13:49:20
 */
@RestController
@RequestMapping("/api/roleMenu/")
public class RoleMenuController {
    /**
     * 服务对象
     */
    @Resource
    private RoleMenuService roleMenuService;

    /**
     * 数据回显
     * @param roleId
     * @return
     */
    @GetMapping("/getRoleMenu")
    public Result getRoleMenu(int roleId){
        Result result=new Result();
        List<RoleMenu> roleMenus = roleMenuService.setMenuIdMenus(roleId);
        List<Integer> integers=new ArrayList<>();
        for (RoleMenu roleMenu : roleMenus) {
            Integer menuId = roleMenu.getMenuId();
            integers.add(menuId);
        }
        result.setData(integers);
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;

    }


}

