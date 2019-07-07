package top.potens.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className MemberNeo4jRequest
 * @projectName web-api
 * @date 2019/7/5 14:49
 */
@Data
@ApiModel(description = "member neo4j 对象")
public class MemberNeo4jRequest {
    @NotNull
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer memberId;

    @NotNull
    @ApiModelProperty(value = "用户名称", example = "123")
    private String memberName;

    @NotNull
    @ApiModelProperty(value = "创建时间", example = "2019-01-01 10:10:10.0")
    private Date createTime;

    @NotNull
    @ApiModelProperty(value = "用户所属城市code", example = "110100000000")
    private String partCityCode;
}
