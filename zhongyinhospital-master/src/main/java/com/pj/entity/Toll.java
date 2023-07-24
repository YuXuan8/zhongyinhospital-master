package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 收费记录表(Toll)实体类
 *
 * @author makejava
 * @since 2023-07-14 21:38:05
 */
@Data
public class Toll implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date createDatetime;


}

