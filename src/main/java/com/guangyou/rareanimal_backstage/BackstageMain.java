package com.guangyou.rareanimal_backstage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xukai
 * @create 2023-03-24 15:11
 */
@SpringBootApplication
@MapperScan("com.guangyou.rareanimal_backstage.mapper")
public class BackstageMain {

    public static void main(String[] args) {
        SpringApplication.run(BackstageMain.class, args);
    }
}
