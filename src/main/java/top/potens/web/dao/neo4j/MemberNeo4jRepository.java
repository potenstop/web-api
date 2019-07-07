package top.potens.web.dao.neo4j;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.potens.web.model.neo4j.MemberNeo4j;

import java.util.Date;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className MemberNeo4jRepository
 * @projectName web-api
 * @date 2019/7/3 20:47
 */
@Repository
public interface MemberNeo4jRepository extends Neo4jRepository<MemberNeo4j, Long> {
    /**
    *
    * 方法功能描述: 按memberId查找
    *
    * @author yanshaowen
    * @date 2019/7/5 14:36
    * @param memberId   用户id
    * @return
    * @throws
    */
    MemberNeo4j findByMemberId(Integer memberId);

    /**
    *
    * 方法功能描述: 删除用户的归属关系
    *
    * @author yanshaowen
    * @date 2019/7/5 17:57
    * @param memberId   用户id
    * @return
    * @throws
    */
    @Query("match (:Member{memberId:{0} })-[r:AFFILIATION]->(:Area) delete r")
    Long deletePartCity(Integer memberId);

    @Query(value = "MERGE (n: Member{memberId:{memberId} }) ON CREATE SET n.memberId = {memberId}, n.memberName = {memberName}, n.createTime = {createTime} " +
            " ON MATCH SET n.memberName = {memberName}")
    void createOrUpdate(@Param("memberId") Integer memberId, @Param("memberName") String memberName, @Param(value = "createTime") String createTime);
}

