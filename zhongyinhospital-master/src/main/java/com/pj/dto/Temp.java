package com.pj.dto;

import lombok.Data;

/**
 * 门诊收费根据卡号查询信息
 */
@Data
public class Temp {
  private   Integer cardId;
    private Integer tollStatus;
    private Integer registerId;
    private String prescriptionNum;
    private String money;
}
