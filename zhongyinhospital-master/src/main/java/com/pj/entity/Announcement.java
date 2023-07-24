package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 公告(Announcement)实体类
 *
 * @author makejava
 * @since 2023-07-07 22:56:09
 */
@Data
public class Announcement implements Serializable {
    /**
     * 公告id
     */
    private Integer id;
    /**
     * 公告标题
     */
    private String title;
    /**
     * 公告内容
     */
    private String contents;
    /**
     * 公告创建的时间
     */
    private Date createDatetime;
    /**
     * 公告状态 0正常 1废弃
     */
    private Integer annStatus;
    /**
     * 公告状态修改时间
     */
    private String annDate;




}

