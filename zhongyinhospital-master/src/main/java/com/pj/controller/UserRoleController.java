package com.pj.controller;

import com.pj.entity.UserRole;
import com.pj.service.UserRoleService;
import com.pj.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色表(UserRole)表控制层
 *
 * @author makejava
 * @since 2023-07-05 13:49:19
 */
@RestController
@RequestMapping("/api/userRole")
public class UserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private UserRoleService userRoleService;

    /**
     * 管理员管理数据回显
     * @param uid
     * @return
     */
    @GetMapping("/getUserRole")
    public Result get( Integer uid){
        System.out.println(uid);
        Result result= new Result();
        List<UserRole> userRoles = userRoleService.setRoleIdRoles(uid);
        List<Integer> roles=new ArrayList<>();
        for (UserRole userRole : userRoles) {
            roles.add(userRole.getRoleId());
        }
        result.setData(roles);
        result.setStatus(Result.RESPONSE_SUCCESS);
        return result;
    }





}

