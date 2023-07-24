package com.pj.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 角色表(Role)实体类
 *
 * @author makejava
 * @since 2023-07-05 13:49:21
 */
@Data
public class Role implements Serializable {
    /**
     * ID
     */
    private Integer id;
    /**
     * 显示顺序
     */
    private Integer roleSort;
    /**
     * 角色标识
     */
    private String role;
    /**
     * 备注
     */
    private String remark;
    /**
     * 角色状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 1管理员 2代表删除 ）
     */
    private String delFlag;
    /**
     * 创建时间
     */
    private Date createDatetime;

    private List<Integer> menuIds;




}

