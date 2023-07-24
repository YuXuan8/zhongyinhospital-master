package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 菜单权限表(Menu)实体类
 *
 * @author makejava
 * @since 2023-07-05 13:49:22
 */
@Data
public class Menu implements Serializable {
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
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String menuType;
    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;
    /**
     * 是否刷新（0刷新 1不刷新）
     */
    private String isRefresh;
    /**
     * 权限标识
     */
    private String perms;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 打开方式（menuItem页签 menuBlank新窗口）
     */
    private String target;



}

