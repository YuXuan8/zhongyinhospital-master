package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 就诊记录表(MedicalRecord)实体类
 *
 * @author makejava
 * @since 2023-07-13 10:54:18
 */
@Data
public class MedicalRecord implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 处方号（用来关联处方中的具体药物信息）
     */
    private String prescriptionNum;
    /**
     * 药方、处方信息
     */
    private String prescription;
    /**
     * 药品id集合
     */
    private String drugids;
    /**
     * 主诉
     */
    private String conditionDescription;
    /**
     * 诊断结果
     */
    private String diagnosisResult;
    /**
     * 医嘱
     */
    private String medicalOrder;
    /**
     * 总价格
     */
    private String money;
    /**
     * 门诊登记id
     */
    private Integer registerId;
    /**
     * 创建时间
     */
    private Date createDatetime;


}

