package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 挂号记录表(Register)实体类
 *
 * @author makejava
 * @since 2023-07-11 13:31:12
 */
@Data
public class Register implements Serializable {
    /**
     * ID
     */
    private Integer id;
    /**
     * 门诊登记编号
     */
    private String registerednum;
    /**
     * 科室Id
     */
    private Integer departmentId;
    /**
     * 医生id
     */
    private Integer doctorId;
    /**
     * 医生名称
     */
    private String doctor;
    /**
     * 付费方式
     */
    private String payType;
    /**
     * 挂号类型
     */
    private String registerType;
    /**
     * 诊查费
     */
    private String price;
    /**
     * 挂号费
     */
    private String registerPriice;
    /**
     * 实付费用
     */
    private String payPrice;
    /**
     * 找零
     */
    private String changePrice;
    /**
     * 病人ID
     */
    private Integer patientId;
    /**
     * 创建人id（挂号护士id）
     */
    private Integer createId;
    /**
     * 挂号状态。-1:过期，1:挂号成功
     */
    private Integer registerStatus;
    /**
     * 就诊状态,包括门诊，体检。0:未就诊，1:已就诊
     */
    private Integer treatmentStatus;
    /**
     * 创建人名字
     */
    private String createName;
    /**
     * 收费状态：0：未收费  1：已收费
     */
    private Integer chargeStatus;
    /**
     * 创建时间
     */
    private Date createDatetime;
    /**
     * 科室
     */
    private String departmentName;
    /**
     * 病人
     */
    private String name;
    /**
     * 状态
     */
    private String status;
    /**
     *处方号
     */
    private String prescriptionNum;



}

