package top.potens.web.dao.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import top.potens.web.model.neo4j.AreaNeo4j;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AreaNeo4jRepository
 * @projectName web-api
 * @date 2019/7/5 10:05
 */
@Repository
public interface AreaNeo4jRepository extends Neo4jRepository<AreaNeo4j, Long> {
    /**
    *
    * 方法功能描述: 根据地区code获取area对象
    *
    * @author yanshaowen
    * @date 2019/7/5 12:52
    * @param areaCode   地区code
    * @return
    * @throws
    */
    AreaNeo4j findByAreaCode(String areaCode);
}
