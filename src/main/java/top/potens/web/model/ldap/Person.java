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
    private String sn; //必填属性
    private String cn; //必填属性
    private String uid; //必填属性

    private String userPassword; //可选属性
    private String telephoneNumber; //可选属性
    private String seeAlso; //可选属性
    private String description;  //可选属性
}
