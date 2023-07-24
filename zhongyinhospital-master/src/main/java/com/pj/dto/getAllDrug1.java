package com.pj.dto;

import lombok.Data;

//药品信息管理接收参数
@Data
public class getAllDrug1 {
    private Integer page;

    private Integer limit;

    private String name;

    private String drugType;

    private String unit;

    private String specification;

    private String role;

}
