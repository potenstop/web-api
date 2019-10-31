package top.potens.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseListItemResponse
 * @projectName web-api
 * @date 2019/10/28 6:49
 */
@ApiModel(description = "课程对象")
@Data
public class CourseListItemResponse {

    @ApiModelProperty(value = "课程id", name = "courseId", required = true, example = "")
    private Integer courseId;

    @ApiModelProperty(value = "课程名称", name = "courseName", required = true, example = "")
    private String courseName;

    @ApiModelProperty(value = "课程代码", name = "courseCode", required = true, example = "")
    private String courseCode;

    @ApiModelProperty(value = "一级分类id", name = "contentCount", required = true, example = "1")
    private Integer courseStairId;

    @ApiModelProperty(value = "一级分类名称", name = "courseStairName", required = true, example = "一级")
    private String courseStairName;

    @ApiModelProperty(value = "二级分类id", name = "courseSecondId", required = true, example = "1")
    private Integer courseSecondId;

    @ApiModelProperty(value = "二级分类名称", name = "courseSecondName", required = true, example = "二级")
    private String courseSecondName;

    @ApiModelProperty(value = "三级分类对象", name = "courseThreeList", required = true)
    private List<CourseTypeSimpleResponse> courseThreeList;


}
