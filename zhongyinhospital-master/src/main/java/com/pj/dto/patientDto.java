package com.pj.dto;

import com.pj.entity.Patient;
import lombok.Data;

@Data
public class patientDto extends Patient {

    private String bodyTemperature;

    private String heartRate;

    private String bloodPressure;

    private String examinationCost;

    private  String prescriptionNum;

    private String pulse;

    private Integer registerId;
}
