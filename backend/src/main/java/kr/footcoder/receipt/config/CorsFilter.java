package kr.footcoder.receipt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by niee on 2018. 4. 5..
 */
@Configuration
public class CorsFilter {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(false)
                        .allowedOrigins("*")
                        .allowedMethods("POST","GET","OPTIONS")
                        .maxAge(3600);
            }
        };
    }
}
