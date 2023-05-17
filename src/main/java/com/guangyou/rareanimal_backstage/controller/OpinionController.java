package com.guangyou.rareanimal_backstage.controller;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.OpinionReplyDto;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.entity.Opinion;
import com.guangyou.rareanimal_backstage.pojo.vo.OpinionVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.service.OpinionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xukai
 * @create 2023-04-12 22:51
 */
@Slf4j
@RestController
@RequestMapping("/opinion")
@Api(tags = "管理用户意见接口")
public class OpinionController {

    @Autowired
    private OpinionService opinionService;

    @ApiOperation(value = "分页获取用户意见")
    @GetMapping("/getOpinionsByPage")
    public Result getOpinionsByPage(PageDto pageDto){
        PageDataVo<OpinionVo> pageDataVo = opinionService.getOpinionsByPage(pageDto);
        if (pageDataVo.getPageData().isEmpty()){
            return Result.succ("当前还没有用户发表过意见");
        }
        return Result.succ(200,"用户意见分页数据如下",pageDataVo);
    }


    @ApiOperation(value = "管理员回复用户意见")
    @PostMapping("/replyOpinion")
    public Result replyOpinion(OpinionReplyDto opinionReplyDto){
        Long replyId = opinionService.replyOpinion(opinionReplyDto);
        if (replyId == 0 || replyId == null) {
            return Result.fail("回复用户意见出现了异常");
        }
        return Result.succ("回复用户意见成功，回复id为：" + replyId + ",意见id为：" + opinionReplyDto.getOpinionId());
    }


    @ApiOperation(value = "管理员删除用户意见")
    @DeleteMapping("/deleteOpinion")
    public Result deleteOpinion(Long opinionId){
        int deleteResult = opinionService.deleteOpinion(opinionId);
        if (deleteResult == 0) {
            return Result.fail("删除出现异常");
        }
        return Result.succ("删除用户意见成功，被删除的意见id为：" + opinionId);
    }

}
