package top.potens.web.model.neo4j;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.Set;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className MemberNeo4j
 * @projectName web-api
 * @date 2019/7/5 9:54
 */
@NodeEntity("Member")
@Data
public class MemberNeo4j {
    @Id
    @GeneratedValue
    private Long id;

    private Integer memberId;

    private String memberName;

    private Date createTime;

    @Relationship(type = "AFFILIATION", direction = "OUTGOING")
    private AreaNeo4j cityAreaNeo4j;
}
