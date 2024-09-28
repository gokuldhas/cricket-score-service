package com.cricket.score.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Allow CORS for all paths
                        .allowedOrigins("http://localhost:8080")  // Add the origin where Swagger is hosted
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allowed HTTP methods
                        .allowedHeaders("*");  // Allow all headers
            }
        };
    }
}
