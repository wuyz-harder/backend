package com.example.myblog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadFile implements WebMvcConfigurer {
    @Value("${file.upload.abspath}")
    private String abpath;

    //文件夹url
    @Value("${file.upload.urlpath}")
    private String ulrpath;

    public UploadFile() {
        System.out.println("注册进来了=====================");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/markdown/**").addResourceLocations("classpath:/static/markdown/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler(ulrpath).addResourceLocations("file:"+abpath);
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

    }
}
