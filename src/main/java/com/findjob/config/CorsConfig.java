package com.findjob.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 * @author Chichiu Yeung
 * Created in 2019/3/18 10:55
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "OPTIONS", "DELETE")
                .allowedOrigins("http://localhost", "http://120.79.93.191")
                .maxAge(3600);
    }
}
