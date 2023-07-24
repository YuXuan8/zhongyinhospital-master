package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户角色表(UserRole)实体类
 *
 * @author makejava
 * @since 2023-07-05 13:49:20
 */
@Data
public class UserRole implements Serializable {
    /**
     * 角色用户中间表id
     */
    private Integer id;
    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 角色编号
     */
    private Integer roleId;
    /**
     * 邮箱+角色描述
     */
    private String description;
    /**
     * 注册时间
     */
    private Date createDatetime;




}

