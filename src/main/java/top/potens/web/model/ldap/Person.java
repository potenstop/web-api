package top.potens.web.model.ldap;

import lombok.Data;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className Person
 * @projectName web-api
 * @date 2019/8/25 9:02
 */
@Data
public class Person {
    private String sn;
    private String cn;
    private String uid;
    private String uidNumber;

    private String telephoneNumber;
    private String seeAlso;
    private String description;
}
