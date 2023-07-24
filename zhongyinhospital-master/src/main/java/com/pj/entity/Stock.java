package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 药品进货表(Stock)实体类
 *
 * @author makejava
 * @since 2023-07-16 15:37:36
 */
@Data
public class Stock implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 药品id
     */
    private Integer drugId;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 单价
     */
    private String price;
    /**
     * 费用
     */
    private String money;
    /**
     * 进货商
     */
    private String supplier;
    /**
     * 时间
     */
    private Date stockTime;




}

