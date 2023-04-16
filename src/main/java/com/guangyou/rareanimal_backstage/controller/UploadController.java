package com.guangyou.rareanimal_backstage.controller;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xukai
 * @create 2023-03-29 13:38
 */
@RestController
@Slf4j
@RequestMapping("/upload")
@Api(tags = "上传图片控制器")
public class UploadController {

    @Autowired
    private UploadService uploadService;


    @ApiOperation(value = "图片上传接口",notes = "用于上传用户头像或文章内容图的接口")
    @PostMapping("/img")
    public Result uploadImg(@RequestParam("img") MultipartFile img){
        String imgUrl = uploadService.uploadImg(img);
        if (imgUrl == null){
            throw new RuntimeException("上传失败");
        }else {
            return Result.succ(200, "上传成功", imgUrl);
        }
    }

}
