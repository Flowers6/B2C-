package com.jjy.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/25
 * @time : 16:07
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("admin-api")
                .pathsToMatch("/admin/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("B2C电商系统API接口文档")
                        .version("1.0")
                        .description("B2C电商系统API接口文档")
                        .contact(new Contact().name("jjy")));
    }

}
