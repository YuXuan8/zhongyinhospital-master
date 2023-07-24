package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 划价收费-拿药信息表(TollTakedrug)实体类
 *
 * @author makejava
 * @since 2023-07-12 16:56:24
 */
@Data
public class TollTakedrug implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 处方号
     */
    private String prescriptionNum;
    /**
     * 拿药时间
     */
    private String takingDrugDateTime;
    /**
     * 药房操作员
     */
    private String takingDrugOperator;
    /**
     * 取药状态：0未取 1已取
     */
    private Integer takingDrugStatus;
    /**
     * 收费时间
     */
    private String tollDateTime;
    /**
     * 收费操作员
     */
    private String tollOperator;
    /**
     * 患者id
     */
    private Integer patientId;
    /**
     * 支付金额
     */
    private String money;
    /**
     * 创建时间
     */
    private Date createDatetime;




}

