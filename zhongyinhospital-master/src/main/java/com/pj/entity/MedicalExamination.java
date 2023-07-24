package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 体检报告(MedicalExamination)实体类
 *
 * @author makejava
 * @since 2023-07-14 10:54:53
 */
@Data
public class MedicalExamination implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 血压
     */
    private String bloodPressure;
    /**
     * 体温
     */
    private String bodyTemperature;
    /**
     * 心率
     */
    private String heartRate;
    /**
     * 脉搏
     */
    private String pulse;
    /**
     * 门诊登记id
     */
    private Integer registerId;
    /**
     * 检查费用
     */
    private String examinationCost;
    /**
     * 处方号
     */
    private String prescriptionNum;
    /**
     * 创建时间
     */
    private Date createDatetime;



}

