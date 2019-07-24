package top.potens.web.model.neo4j;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className UserNeo4j
 * @projectName web-api
 * @date 2019/7/5 9:54
 */
@NodeEntity("User")
@Data
public class UserNeo4j {
    @Id
    @GeneratedValue
    private Long id;

    private Integer userId;

    private String userName;

    @DateString("yyyy-MM-dd HH:mm:ss.SSS")
    private Date createTime;

    @Relationship(type = "AFFILIATION", direction = "OUTGOING")
    private AreaNeo4j cityAreaNeo4j;

    @Relationship(type = "RECOMMEND", direction = "OUTGOING")
    private UserNeo4j fromUser;
}
