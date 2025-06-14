package com.zxst.shoop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.zxst.shoop.mapper")
@EnableScheduling  //开启定时任务
@SpringBootApplication
public class CleanPic_Application {
    public static void main(String[] args) {
        SpringApplication.run(CleanPic_Application.class, args);
    }
}
