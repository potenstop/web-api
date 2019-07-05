package top.potens.web.model.neo4j;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.math.BigDecimal;
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
    private Long id;
    private BigDecimal paidAmount;
    @Relationship(type = "BUY", direction = "OUTGOING")
    private Set<ProductNeo4j> products;
}
