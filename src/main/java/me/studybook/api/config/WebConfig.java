package me.studybook.api.config;

import lombok.Value;
import me.studybook.api.interceptor.CorsIC;
import me.studybook.api.interceptor.TokenValidationIC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///c:/tmp/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new CorsIC())
//                .addPathPatterns("/**");

        registry.addInterceptor(new TokenValidationIC())
                .addPathPatterns("/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/api/users/kakao-login")
                .excludePathPatterns("/api/posts")
                .excludePathPatterns("/api/posts/{id}")
                .excludePathPatterns("/api/naver/news");
    }
}
