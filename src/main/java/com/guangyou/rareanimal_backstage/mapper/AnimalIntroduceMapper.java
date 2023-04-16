package com.guangyou.rareanimal_backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guangyou.rareanimal_backstage.pojo.entity.AnimalIntroduce;
import com.guangyou.rareanimal_backstage.pojo.dto.AnimalIntroduceDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xukai
 * @create 2023-03-29 20:11
 */
@Mapper
public interface AnimalIntroduceMapper extends BaseMapper<AnimalIntroduce> {
    /**
     * 根据动物id 添加 动物信息
     * @param animalIntroduce
     */
    void addAnimalIntroduceById(AnimalIntroduceDto animalIntroduce);

    /**
     * 根据动物id 删除 t_animal_introduce 表数据
     * @param animalId
     */
    void deleteByAnimalId(Integer animalId);
}
