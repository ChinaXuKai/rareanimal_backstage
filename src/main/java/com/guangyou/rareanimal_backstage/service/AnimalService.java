package com.guangyou.rareanimal_backstage.service;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.AnimalDto;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.entity.Animal;
import com.guangyou.rareanimal_backstage.pojo.vo.AnimalVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;

import java.util.List;

/**
 * @author xukai
 * @create 2023-03-29 14:48
 */
public interface AnimalService {
    /**
     * 新增动物
     * @param animalDto
     * @return
     */
    Result addAnimal(AnimalDto animalDto);

    /**
     * 根据动物id 删除对应动物的相关数据
     * @param animalId
     * @return
     */
    int deleteAnimalById(Integer animalId);

    /**
     * 根据动物id 修改对应动物的相关数据
     * @param animalDto
     * @return 动物id
     */
    int updateAnimal(AnimalDto animalDto);

    /**
     * 分页获取动物信息
     * @param pageDto 分页参数
     * @return 分页后的动物信息集合
     */
    PageDataVo<AnimalVo> getAnimalsByPage(PageDto pageDto);

    /**
     * 根据 animalLike 进行模糊查询动物
     * @param animalLike 模糊的查询：动物名称、动物标签
     * @return
     */
    List<AnimalVo> selectAnimalByLike(String animalLike);

    /**
     * 根据动物id 查询动物详情数据
     * @param animalId 动物id
     * @return 结果集
     */
    Result getAnimalDetailByAid(Long animalId);
}
