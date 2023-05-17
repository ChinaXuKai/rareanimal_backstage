package com.guangyou.rareanimal_backstage.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author xukai
 * @create 2023-03-29 11:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal implements Serializable {

    @TableId(value = "animal_id",type = IdType.AUTO)
    private Integer animalId;

    private String animalName;

    private String alias;

    private String animalImg;

    private Integer isVertebrates;

    private Integer isSuckle;

    private Integer isBirds;

    private Integer isCreep;

    private Integer isFish;

    private Integer isAmphibian;

    private String animalClassification;

    private String distributionArea;

    private String protectGrade;


}
