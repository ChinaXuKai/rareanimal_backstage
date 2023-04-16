package com.guangyou.rareanimal_backstage.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-03-29 11:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalLabel {

    private Long id;

    private Long animalId;

    private String animalLabel;
}
