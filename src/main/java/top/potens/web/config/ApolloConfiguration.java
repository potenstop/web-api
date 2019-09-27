package top.potens.web.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ApolloConfiguration
 * @projectName web-api
 * @date 2019/8/24 10:17
 */
@Configuration
@Data
public class ApolloConfiguration {
    @Value("${web.connection.ldap.ou.user}")
    private String ldapOuUser;

    @Value("${web.connection.ldap.ou.group}")
    private String ldapOuGroup;

    @Value("${web.connection.ldap.identifier:uid}")
    private String ldapIdentifier;

    @Value("${qiniu.access.key}")
    private String qiniuAccessKey;

    @Value("${qiniu.secret.key}")
    private String qiniuSecretKey;

    @Value("${qiniu.bucket.local}")
    private String qiniuBucketLocal;
}
