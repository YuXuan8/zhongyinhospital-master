package com.pj.dto;

import lombok.Data;

import java.util.Date;
//门珍记录字段
@Data
public class medical_recordDto {

    private  Integer cardId;

    private String prescriptionNum;

    private String prescription;

    private String drugIds;

    private String conditionDescription;

    private String diagnosisResult;

    private String medicalOrder;

    private double money;

    private Integer registerId;

    private Date createDatetime;

    private Integer createId;

    private String name;

    private Integer takingDrugStatus;
}
