package com.altia.inditex.prueba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
	
  private final SwaggerProperties properties;
  
  
  @Bean
  public OpenAPI customOpenAPI() {
    return (new OpenAPI())
      .info((new Info())
        .title(this.properties.getTitle())
        .version(this.properties.getVersion())
        .description(this.properties.getDescription()));
  }
}