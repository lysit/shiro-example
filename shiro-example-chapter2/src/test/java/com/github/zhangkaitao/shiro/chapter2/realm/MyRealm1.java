package com.github.zhangkaitao.shiro.chapter2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-25
 * <p>Version: 1.0
 * SecurityManager要验证用户身份，那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；
 * 也需要从Realm得到用户相应的角色/权限进行验证用户是否能进行操作
 * 默认情况下不配置realm将使用org.apache.shiro.realm.text.IniRealm
 */
public class MyRealm1 implements Realm {

    public String getName() {//获取realm的名字
        return "myrealm1";
    }

    public boolean supports(AuthenticationToken token) {//判断realm是否支持此token
        return token instanceof UsernamePasswordToken; //仅支持UsernamePasswordToken类型的Token
    }
    
    //根据Token获取认证信息 
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();  //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
        if(!"zhang".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
