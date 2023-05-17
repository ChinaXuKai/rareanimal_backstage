package com.guangyou.rareanimal_backstage.service;

import com.guangyou.rareanimal_backstage.pojo.dto.OpinionReplyDto;
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

    /**
     * 管理员回复用户意见
     * @param opinionReplyDto 回复所需参数
     * @return 回复id
     */
    Long replyOpinion(OpinionReplyDto opinionReplyDto);

    /**
     * 根据用户意见id 逻辑删除 用户意见
     * @param opinionId 用户意见id
     * @return 逻辑删除结果影响的条数
     */
    int deleteOpinion(Long opinionId);
}
