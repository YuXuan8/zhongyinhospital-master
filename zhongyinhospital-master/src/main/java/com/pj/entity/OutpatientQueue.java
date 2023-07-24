package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 就诊队列表(OutpatientQueue)实体类
 *
 * @author makejava
 * @since 2023-07-12 00:04:35
 */
@Data
public class OutpatientQueue implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 门诊登记id
     */
    private Integer registerId;
    /**
     * 医生id
     */
    private Integer userId;
    /**
     * 患者id
     */
    private Integer patientId;
    /**
     * 门诊队列状态 1表示正常状态，-1表示稍后处理，0过期
     */
    private Integer status;
    /**
     * 描述
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createDatetime;




}

