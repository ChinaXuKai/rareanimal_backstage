package com.guangyou.rareanimal_backstage.controller;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.vo.ActivityVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xukai
 * @create 2023-04-12 21:28
 */
@Slf4j
@RestController
@RequestMapping("/activity")
@Api(tags = "管理活动接口")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @ApiOperation(value = "分页获取活动集合")
    @GetMapping("/getActivitiesByPage")
    public Result getActivitiesByPage(PageDto pageDto){
        PageDataVo<ActivityVo> pageDataVo = activityService.getActivitiesByPage(pageDto);

        if (pageDataVo.getPageData().isEmpty()){
            return Result.succ("当前还没有人发布活动");
        }
        return Result.succ(200, "分页获取活动集合成功", pageDataVo);
    }


    @ApiOperation(value = "审核活动")
    @PutMapping("/auditActivity")
    public Result auditActivity(Long activityId,String isPassAudit){
        int auditResult = activityService.auditActivity(activityId,isPassAudit);
        if (auditResult == 0){
            return Result.fail("审核出现异常");
        }
        return Result.succ(200,"审核成功",activityId);

    }

}
