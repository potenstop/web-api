package top.potens.web.config;


import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import top.potens.framework.interceptor.MybatisLogInterceptor;

import javax.sql.DataSource;

/**
 * Created by wenshao on 2019/6/15.
 */
@Configuration
@MapperScan(basePackages = ToolDataConfig.PACKAGE, sqlSessionTemplateRef = "sqlSessionTemplate")
public class ToolDataConfig {

    static final String PACKAGE = "top.potens.web.dao.db";

    //@Value("${data.master.url}")
    private String masterUrl = "jdbc:mysql://potens.top:7306/web?useSSL=false";
    //@Value("${data.master.username}")
    private String masterUserName = "web";
    //@Value("${data.master.password}")
    private String masterPassword = "dsqdq2333#$%";

    @Bean(name = "masterDataSource")
    @Primary
    public HikariDataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(masterUrl);
        hikariDataSource.setUsername(masterUserName);
        hikariDataSource.setPassword(masterPassword);
        return hikariDataSource;
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:mappings/*/*.xml"));
        MybatisLogInterceptor mybatisLogInterceptor = new MybatisLogInterceptor();
        Interceptor[] plugins = {mybatisLogInterceptor};
        sessionFactory.setPlugins(plugins);
        return sessionFactory;
    }
    @Bean("sqlSessionTemplate")
    public SqlSessionTemplate createSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactoryBean SqlSessionFactoryBean) throws Exception {
        return new SqlSessionTemplate(SqlSessionFactoryBean.getObject());
    }
    @Bean("masterTransactionManager")
    @Primary
    public DataSourceTransactionManager createWeekendTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }


}

