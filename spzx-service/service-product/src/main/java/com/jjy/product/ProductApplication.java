package com.jjy.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/4
 * @time : 9:23
 */
@SpringBootApplication
@EnableCaching
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
