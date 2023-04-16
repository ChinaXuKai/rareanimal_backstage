package com.guangyou.rareanimal_backstage.service.impl;

import com.guangyou.rareanimal_backstage.mapper.AdminerMapper;
import com.guangyou.rareanimal_backstage.pojo.entity.Adminer;
import com.guangyou.rareanimal_backstage.pojo.vo.AdminerVo;
import com.guangyou.rareanimal_backstage.service.AdminerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xukai
 * @create 2023-03-29 9:38
 */
@Service
public class AdminerServiceImpl implements AdminerService {

    @Autowired
    private AdminerMapper adminerMapper;

    @Override
    public AdminerVo getAdminerByAccAndPwd(String account, String password) {
        List<Adminer> adminerList = adminerMapper.selectAdminerByAccAndPwd(account,password);
        for (Adminer adminer : adminerList){
            if (account.equals(adminer.getAdminerAccount()) && password.equals(adminer.getAdminerPwd())){
                return copy(adminer);
            }
        }
        return null;
//        throw new UnknownAccountException("未查询到有该管理员，请检查账号密码");
    }

    private AdminerVo copy(Adminer adminer) {
        AdminerVo adminerVo = new AdminerVo();
        BeanUtils.copyProperties(adminer, adminerVo);
        return adminerVo;
    }


}
