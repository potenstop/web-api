package top.potens.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Parameter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wenshao on 2019/6/15.
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    //不支持IE浏览器
    @Bean
    public Docket buildDocket() {
        ParameterBuilder plateformParameterBuilder = new ParameterBuilder();
        plateformParameterBuilder.name("platform").parameterType("header")
                .defaultValue("10").description("平台").modelRef(new ModelRef("string"))
                .required(false).build();

        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(plateformParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf()).
                        globalOperationParameters(aParameters)
                .directModelSubstitute(Byte.class, Integer.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.potens"))
                .paths(PathSelectors.any())
                .build();


    }


    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("xxx接口文档")
                .description("xxx相关接口的文档")
                .termsOfServiceUrl("http://www.xxx.com")
                .version("2.0")
                .build();
    }

}