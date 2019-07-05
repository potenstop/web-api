package top.potens.web.config;


import com.zaxxer.hikari.HikariDataSource;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by wenshao on 2019/6/15.
 */
@Configuration
@ComponentScan(basePackages = "top.potens.web.service.neo4j")
@EnableNeo4jRepositories(basePackages = "top.potens.web.dao.neo4j")
@EnableTransactionManagement
public class Neo4jConfig {
    //@Value("${data.master.url}")
    private String masterUrl = "bolt://potens.top:7687/";
    //@Value("${data.master.username}")
    private String masterUserName = "neo4j";
    //@Value("${data.master.password}")
    private String masterPassword = "Wendi_1209";

    @Bean
    public SessionFactory sessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(configuration(), "top.potens.web.model.neo4j");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
                .uri(masterUrl)
                .credentials(masterUserName, masterPassword)
                .build();
        return configuration;
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }
}

