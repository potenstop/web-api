package top.potens.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.potens.framework.model.PageRequest;

import javax.validation.constraints.NotNull;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className UserListRequest
 * @projectName web-api
 * @date 2019/9/14 11:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "用户列表请求对象")
public class UserListRequest extends PageRequest {
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer userId;
}
