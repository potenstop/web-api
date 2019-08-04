package top.potens.web.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.redisson.config.Config;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className RedissonConfiguration
 * @projectName web-api
 * @date 2019/7/23 13:19
 */
@Configuration
public class RedissonConfiguration {
    @Value("${fx.config.redisson.url}")
    private String url;

    @Value("${fx.config.redisson.password}")
    private String password = "potens";

    @Value("${fx.config.redisson.database}")
    private Integer database = 2;

    @Bean
    public RedissonClient getRedisson(){

        Config config = new Config();
        config.useSingleServer().setAddress(url).setPassword(password).setDatabase(database);
        //添加主从配置
        // config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});

        return Redisson.create(config);
    }
}
