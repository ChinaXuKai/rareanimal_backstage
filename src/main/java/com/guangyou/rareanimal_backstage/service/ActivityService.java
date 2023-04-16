package com.guangyou.rareanimal_backstage.service;

import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.vo.ActivityVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;

/**
 * @author xukai
 * @create 2023-04-12 21:32
 */
public interface ActivityService {

    /**
     *
     * @param pageDto
     * @return
     */
    PageDataVo<ActivityVo> getActivitiesByPage(PageDto pageDto);

}
