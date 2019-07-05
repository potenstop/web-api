package top.potens.web.model.neo4j;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ProductNeo4j
 * @projectName web-api
 * @date 2019/7/5 10:05
 */
@NodeEntity("Product")
@Data
public class ProductNeo4j {
    private Long id;
    private String productName;
}
