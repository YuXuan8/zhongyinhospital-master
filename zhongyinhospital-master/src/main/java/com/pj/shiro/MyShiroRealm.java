package com.pj.shiro;

import com.pj.entity.User;
import com.pj.service.UserService;
import com.pj.util.MenuDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        String email = (String) principals.getPrimaryPrincipal();
        //创建角色和权限对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //根据用户名获取用户的菜单及可用权限
        List<MenuDto> menuDtos = userService.role(email);
        //用户角色
        HashSet<String> roles = new HashSet<>();
        //用户权限
        List<String> authority=new ArrayList<>();
        for (MenuDto menuDto : menuDtos) {
            roles.add(menuDto.getRole());
            if (menuDto.getPerms()!=null){
                authority.add(menuDto.getPerms());
            }
        }
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(authority);
        //返回权限
        return authorizationInfo;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1获取用户身份信息
        String email = authenticationToken.getPrincipal().toString();
        //2获取用户信息
        User user = userService.selectByEmail(email);
        //3判断数据并完成封装
        if (user != null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(),//令牌身份信息对象
                    user.getPassword(),//用户数据库中存储密码
//                    ByteSource.Util.bytes(user.getSalt().getBytes()),//加密时的盐值
                    email//用户名
            );
            return authenticationInfo;
        }
        return null;
    }
}
