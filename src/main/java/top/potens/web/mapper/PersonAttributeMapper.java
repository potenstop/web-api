package top.potens.web.mapper;

import org.springframework.ldap.core.AttributesMapper;
import top.potens.web.model.ldap.Person;

import javax.naming.directory.Attributes;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className PersonAttributeMapper
 * @projectName web-api
 * @date 2019/8/25 9:03
 */
public class PersonAttributeMapper implements AttributesMapper<Person> {
    @Override
    public Person mapFromAttributes(Attributes attr) throws javax.naming.NamingException {
        Person person = new Person();
        person.setSn((String)attr.get("sn").get());
        person.setCn((String)attr.get("cn").get());
        if (attr.get("uid") != null) {
            person.setUid((String)attr.get("uid").get());
        }
        if (attr.get("uidNumber") != null) {
            person.setUidNumber((String)attr.get("uidNumber").get());
        }
        if (attr.get("telephoneNumber") != null) {
            person.setTelephoneNumber((String)attr.get("telephoneNumber").get());
        }
        if (attr.get("seeAlso") != null) {
            person.setSeeAlso((String)attr.get("seeAlso").get());
        }
        if (attr.get("description") != null) {
            person.setDescription((String)attr.get("description").get());
        }
        if (attr.get("description") != null) {
            person.setDescription((String)attr.get("description").get());
        }
        return person;
    }
}
