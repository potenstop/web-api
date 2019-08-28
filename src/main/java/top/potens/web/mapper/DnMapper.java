package top.potens.web.mapper;

import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;

import javax.naming.Name;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className DnMapper
 * @projectName web-api
 * @date 2019/8/27 16:26
 */
public class DnMapper implements ContextMapper<String> {
    @Override
    public String mapFromContext(Object ctx) {
        DirContextAdapter context = (DirContextAdapter)ctx;
        Name name = context.getDn();
        return name.toString();
    }
}
