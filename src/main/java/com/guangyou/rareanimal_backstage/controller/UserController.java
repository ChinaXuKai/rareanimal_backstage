package com.guangyou.rareanimal_backstage.controller;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.entity.User;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.pojo.vo.UserVo;
import com.guangyou.rareanimal_backstage.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xukai
 * @create 2023-04-06 12:38
 */
@Slf4j
@Api(tags = "用户控制器：用户信息处理、用户意见处理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "分页展示用户列表")
    @GetMapping("/getUserListByPage")
    public Result getUserListByPage(PageDto pageDto){
        //查询用户列表
        PageDataVo<UserVo> userVoPage = userService.getUserListByPage(pageDto);
        if (userVoPage.getPageData().isEmpty()){
            return Result.fail("获取用户列表异常");
        }
        return Result.succ(200,"用户列表已获取到",userVoPage);
    }


    @ApiOperation(value = "删除用户")
    @DeleteMapping("/deleteUserByUid")
    public Result deleteUserByUid(Long userId){
        return userService.deleteUserByUid(userId);
    }


    @ApiOperation(value = "根据用户名或用户昵称模糊查询用户列表")
    @GetMapping("/getUserListByLike")
    public Result getUserListByLike(String userLike){
        return userService.getUserListByLike(userLike);
    }


}
