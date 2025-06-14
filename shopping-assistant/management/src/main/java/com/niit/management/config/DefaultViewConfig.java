package com.niit.management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DefaultViewConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //配置常用界面
        registry.addViewController("/login").setViewName("login.html");
        registry.addViewController("/home").setViewName("homepage.html");
        registry.addViewController("/").setViewName("homepage.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
