package com.learn.droolsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: chengyong.wang
 * @Date: 2018/9/30 上午10:43
 * @Description:
 */
@SpringBootApplication
public class DroolsDemoApplication {

    public static void main(String[] args) {
        System.setProperty("drools.dateformat","yyyy-MM-dd");

        SpringApplication.run(DroolsDemoApplication.class, args);
    }
}
