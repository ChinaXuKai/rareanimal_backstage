package com.guangyou.rareanimal_backstage.service;

import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.vo.OpinionVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;

/**
 * @author xukai
 * @create 2023-04-12 22:55
 */
public interface OpinionService {
    /**
     * 获取用户意见 分页数据集
     * @param pageDto 分页条件
     * @return 用户意见 分页数据集
     */
    PageDataVo<OpinionVo> getOpinionsByPage(PageDto pageDto);
}
