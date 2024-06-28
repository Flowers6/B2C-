package com.jjy;

import com.jjy.properties.MinioProperties;
import com.jjy.properties.UserAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties({UserAuthProperties.class, MinioProperties.class})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}