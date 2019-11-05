package top.potens.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumResponse
 * @projectName web-api
 * @date 2019/10/22 10:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AlbumCourseListItemResponse extends AlbumResponse {
    @ApiModelProperty(value = "题目总数", name = "contentCount", required = true, example = "1")
    private Long contentCount;

    @ApiModelProperty(value = "所属课程id", name = "courseId", required = true, example = "1")
    private Integer courseId;

    @ApiModelProperty(value = "所属课程名称", name = "courseName", required = true, example = "高等数学")
    private String courseName;
}
