package com.guangyou.rareanimal_backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guangyou.rareanimal_backstage.pojo.entity.Adminer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xukai
 * @create 2023-03-29 9:40
 */
@Mapper
public interface AdminerMapper extends BaseMapper<Adminer> {

    /**
     * 根据账号密码登录管理员
     * @param account 账号
     * @param password 密码
     * @return
     */
//    @Select("select * from user where id = #{id}}")
//    List<Adminer> selectAdminerByAccAndPwd(String account, String password, @Param("id") Integer id);
    List<Adminer> selectAdminerByAccAndPwd(String account, String password);
}
