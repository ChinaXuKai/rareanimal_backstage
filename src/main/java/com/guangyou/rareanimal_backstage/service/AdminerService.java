package com.guangyou.rareanimal_backstage.service;

import com.guangyou.rareanimal_backstage.pojo.entity.Adminer;
import com.guangyou.rareanimal_backstage.pojo.vo.AdminerVo;

/**
 * @author xukai
 * @create 2023-03-29 9:38
 */
public interface AdminerService {

    /**
     * 根据账号密码登录管理员
     * @param account 账号
     * @param password 密码
     * @return
     */
    AdminerVo getAdminerByAccAndPwd(String account, String password);
}
