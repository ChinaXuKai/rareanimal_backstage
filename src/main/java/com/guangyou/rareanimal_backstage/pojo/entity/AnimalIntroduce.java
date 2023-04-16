package com.guangyou.rareanimal_backstage.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-03-29 11:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalIntroduce {

    private Integer animalId;

    private String morphology;

    private String habitat;

    private String habits;

    private String populationStatus;

    private String protectionLevel;

    private String elseInfo;

}
