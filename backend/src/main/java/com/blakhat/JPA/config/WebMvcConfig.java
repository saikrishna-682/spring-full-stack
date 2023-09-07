package com.blakhat.JPA.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("#{'${cors.allowed-origins}'.split(',')}")
    private List<String> allowedOrigins;

    @Value("#{'${cors.allowed-methods}'.split(',')}")
    private List<String> allowedMethods;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        CorsRegistration corsRegistration = registry.addMapping("/api/**");
        allowedOrigins.forEach(corsRegistration::allowedOrigins);
        allowedMethods.forEach(corsRegistration::allowedMethods);
    }
}

//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    private final CorsProperties corsProperties;
//
//    @Value("#{'${cors.allowed-origins}'.split(',')}")
//    private List<String> allowedOrigins;
//
//    @Value("#{'${cors.allowed-methods}'.split(',')}")
//    private List<String> allowedMethods;
//
//    @Autowired
//    public WebMvcConfig(CorsProperties corsProperties) {
//        this.corsProperties = corsProperties;
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")
//                .allowedOrigins(corsProperties.getAllowedOrigins().toArray(new String[0]))
//                .allowedMethods(corsProperties.getAllowedMethods().toArray(new String[0]));
//    }
//}






