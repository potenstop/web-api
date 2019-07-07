package top.potens.web.model.neo4j;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className OrderProductNeo4j
 * @projectName web-api
 * @date 2019/7/5 10:05
 */
@NodeEntity("OrderProduct")
@Data
public class OrderProductNeo4j {
    @Id
    @GeneratedValue
    private Long id;

    private Integer productId;

    private String productName;

    private Integer productCount;
}
