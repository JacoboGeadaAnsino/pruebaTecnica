package com.altia.inditex.prueba.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
@ConfigurationProperties(prefix = "swagger.api")
public class SwaggerProperties {
	
  private String title;  
  private String version;  
  private String description;
  
}
