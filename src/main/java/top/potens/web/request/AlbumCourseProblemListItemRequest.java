package top.potens.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.potens.framework.model.PageRequest;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumCourseListItemRequest
 * @projectName web-api
 * @date 2019/10/22 10:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AlbumCourseProblemListItemRequest extends PageRequest {
    @ApiModelProperty(value = "专辑id", name = "albumId", example = "1")
    private Integer albumId;

    @ApiModelProperty(value = "创建时间范围搜索", example = "2019-09-01,2019-09-10")
    private String createTime;

    @ApiModelProperty(value = "按用户id搜索", name = "userId", example = "1")
    private Integer userId;

    @ApiModelProperty(value = "按状态搜索 1 仅保存了 2 提交试卷", name = "state", example = "1")
    private Integer state;
}
