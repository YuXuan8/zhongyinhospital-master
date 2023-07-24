package com.pj.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * shiro的异常处理
 */
@ControllerAdvice
public class ShiroExceptionHandler {

    /**
     * 处理 没有权限的异常
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public String handlerUnauthorizedException(UnauthorizedException e){
        e.printStackTrace();
        return "error";
    }

    /**
     * 处理其他权限验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public String handlerUnauthorizedException(AuthorizationException e){
        e.printStackTrace();
        return "error";
    }
}
