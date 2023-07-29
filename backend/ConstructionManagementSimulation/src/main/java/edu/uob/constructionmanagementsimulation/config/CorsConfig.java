package edu.uob.constructionmanagementsimulation.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"})
                .maxAge(16800)
                .allowedHeaders("*")
                .exposedHeaders("*")
                .allowCredentials(true);
    }
}
