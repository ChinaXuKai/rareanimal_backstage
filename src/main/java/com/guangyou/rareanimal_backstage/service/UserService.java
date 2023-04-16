package com.guangyou.rareanimal_backstage.service;

import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.pojo.vo.UserVo;

import java.util.List;

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
}
