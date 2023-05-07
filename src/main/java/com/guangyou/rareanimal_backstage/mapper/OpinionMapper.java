package com.guangyou.rareanimal_backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guangyou.rareanimal_backstage.pojo.entity.Opinion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xukai
 * @create 2023-04-12 22:57
 */
@Mapper
public interface OpinionMapper extends BaseMapper<Opinion> {

    /**
     * 获取用户意见 分页数据集
     * @param initialDataLocation 初始数据位置
     * @param pageSize 每页多少数据
     * @return 用户意见分页数据集
     */
    List<Opinion> getOpinionsByPage(Integer initialDataLocation, Integer pageSize);
}
