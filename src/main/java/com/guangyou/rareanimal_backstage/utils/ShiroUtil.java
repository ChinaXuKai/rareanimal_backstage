package com.guangyou.rareanimal_backstage.utils;

import cn.hutool.core.bean.BeanUtil;
import com.guangyou.rareanimal_backstage.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author xukai
 * @create 2022-11-05 15:04
 */
public class ShiroUtil {


    public static AccountProfile getProfile(){
        AccountProfile accountProfile = new AccountProfile();
        BeanUtil.copyProperties(SecurityUtils.getSubject().getPrincipal(), accountProfile);
        return accountProfile;
    }

}
