package top.codesky.forcoder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Date: 2019/4/10 10:29
 * @Author: codesky
 * @Description: Swagger2的配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.codesky.forcoder.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("codesky", "codesky.top", "super_codesky@163.com");
        return new ApiInfoBuilder()
                .title("SpringBoot基于Swagger2构建的RESTful API文档")
                .description("更多请关注：http://api.codesky.top/swagger-ui.html")
                .termsOfServiceUrl("http://api.codesky.top")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
