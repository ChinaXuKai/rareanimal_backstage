package com.guangyou.rareanimal_backstage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.LoginDto;
import com.guangyou.rareanimal_backstage.pojo.vo.AdminerVo;
import com.guangyou.rareanimal_backstage.service.AdminerService;
import com.guangyou.rareanimal_backstage.utils.JwtUtil;
import com.guangyou.rareanimal_backstage.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author xukai
 * @create 2023-03-29 8:50
 */
@RestController
@RequestMapping("/login")
@Slf4j
@Api(tags = "管理员控制器")
public class LoginController {

    @Autowired
    private AdminerService adminerService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtUtil jwtUtil;

    @SneakyThrows
    @ApiOperation(value = "管理员登录接口",notes = "管理员登录接口")
    @PostMapping("/loginAdminer")
    public Result loginAdminer(@RequestBody LoginDto loginParam, HttpServletResponse response){
        //调用userService层查询是否有该用户
        AdminerVo adminer = adminerService.getAdminerByAccAndPwd(loginParam.getAccount(),loginParam.getPassword());
        if (adminer == null){
            return Result.fail("登录失败，请检查账号密码");
        }
        // 生成 jwt 信息，并将其封装到responseHeader中
        String jwt = jwtUtil.generateToken(adminer.getAdminerId());
        response.setHeader("token", jwt);
        response.setHeader("Access-control-Expose-Headers", "token");
//         将 JWT 信息存储至 redis 的 库0 中，并设置过期时间
        String jsonUser = new ObjectMapper().writeValueAsString(adminer);
        redisUtil.select(0);
        redisUtil.set(jwt, jsonUser);           //jwt:jsonUser
        redisUtil.expire(jwt, 7, TimeUnit.DAYS);
        log.info("jwtUtil.generateTokenById={}",jwt);
        log.info("jsonUser={}",jsonUser);
        return Result.succ(200, "登录成功", adminer);
    }


    @ApiOperation(value = "退出登录，并删除redis中的token",notes = "退出登录，并删除redis中的token（需要传jwt）")
    @PostMapping("/logoutUser")
    public Result logoutUser(HttpServletRequest request){
        SecurityUtils.getSubject().logout();
        //获取Jwt信息，将其从redis中删除
        String jwt = request.getHeader("Authorization");
        //查看是否已经删除，避免重复退出
        log.info("jwt：{}", redisUtil.get(jwt));
            //已退出
        if (redisUtil.get(jwt) == null) {
            return Result.fail(Result.FORBIDDEN, "已经退出登录，不能重复退出", null);
        }
            //未退出
        redisUtil.delete(jwt);
        return Result.succ("退出登录成功");
    }

}
