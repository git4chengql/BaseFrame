package com.ysusoft.frame.sys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author chengql
 * @create 2017-12-18 下午2:53
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ApiInfo apiInfo = new ApiInfo("燕软框架API", "燕软框架API", "0.1", null, null, null, null);
        Docket docket = new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/menu/.*")).build()
                .apiInfo(apiInfo).useDefaultResponseMessages(false);
        return docket;
    }
}
