package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 药品出库表(Goout)实体类
 *
 * @author makejava
 * @since 2023-07-18 19:14:53
 */
@Data
public class Goout implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 药品编号
     */
    private Integer drugId;
    /**
     * 药名
     */
    private String drugName;
    /**
     * 出库数量
     */
    private Integer drugNum;
    /**
     * 小计（该商品数量X单价）
     */
    private String moner;
    /**
     * 处方号
     */
    private String tolltakedrugNo;
    /**
     * 患者id
     */
    private String patientId;
    /**
     * 售出时间
     */
    private Date createTime;



}

