package com.pj.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色和菜单关联表(RoleMenu)实体类
 *
 * @author makejava
 * @since 2023-07-05 13:49:20
 */
@Data
public class RoleMenu implements Serializable {
    private Integer id;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 菜单ID
     */
    private Integer menuId;




}

