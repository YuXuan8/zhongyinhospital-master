package com.pj.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典表(Dite)实体类
 *
 * @author makejava
 * @since 2023-07-16 00:09:21
 */
@Data
public class Dite implements Serializable {
    /**
     * 字典表
     */
    private Integer id;
    /**
     * 编号
     */
    private String typeno;
    /**
     * 权重
     */
    private Integer hierarchy;
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否废弃：0是1否
     */
    private Integer flog;

    private String typeName;




}

