package com.guangyou.rareanimal_backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guangyou.rareanimal_backstage.mapper.UserMapper;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.entity.User;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.pojo.vo.UserVo;
import com.guangyou.rareanimal_backstage.service.UserService;
import com.guangyou.rareanimal_backstage.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xukai
 * @create 2023-04-06 12:53
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CopyUtils copyUtils;

    @Override
    public PageDataVo<UserVo> getUserListByPage(PageDto pageDto) {
        PageDataVo<UserVo> pageDataVo = new PageDataVo<>();
        List<User> userList = userMapper.getUserListByPage(pageDto.getPageSize() * (pageDto.getPage() - 1), pageDto.getPageSize());
        List<UserVo> userVoList = copyUtils.userListCopy(userList);
        pageDataVo.setPageData(userVoList);
        pageDataVo.setCurrent(pageDto.getPage());
        pageDataVo.setSize(pageDto.getPageSize());
        int total = userMapper.selectCount(null).intValue();
        pageDataVo.setTotal(total);
        int isRemainZero = total%pageDto.getPageSize();
        if (isRemainZero != 0){
            pageDataVo.setPages( (total/pageDto.getPageSize()) + 1);
        }else {
            pageDataVo.setPages( total/pageDto.getPageSize() );
        }
        return pageDataVo;
    }
}
