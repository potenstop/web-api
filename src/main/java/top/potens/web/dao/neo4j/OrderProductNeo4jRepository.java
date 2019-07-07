package top.potens.web.dao.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import top.potens.web.model.neo4j.OrderProductNeo4j;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ProductNeo4jRepository
 * @projectName web-api
 * @date 2019/7/5 10:06
 */
@Repository
public interface OrderProductNeo4jRepository extends Neo4jRepository<OrderProductNeo4j, Long> {
}
