package com.guangyou.rareanimal_backstage.service.impl;

import com.guangyou.rareanimal_backstage.service.UploadService;
import com.guangyou.rareanimal_backstage.utils.FtpUtil;
import com.guangyou.rareanimal_backstage.utils.IDUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xukai
 * @create 2023-03-29 13:39
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FtpUtil ftpUtil;

    /**
     * 上传图片
     * @param img 图片
     * @return 上传图片后得到的 url地址
     */
    @Override
    public String uploadImg(MultipartFile img) {
        //1、给上传的图片生成新的文件名
        //1.1获取原始文件名
        String oldName = img.getOriginalFilename();
        //1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
        String newName = IDUtil.genImageName();
        assert oldName != null;
        newName = newName + oldName.substring(oldName.lastIndexOf("."));
        //1.3生成文件在服务器端存储的子目录
        String filePath = new DateTime().toString("/yyyyMMdd/");

        //2、把图片上传到图片服务器
        //2.1获取上传的io流
        InputStream input = null;
        try {
            input = img.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.2调用FtpUtil工具类进行上传，并返回图片地址
        return ftpUtil.putImages(input, filePath, newName);
    }
}
