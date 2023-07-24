package com.pj.util;

import lombok.Data;

@Data
public class GetCardIdInforReqVO {
   private Integer command;
   private  String cardId;
   private  String patientName;
}
