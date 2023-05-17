package com.guangyou.rareanimal_backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guangyou.rareanimal_backstage.mapper.AnimalIntroduceMapper;
import com.guangyou.rareanimal_backstage.mapper.AnimalLabelMapper;
import com.guangyou.rareanimal_backstage.mapper.AnimalMapper;
import com.guangyou.rareanimal_backstage.pojo.dto.*;
import com.guangyou.rareanimal_backstage.pojo.entity.Animal;
import com.guangyou.rareanimal_backstage.pojo.entity.AnimalIntroduce;
import com.guangyou.rareanimal_backstage.pojo.entity.AnimalLabel;
import com.guangyou.rareanimal_backstage.pojo.vo.AnimalLabelVo;
import com.guangyou.rareanimal_backstage.pojo.vo.AnimalVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.service.AnimalService;
import com.guangyou.rareanimal_backstage.utils.CopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xukai
 * @create 2023-03-29 14:48
 */
@Slf4j
@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalMapper animalMapper;
    @Autowired
    private AnimalIntroduceMapper animalIntroduceMapper;
    @Autowired
    private AnimalLabelMapper animalLabelMapper;

    /**
     * 新增动物 ：需开启事务
     * 1、先添加的动物的基本信息 ：animalBasicInfo
     * 2、根据动物名称 查询动物，获取到 动物id
     * 3、根据 动物id 添加动物类型 ：animalType
     * 4、根据 动物id 添加动物标签 ：animalLabel
     * 5、根据 动物id 添加动物简介 ：animalIntroduce
     * @param animalDto
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public Integer addAnimal(AnimalDto animalDto) {
        //1、添加动物的基本信息
        AnimalBasicInfoDto animalBasicInfo = animalDto.getAnimalBasicInfo();
        animalMapper.addAnimalBasicInfo(animalBasicInfo);
        //2、根据动物名称查询动物，获取到等 动物id
        String animalName = animalBasicInfo.getAnimalName();
        Animal animal= animalMapper.selectAnimalByName(animalName);
        Integer animalId = animal.getAnimalId();
        //3、根据 动物id 添加动物类型 ：animalType
        AnimalTypeDto animalType = animalDto.getAnimalType();
        Integer isAmphibian = animalType.getIsAmphibian();
        Integer isBirds = animalType.getIsBirds();
        Integer isCreep = animalType.getIsCreep();
        Integer isFish = animalType.getIsFish();
        Integer isVertebrates = animalType.getIsVertebrates();
        Integer isSuckle = animalType.getIsSuckle();
        animalMapper.updateAnimalById(animalId,isVertebrates,isCreep,isFish,isBirds,isSuckle,isAmphibian);
        //4、根据 动物id 添加动物标签 ：animalLabel
        List<String> animalLabelList = animalDto.getAnimalLabel().getAnimalLabelList();
        String animalLabels = null;
        for (int i = 0; i < animalLabelList.size(); i++){
            String label = animalLabelList.get(i);
            if (animalLabels != null){
                animalLabels = animalLabels + "、" + label;
            }else{
                animalLabels = label;
            }
        }
        animalLabelMapper.addAnimalLabelById(animalId,animalLabels);
        //5、根据 动物id 添加动物简介 ：animalIntroduce
        AnimalIntroduceDto animalIntroduce = animalDto.getAnimalIntroduce();
        animalIntroduce.setAnimalId(animalId);
        animalIntroduceMapper.addAnimalIntroduceById(animalIntroduce);
        return animalId;
    }


    /**
     * 删除动物 ：需开启事务
     * 1、根据 动物id 删除 t_animal 表的数据
     * 2、根据 动物id 删除 t_animal_introduce 表数据
     * 3、根据 动物id 删除 t_animal_label 表数据
     * @param animalId
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteAnimalById(Integer animalId) {
        // 1、根据 动物id 删除 t_animal 表的数据
        animalMapper.deleteByAnimalId(animalId);
        // 2、根据 动物id 删除 t_animal_introduce 表数据
        animalIntroduceMapper.deleteByAnimalId(animalId);
        // 3、根据 动物id 删除 t_animal_label 表数据
        LambdaQueryWrapper<AnimalLabel> labelWrapper = new LambdaQueryWrapper<>();
        labelWrapper.eq(AnimalLabel::getAnimalId,animalId);
        animalLabelMapper.delete(labelWrapper);
        return animalId;
    }

    /**
     * 修改动物：需开启事务
     * 1、根据 动物id 修改 t_animal 表数据
     * 2、根据 动物id 修改 t_animal_introduce 表数据
     * 3、根据 动物id 修改 t_animal_label 表数据
     * @param animalDto
     * @return 被修改的动物id
     */
    @Transactional(readOnly = false)
    @Override
    public int updateAnimal(AnimalDto animalDto) {
        // 1、根据 动物id 修改 t_animal 表数据
        LambdaQueryWrapper<Animal> animalQueryWrapper = new LambdaQueryWrapper<>();
        animalQueryWrapper.eq(Animal::getAnimalId, animalDto.getAnimalId());
        Animal animal = new Animal();
        AnimalBasicInfoDto animalBasicInfo = animalDto.getAnimalBasicInfo();
        AnimalTypeDto animalType = animalDto.getAnimalType();

        animal.setAnimalName(animalBasicInfo.getAnimalName());
        animal.setAlias(animalBasicInfo.getAnimalAlias());
        animal.setAnimalImg(animalBasicInfo.getAnimalImgUrl());
        animal.setAnimalClassification(animalBasicInfo.getAnimalSpecificType());
        animal.setDistributionArea(animalBasicInfo.getDistributionArea());
        animal.setProtectGrade(animalBasicInfo.getProtectGrade());
        animal.setIsVertebrates(animalType.getIsVertebrates());
        animal.setIsAmphibian(animalType.getIsAmphibian());
        animal.setIsCreep(animalType.getIsCreep());
        animal.setIsBirds(animalType.getIsBirds());
        animal.setIsSuckle(animalType.getIsSuckle());
        animal.setIsFish(animalType.getIsFish());
        animalMapper.update(animal, animalQueryWrapper);
        // 2、根据 动物id 修改 t_animal_introduce 表数据
        LambdaQueryWrapper<AnimalIntroduce> introduceQueryWrapper = new LambdaQueryWrapper<>();
        introduceQueryWrapper.eq(AnimalIntroduce::getAnimalId, animalDto.getAnimalId());
        AnimalIntroduce animalIntroduce = new AnimalIntroduce();
        AnimalIntroduceDto introduce = animalDto.getAnimalIntroduce();

        animalIntroduce.setHabitat(introduce.getHabitat());
        animalIntroduce.setHabits(introduce.getHabits());
        animalIntroduce.setMorphology(introduce.getMorphology());
        animalIntroduce.setPopulationStatus(introduce.getPopulationStatus());
        animalIntroduce.setProtectionLevel(introduce.getProtectionLevel());
        animalIntroduce.setElseInfo(introduce.getElseInfo());
        animalIntroduceMapper.update(animalIntroduce,introduceQueryWrapper);
        // 3、根据 动物id 修改 t_animal_label 表数据
        AnimalLabel animalLabel = new AnimalLabel();
        LambdaQueryWrapper<AnimalLabel> labelQueryWrapper = new LambdaQueryWrapper<>();
        labelQueryWrapper.eq(AnimalLabel::getAnimalId,animalDto.getAnimalId());

        List<String> animalLabelList = animalDto.getAnimalLabel().getAnimalLabelList();
        String animalLabels = null;
        for (int i = 0; i < animalLabelList.size(); i++){
            String label = animalLabelList.get(i);
            if (animalLabels != null){
                animalLabels = animalLabels + "、" + label;
            }else{
                animalLabels = label;
            }
        }

        animalLabel.setAnimalId(animalDto.getAnimalId().longValue());
        animalLabel.setAnimalLabel(animalLabels);
        animalLabelMapper.update(animalLabel,labelQueryWrapper);
        return animalDto.getAnimalId();
    }


    @Autowired
    private CopyUtils copyUtils;

    /**
     * 分页获取动物信息
     * @param pageDto 分页参数
     * @return 分页后的
     */
    @Override
    public PageDataVo<AnimalVo> getAnimalsByPage(PageDto pageDto) {
        PageDataVo<AnimalVo> pageDataVo = new PageDataVo<>();
        List<Animal> animalList = animalMapper.selectAnimalByPage(pageDto.getPageSize() * (pageDto.getPage() - 1), pageDto.getPageSize());
        List<AnimalVo> animalVoList = copyUtils.animalListCopy(animalList);
        //
        pageDataVo.setPageData(animalVoList);
        pageDataVo.setCurrent(pageDto.getPage());
        pageDataVo.setSize(pageDto.getPageSize());
        //数据集的总个数：数据库查询全部，然后获取size()
        List<Animal> allAnimalList = animalMapper.selectList(null);
        pageDataVo.setTotal(allAnimalList.size());
        //总多少页：
        int isRemainZero = allAnimalList.size()%pageDto.getPageSize();
        if (isRemainZero != 0){
            pageDataVo.setPages( (allAnimalList.size()/pageDto.getPageSize()) + 1);
        }else {
            pageDataVo.setPages( allAnimalList.size()/pageDto.getPageSize() );
        }
        return pageDataVo;
    }

    @Override
    public List<AnimalVo> selectAnimalByLike(String animalLike) {
        List<Animal> animalsByLike = animalMapper.selectAnimalByLikeName("%"+animalLike+"%");
        List<AnimalVo> animalVoList = copyUtils.animalListCopy(animalsByLike);
        getAnimalLabels(animalVoList);
        return animalVoList;
    }

    /**
     * 将数据库里的 单个动物标签 拆分成 List<String>类型的多个动物标签
     * @param animalList
     * @return
     */
    private void getAnimalLabels(List<AnimalVo> animalList){
        for (AnimalVo animalVo : animalList){
            Long animalId = animalVo.getAnimalId();
            AnimalLabelVo animalLabelVo = new AnimalLabelVo();
            AnimalLabel animalLabel = animalLabelMapper.selectOne(new LambdaQueryWrapper<AnimalLabel>().eq(AnimalLabel::getAnimalId, animalId));
            BeanUtils.copyProperties(animalLabel,animalLabelVo);
            String[] animalLabels = animalLabelVo.getAnimalLabel().split("、");
            List<String> labelList = new ArrayList<>(Arrays.asList(animalLabels));
            animalVo.setAnimalLabel(labelList);
        }
    }
}
