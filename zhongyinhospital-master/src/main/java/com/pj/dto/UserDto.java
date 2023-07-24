package com.pj.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Integer doctorId;
    private String doctor;
    private String workDateTime;
    private double price;
    private int allowNum;
    private int nowNum;
    private String workAddress;
    private Date updateTime;

}
