package com.ysusoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ysusoft.frame.sys.base.interceptor.LogInterceptor;
import com.ysusoft.frame.sys.base.interceptor.UserSecurityInterceptor;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.ysusoft"})
public class YsusoftframeApplication {

    public static void main(String[] args) {
      SpringApplication.run(YsusoftframeApplication.class, args);
    }

    @Configuration
    public static class WebMvcConfigurer extends WebMvcConfigurerAdapter {

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(
                    //登录验证过滤器
                    new UserSecurityInterceptor()
            ).addPathPatterns("/*.do")
                    .addPathPatterns("/*.json")
                    .addPathPatterns("/*.html").addPathPatterns("/*.ftl")
                    .excludePathPatterns("/login.*").excludePathPatterns("/").excludePathPatterns("/logout.*").excludePathPatterns("/^websocket");

            registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
        }
    }
}
