package com.guangyou.rareanimal_backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guangyou.rareanimal_backstage.pojo.entity.Animal;
import com.guangyou.rareanimal_backstage.pojo.dto.AnimalBasicInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xukai
 * @create 2023-03-29 20:11
 */
@Mapper
public interface AnimalMapper extends BaseMapper<Animal> {

    /**
     * 添加动物的基本信息
     * @param animalBasicInfo 动物的基本信息
     * @return
     */
    int addAnimalBasicInfo(AnimalBasicInfoDto animalBasicInfo);

    /**
     * 根据动物名称查询动物
     * @param animalName
     * @return
     */
    Animal selectAnimalByName(String animalName);

    /**
     * 根据 动物id 添加 动物类型
     * @param animalId
     * @param isVertebrates
     * @param isCreep
     * @param isFish
     * @param isBirds
     * @param isSuckle
     * @param isAmphibian
     */
    void updateAnimalById(Integer animalId, Integer isVertebrates, Integer isCreep, Integer isFish, Integer isBirds, Integer isSuckle, Integer isAmphibian);

    /**
     * 根据动物id 删除 t_animal 的数据
     * @param animalId
     */
    void deleteByAnimalId(Integer animalId);

    /**
     * 按照分页查询动物
     * @param initialDataLocation
     * @param pageSize
     * @return
     */
    List<Animal> selectAnimalByPage(Integer initialDataLocation, Integer pageSize);

}
