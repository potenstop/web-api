package top.potens.web.dao.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import top.potens.web.model.neo4j.OrderNeo4j;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className OrderNeo4jRepository
 * @projectName web-api
 * @date 2019/7/5 10:05
 */
@Repository
public interface OrderNeo4jRepository extends Neo4jRepository<OrderNeo4j, Long> {
}
