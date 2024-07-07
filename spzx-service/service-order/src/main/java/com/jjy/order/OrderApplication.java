package com.jjy.order;

import com.jjy.common.anno.EnableUserTokenFeignInterceptor;
import com.jjy.common.anno.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/6
 * @time : 13:38
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.jjy.feign.cart", "com.jjy.feign.product", "com.jjy.feign.user"})
@EnableUserTokenFeignInterceptor
@EnableUserWebMvcConfiguration
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class , args) ;
    }
}
