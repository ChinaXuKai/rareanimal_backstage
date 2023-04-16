package com.guangyou.rareanimal_backstage.shiro;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.guangyou.rareanimal_backstage.pojo.entity.Adminer;
import com.guangyou.rareanimal_backstage.utils.RedisUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xukai
 * @create 2022-11-04 9:38
 */
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof com.guangyou.rareanimal_backstage.shiro.JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 强转为 jwtToken
        com.guangyou.rareanimal_backstage.shiro.JwtToken jwtToken = (com.guangyou.rareanimal_backstage.shiro.JwtToken) token;
        // 获取 jwtToken 的 身份（jwt）
        String jwt = jwtToken.getPrincipal().toString();
        // 根据 jwt 从redis中获取对应的 jsonUser，并转换为 UserVo
        String jsonAdminer = redisUtil.get(jwt);
        Adminer adminer = JSON.parseObject(jsonAdminer, Adminer.class);

        if (adminer == null) {
            throw new UnknownAccountException("账户不存在");
        }

        // 构建认证令牌对象 AccountProfile
        com.guangyou.rareanimal_backstage.shiro.AccountProfile profile = new com.guangyou.rareanimal_backstage.shiro.AccountProfile();
        // 复制userVo对象的属性到profile对象的属性上
        BeanUtil.copyProperties(adminer, profile);

        // 构建认证信息对象：认证令牌对象、token凭证（jwt）、当前realm类名称
        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}
