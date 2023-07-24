package com.pj.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典类型表(Dicttype)实体类
 *
 * @author makejava
 * @since 2023-07-17 10:43:02
 */
@Data
public class Dicttype implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 自定义编号
     */
    private String typeno;
    /**
     * 字典名称
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


}

