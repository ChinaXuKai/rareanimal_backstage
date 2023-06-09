package com.guangyou.rareanimal_backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guangyou.rareanimal_backstage.pojo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xukai
 * @create 2023-04-06 13:14
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据账号查询用户
     * @param authorAccount 用户账号
     * @return 用户
     */
    User getUsersByAccount(String authorAccount);

    /**
     * 根据用户id 查询用户
     * @param userId 用户id
     * @return 用户
     */
    User getUserById(Integer userId);

    /**
     * 获取 用户分页数据集
     * @param initialDataLocation 初始数据位置
     * @param pageSize 每页多少数据
     * @return 用户分页数据集
     */
    List<User> getUserListByPage(Integer initialDataLocation, Integer pageSize);

    /**
     * 根据用户id 逻辑删除 用户
     * @param userId 用户id
     * @return 逻辑删除结果
     */
    int deleteByUid(Long userId);

    /**
     * 根据 用户名 或 用户昵称 模糊查询用户列表
     * @param userLike 模糊查询条件
     * @return
     */
    List<User> selectUserListByLike(String userLike);
}
