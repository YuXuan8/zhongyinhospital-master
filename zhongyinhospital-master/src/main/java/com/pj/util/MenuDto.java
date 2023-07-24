package com.pj.util;

import lombok.Data;

@Data
public class MenuDto {
    /**
     * 菜单ID
     */
    private Integer menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 父菜单ID
     */
    private Integer parentId;
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String menuType;
    /**
     * 权限标识
     */
    private String perms;
    /**
     * 图标
     */
    private String icon;
    /**
     * 连接
     */
    private String url;
    /**
     * 角色
     */
    private String role;

}
