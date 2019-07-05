package top.potens.web.model.neo4j;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AreaNeo4jRepository
 * @projectName web-api
 * @date 2019/7/5 10:02
 */
@NodeEntity("Area")
@Data
public class AreaNeo4j {
    @Id
    @GeneratedValue
    private Long id;

    private String areaName;

    private String areaCode;

    private Integer rank;
}
