package top.potens.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by wenshao on 2019/6/15.
 */
@ApiModel(description = "用户对象")
@Data
public class MemberResponse {
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer memberId;

}
