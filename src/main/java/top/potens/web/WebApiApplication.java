package top.potens.web;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.potens.framework.aop.ControllerVisitAspect;
import top.potens.framework.response.ResultCodeInit;

@SpringBootApplication(scanBasePackages = {"top.potens"})
@EnableTransactionManagement
@EnableApolloConfig
@EnableFeignClients(basePackages = {"top.potens"})
public class WebApiApplication {

    public static void main(String[] args) {
        ResultCodeInit.addResultCodeDefinitionClassByPackage("top.potens.web.code");
        ResultCodeInit.addResultCodeDefinitionClassByPackage("top.potens.cms.code");
        ResultCodeInit.addResultCodeDefinitionClassByPackage("top.potens.wechat.code");
        SpringApplication.run(WebApiApplication.class, args);
    }
}
