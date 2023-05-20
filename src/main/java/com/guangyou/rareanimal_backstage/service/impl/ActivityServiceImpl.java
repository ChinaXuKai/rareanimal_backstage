package com.guangyou.rareanimal_backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guangyou.rareanimal_backstage.mapper.ActivityMapper;
import com.guangyou.rareanimal_backstage.pojo.dto.AuditDto;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.entity.Activity;
import com.guangyou.rareanimal_backstage.pojo.vo.ActivityVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.service.ActivityService;
import com.guangyou.rareanimal_backstage.utils.CopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xukai
 * @create 2023-04-12 21:32
 */
@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private CopyUtils copyUtils;

    @Override
    public PageDataVo<ActivityVo> getActivitiesByPage(PageDto pageDto) {
        PageDataVo<ActivityVo> pageDataVo = new PageDataVo<>();
        //后台展示所有的活动
        List<Activity> activityList = activityMapper.getActivitiesByPage(pageDto.getPageSize() * (pageDto.getPage() - 1), pageDto.getPageSize());
        List<ActivityVo> activityVoList = copyUtils.activityListCopy(activityList);
        pageDataVo.setPageData(activityVoList);
        pageDataVo.setCurrent(pageDto.getPage());
        pageDataVo.setSize(pageDto.getPageSize());
        //后台查询所有活动的总数
        int total = activityMapper.selectCount(null).intValue();
        pageDataVo.setTotal(total);
        int isRemainZero = total%pageDto.getPageSize();
        if (isRemainZero != 0){
            pageDataVo.setPages( (total/pageDto.getPageSize()) + 1);
        }else {
            pageDataVo.setPages( total/pageDto.getPageSize() );
        }
        return pageDataVo;
    }


    @Override
    public int auditActivity(AuditDto auditDto) {
        LambdaQueryWrapper<Activity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Activity::getActivityId, auditDto.getId());
        Activity dbActivity = activityMapper.selectOne(queryWrapper);
        dbActivity.setAuditState(auditDto.getIsPassAudit());
        dbActivity.setAuditTime(System.currentTimeMillis());
        dbActivity.setAuditReason(auditDto.getAuditReason());
        return activityMapper.update(dbActivity,queryWrapper);
    }


}
