package com.jjy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/25
 * @time : 13:58
 */
@SpringBootApplication(scanBasePackages = "com.jjy")
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}