package com.pj.dto;

import lombok.Data;

@Data
public class Pagepatient {
    private Integer page;

    private Integer limit;

    private String  name;

    private String idCard;
}
