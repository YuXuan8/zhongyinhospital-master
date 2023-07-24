package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 登录信息表(LoginInfor)实体类
 *
 * @author makejava
 * @since 2023-07-18 11:10:49
 */
@Data
public class LoginInfor implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 登录IP
     */
    private String loginIp;
    /**
     * 登录客户端
     */
    private String loginBroswer;
    /**
     * 登录地址
     */
    private String loginAddress;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createDatetime;

    private String username;




}

