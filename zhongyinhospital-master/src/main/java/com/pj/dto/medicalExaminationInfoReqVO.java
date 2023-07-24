package com.pj.dto;

import lombok.Data;
//用户体检完成
@Data
public class medicalExaminationInfoReqVO {
    private String bloodPressure;
    private String bodyTemperature;
    private String cardId;
    private double examinationCost;
    private String heartRate;
    private String prescriptionNum;
    private String pulse;
    private String queueId;

}
