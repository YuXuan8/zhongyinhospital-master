package com.pj.dto;

import lombok.Data;

/**
 * 门珍挂起和门珍完成提交信息
 */
@Data
public class MedicalRecordReqVO {
    /**
     * 病人id
     */
    private Integer cardId;
    /**
     * 住宿
     */
    private String conditionDescription;
    /**
     * 处方号
     */
    private String prescriptionNum;
    /**
     * 诊断表id
     */
    private Integer queueId;

    private String career;
    private String diagnosisResult;
    private String drugIds;
    private String familyHistory;
    private String maritalStatus;
    private String medicalOrder;
    private Integer money;
    private String pastHistory;
    private String personalHistory;
    private String prescription;
}
