package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 患者表(Patient)实体类
 *
 * @author makejava
 * @since 2023-07-11 14:57:27
 */
@Data
public class Patient implements Serializable {
    /**
     * 序号
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 地址
     */
    private String address;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 电话号
     */
    private String telphone;
    /**
     * 卡号
     */
    private String cardId;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 民族
     */
    private String nationality;
    /**
     * 职业
     */
    private String career;
    /**
     * 家族历史
     */
    private String familyHistory;
    /**
     * 婚姻状况
     */
    private String maritalStatus;
    /**
     * 过去病史
     */
    private String pastHistory;
    /**
     * 个人史  生活史
     */
    private String personalHistory;
    /**
     * 创建日期
     */
    private Date createDatetime;
    /**
     * 处方号
     */
    private  String prescriptionNum;
    /**
     * 处方单创建时间
     */
    private  Date date;
    /**
     * 科室
     */
    private  String department;
    /**
     * 医生姓名
     */
    private  String doctorName;
    /**
     *就诊id
     */
    private  int queueId;
    /**
     * 主诉
     */
    private  String conditionDescription;
    /**
     * 开药信息
     */
    private String prescription;
    /**
     * 诊断信息
     */
    private String diagnosisResult;
    /**
     * 医嘱
     */
    private String medicalOrder;
    /**
     *体检费
     */
    private String examinationCost;
    /**
     *药费
     */
    private String drugCost;


}

