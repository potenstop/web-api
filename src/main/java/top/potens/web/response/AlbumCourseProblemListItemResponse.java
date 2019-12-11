package top.potens.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.potens.framework.annotation.JsonFormatDatetime;

import java.util.Date;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumResponse
 * @projectName web-api
 * @date 2019/10/22 10:22
 */
@Data
public class AlbumCourseProblemListItemResponse {
    @ApiModelProperty(value = "id", name = "albumCourseProblemId", required = true, example = "1")
    private Integer albumCourseProblemId;

    @ApiModelProperty(value = "创建时间", name = "createTime", required = true, example = "2019-01-01 11:11:11.0")
    @JsonFormatDatetime
    private Date createTime;

    @ApiModelProperty(value = "更新时间", name = "updateTime", required = true, example = "2019-01-01 11:11:11.0")
    @JsonFormatDatetime
    private Date updateTime;

    @ApiModelProperty(value = "专辑id", name = "albumId", required = true, example = "0")
    private Integer albumId;

    @ApiModelProperty(value = "课程专辑id", name = "albumCourseId", required = true, example = "0")
    private Integer albumCourseId;

    @ApiModelProperty(value = "用户id", name = "userId", required = true, example = "1")
    private Integer userId;

    @ApiModelProperty(value = "状态 1 保存试卷 2 提交试卷", name = "state", required = true, example = "1")
    private Integer state;
}
