package com.guangyou.rareanimal_backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guangyou.rareanimal_backstage.pojo.entity.AnimalLabel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xukai
 * @create 2023-03-29 20:13
 */
@Mapper
public interface AnimalLabelMapper extends BaseMapper<AnimalLabel> {
    /**
     * 根据动物id 添加动物标签
     * @param animalId 动物id
     * @param animalLabel 动物标签
     */
    void addAnimalLabelById(Integer animalId, String animalLabel);
}
