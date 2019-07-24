package top.potens.web.dao.neo4j;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.potens.web.model.neo4j.UserNeo4j;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className UserNeo4jRepository
 * @projectName web-api
 * @date 2019/7/3 20:47
 */
@Repository
public interface UserNeo4jRepository extends Neo4jRepository<UserNeo4j, Long> {
    /**
    *
    * 方法功能描述: 按userId查找
    *
    * @author yanshaowen
    * @date 2019/7/5 14:36
    * @param userId   用户id
    * @return
    * @throws
    */
    UserNeo4j findByUserId(Integer userId);

    /**
    *
    * 方法功能描述: 删除用户的归属关系
    *
    * @author yanshaowen
    * @date 2019/7/5 17:57
    * @param userId   用户id
    * @return
    * @throws
    */
    @Query("match (:User{userId:{0} })-[r:AFFILIATION]->(:Area) delete r")
    Long deletePartCity(Integer userId);

    @Query(value = "MERGE (n: User{userId:{userId} }) ON CREATE SET n.userId = {userId}, n.userName = {userName}, n.createTime = {createTime} " +
            " ON MATCH SET n.userName = {userName}")
    void createOrUpdate(@Param("userId") Integer userId, @Param("userName") String userName, @Param(value = "createTime") String createTime);
}

