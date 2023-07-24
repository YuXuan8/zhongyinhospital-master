package com.pj.dto;

import lombok.Data;

@Data
public class PageUser {
    private Integer page;

    private Integer limit;

    private String role;
}
