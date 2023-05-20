package com.guangyou.rareanimal_backstage.service;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.pojo.vo.UserVo;

/**
 * @author xukai
 * @create 2023-04-06 12:53
 */
public interface UserService {

    /**
     * 根据分页条件 获取用户信息分页数据集
     * @param pageDto 分页条件
     * @return 用户信息分页数据集
     */
    PageDataVo<UserVo> getUserListByPage(PageDto pageDto);

    /**
     * 根据用户id 逻辑删除 用户
     * @param userId 用户id
     * @return 结果集
     */
    Result deleteUserByUid(Long userId);

    /**
     * 根据 用户名 和 用户昵称 模糊查询 用户列表
     * @param userLike 模糊查询条件
     * @return 结果集
     */
    Result getUserListByLike(String userLike);

}
