package com.jjy.pay;

import com.jjy.common.anno.EnableUserTokenFeignInterceptor;
import com.jjy.common.anno.EnableUserWebMvcConfiguration;
import com.jjy.pay.utils.AlipayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/7
 * @time : 13:24
 */
@SpringBootApplication
@EnableUserWebMvcConfiguration
@EnableFeignClients(basePackages = "com.jjy.feign.order")
@EnableConfigurationProperties(AlipayProperties.class)
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class , args) ;
    }

}
