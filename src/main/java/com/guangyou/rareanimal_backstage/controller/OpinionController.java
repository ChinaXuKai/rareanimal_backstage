package com.guangyou.rareanimal_backstage.controller;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.entity.Opinion;
import com.guangyou.rareanimal_backstage.pojo.vo.OpinionVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.service.OpinionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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





}
