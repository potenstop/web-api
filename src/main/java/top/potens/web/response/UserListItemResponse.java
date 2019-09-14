package top.potens.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className UserListItemResponse
 * @projectName web-api
 * @date 2019/9/14 9:17
 */
@ApiModel(description = "用户列表对象")
@Data
public class UserListItemResponse {
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer userId;

    @ApiModelProperty(value = "用户昵称", example = "123")
    private String nickname;

    @ApiModelProperty(value = "头像", example = "http://")
    private String avatar;

    @ApiModelProperty(value = "是否删除 0 否 1 是", example = "0")
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间", example = "2019-09-14 01:01:01.789")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", example = "2019-09-14 01:01:01.789")
    private Date updateTime;
}
