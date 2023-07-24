package com.pj.dto;

import lombok.Data;

/**
 * 修改密码接受的参数
 */
@Data
public class ChangePasswordReqVO {
//    原始密码
    private String password;
    private String newPassword;
    private String okPassword;
}
