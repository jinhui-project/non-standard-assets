package com.jinhui.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
public class ApplicationSwaggerConfig {

    //访问 http://localhost:port/swagger-ui.html
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                //.paths(Predicates.not(PathSelectors.regex("/error.*")))
//                .apis(RequestHandlerSelectors.basePackage("com.jinhui.controller.abs"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("jinhuisystem API")
                .description("description")
                .version("2.0")
                .build();
    }

}
