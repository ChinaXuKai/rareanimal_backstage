package com.guangyou.rareanimal_backstage.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-03-29 13:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public static final String OFFICIAL_ACCOUNT = "POEAzsyxk";

    private Integer userId;

    private String userName;

    private String userAccount;

    private String userPwd;

    private String userAvatar;

    private Long createTime;

    private Integer isDelete;

    private String userAddress;
}
