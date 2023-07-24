package com.pj.dto;

import lombok.Data;

@Data
public class MenuDto {
    private Integer page;

    private Integer limit;

    private String menuName;

    private String menuType;

    private Integer menuId;
}
