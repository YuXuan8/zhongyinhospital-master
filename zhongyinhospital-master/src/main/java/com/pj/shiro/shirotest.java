package com.pj.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class shirotest {
    public static void main(String[] args) {
        //1创建ini格式工厂模型创建对象
        IniSecurityManagerFactory securityManagerFactory=new IniSecurityManagerFactory("classpath:shrio.ini");
        //2获取安全管理器
        SecurityManager securityManager = securityManagerFactory.getInstance();
        //3让安全管理器开始工作
        SecurityUtils.setSecurityManager(securityManager);
        //4获取系统访问的主题就是用户登录的信息
        Subject subject = SecurityUtils.getSubject();
        //4.1创建token
        AuthenticationToken token=new UsernamePasswordToken("admin","12345");
        try {
            subject.login(token);
            System.out.println("登陆成功");
        } catch (UnknownAccountException e) {
            System.out.println("账号错误");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
        } catch (AuthenticationException e) {
            System.out.println("认证错误");
        }

    }
}
