package top.potens.web.model.neo4j;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className OrderNeo4jRepository
 * @projectName web-api
 * @date 2019/7/5 10:04
 */
@NodeEntity("Order")
@Data
public class OrderNeo4j {
    @Id
    @GeneratedValue
    private Long id;

    private Integer orderId;

    private BigDecimal paidAmount;

    @DateString("yyyy-MM-dd HH:mm:ss.SSS")
    private Date createTime;

    @Relationship(type = "BUY", direction = "OUTGOING")
    private Set<OrderProductNeo4j> products;

    @Relationship(type = "BUY_ER", direction = "OUTGOING")
    private UserNeo4j userNeo4J;

    @Relationship(type = "DIRECT_USER", direction = "OUTGOING")
    private UserNeo4j directUser;

    @Relationship(type = "INDIRECT_USER", direction = "OUTGOING")
    private UserNeo4j indirectUser;
}
