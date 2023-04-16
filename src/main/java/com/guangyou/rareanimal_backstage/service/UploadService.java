package com.guangyou.rareanimal_backstage.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author xukai
 * @create 2023-03-29 13:39
 */
public interface UploadService {
    //上传图片
    String uploadImg(MultipartFile img);
}
