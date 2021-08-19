package com.example.myblog.config;

import com.example.myblog.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> jwtExcludePatterns = new ArrayList();
//        添加不被拦截的路径
//        一些静态资源跟登录的接口
        jwtExcludePatterns.add("/img/**");
        jwtExcludePatterns.add("/login/**");

        jwtExcludePatterns.add("/markdown/**");
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(jwtExcludePatterns);

    }
}
