package com.jjy.manager;

import com.jjy.common.log.annotation.EnableLogAspect;
import com.jjy.manager.properties.MinioProperties;
import com.jjy.manager.properties.UserAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/25
 * @time : 13:58
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.jjy", "com.jjy.common"})
@EnableConfigurationProperties({UserAuthProperties.class, MinioProperties.class})
@EnableScheduling
@EnableLogAspect
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}