package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 科室表(Department)实体类
 *
 * @author makejava
 * @since 2023-07-11 18:21:04
 */
@Data
public class Department implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门地址
     */
    private String address;
    /**
     * 类型 10.内科  11.外科 12。急诊 13。其他（体检）
     */
    private Integer type;
    /**
     * 部门创建时间
     */
    private Date createDatetime;
    /**
     * 名称
     */
    private String typeName;




}

