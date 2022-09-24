package com.project.project8core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI().info(new Info()
        .title("Spring Boot Open API Test with Swagger")
        .description("설명 부분")
        .version("1.0.0"));
  }
}
