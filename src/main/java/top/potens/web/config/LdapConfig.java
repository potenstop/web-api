package top.potens.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className LdapConfig
 * @projectName web-api
 * @date 2019/8/24 9:33
 */
@Configuration
@EnableLdapRepositories(basePackages = "top.potens.web.dao.mapper")
public class LdapConfig {
    @Value("${web.connection.ldap.base}")
    private String base;

    @Value("${web.connection.ldap.url}")
    private String url;

    @Value("${web.connection.ldap.admin.dn}")
    private String adminDn;

    @Value("${web.connection.ldap.admin.password}")
    private String adminPassword;

    @Bean
    ContextSource contextSource() {
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setBase(base);
        ldapContextSource.setUrl(url);
        ldapContextSource.setUserDn(adminDn);
        ldapContextSource.setPassword(adminPassword);
        return ldapContextSource;
    }

    @Bean
    LdapTemplate ldapTemplate(ContextSource contextSource) {
        return new LdapTemplate(contextSource);
    }
}

