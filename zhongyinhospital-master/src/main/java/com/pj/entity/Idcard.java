package com.pj.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 身份证信息表(Idcard)实体类
 *
 * @author makejava
 * @since 2023-07-11 15:29:34
 */
@Data
public class Idcard implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 民族
     */
    private String nationality;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 地址
     */
    private String address;
    /**
     * 生日
     */
    private String birthday;




}

