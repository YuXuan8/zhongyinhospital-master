package com.pj.dto;

import lombok.Data;

@Data
public class PageloginInfor {
    private Integer page;

    private Integer limit;

    private String username;
}
