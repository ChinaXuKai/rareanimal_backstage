package com.guangyou.rareanimal_backstage.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-03-24 16:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adminer {

    private Long adminerId;

    private String adminerName;

    private String adminerAccount;

    private String adminerPwd;

    private String adminerAvatar;

    private Integer isDelete;

}
