package com.guangyou.rareanimal_backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guangyou.rareanimal_backstage.common.lang.Result;
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
        //分页查询
        List<User> userList = userMapper.getUserListByPage(pageDto.getPageSize() * (pageDto.getPage() - 1), pageDto.getPageSize());
        List<UserVo> userVoList = copyUtils.userListCopy(userList);
        //设置分页参数
        pageDataVo.setPageData(userVoList);
        pageDataVo.setCurrent(pageDto.getPage());
        pageDataVo.setSize(pageDto.getPageSize());
            //统计未删除用户个数
        int total = userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getIsDelete,0)).intValue();
        pageDataVo.setTotal(total);
        int isRemainZero = total%pageDto.getPageSize();
        if (isRemainZero != 0){
            pageDataVo.setPages( (total/pageDto.getPageSize()) + 1);
        }else {
            pageDataVo.setPages( total/pageDto.getPageSize() );
        }
        return pageDataVo;
    }


    @Override
    public Result deleteUserByUid(Long userId) {
        int deleteResult = userMapper.deleteByUid(userId);
        if (deleteResult == 0){
            return Result.fail( "删除用户失败");
        }
        return Result.succ(200, "删除用户成功", userId);
    }

    @Override
    public Result getUserListByLike(String userLike) {
        List<User> usersByLike = userMapper.selectUserListByLike('%'+userLike+'%');
        if (usersByLike.isEmpty()){
            return Result.succ("当前查询条件下没有用户");
        }
        List<UserVo> userVoList = copyUtils.userListCopy(usersByLike);
        return Result.succ(userVoList);
    }
}
