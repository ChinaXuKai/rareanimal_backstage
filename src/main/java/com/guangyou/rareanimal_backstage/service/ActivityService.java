package com.guangyou.rareanimal_backstage.service;

import com.guangyou.rareanimal_backstage.pojo.dto.AuditDto;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.vo.ActivityVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;

/**
 * @author xukai
 * @create 2023-04-12 21:32
 */
public interface ActivityService {

    /**
     * 获取 活动的分页数据集
     * @param pageDto 分页条件
     * @return 活动分页数据集
     */
    PageDataVo<ActivityVo> getActivitiesByPage(PageDto pageDto);

    /**
     * 审核活动
     * @param auditDto
     * @return 活动id
     */
    int auditActivity(AuditDto auditDto);
}
