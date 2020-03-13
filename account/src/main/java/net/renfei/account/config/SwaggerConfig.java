package net.renfei.account.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

/**
 * Swagger2的配置
 *
 * @author RenFei
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String VERSION = "1.0.0";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.renfei.account.controller"))
                // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档的标题
                .title("账户中心")
                // 设置文档的描述
                .description("管理账户相关信息")
                // 设置文档的版本信息-> 1.0.0 Version information
                .version(VERSION)
                .termsOfServiceUrl("https://www.renfei.net")
                .build();
    }
}
