package top.potens.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.potens.framework.model.PageRequest;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseListItemRequest
 * @projectName web-api
 * @date 2019/11/2 6:18
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("课程列表请求")
@Data
public class CourseListItemRequest extends PageRequest {
    @ApiModelProperty(value = "课程id", name = "courseId", required = true, example = "1")
    private Integer courseId;

    @ApiModelProperty(value = "课程名称", name = "courseName", required = true, example = "近现代史")
    private String courseName;

    @ApiModelProperty(value = "课程代码", name = "courseCode", required = true, example = "03708")
    private String courseCode;

    @ApiModelProperty(value = "一级分类id", name = "courseStairId", required = true, example = "1")
    private Integer courseStairId;


    @ApiModelProperty(value = "二级分类id", name = "courseSecondId", required = true, example = "1")
    private Integer courseSecondId;


    @ApiModelProperty(value = "三级分类id", name = "courseThreeId", required = true, example = "1")
    private Integer courseThreeId;

}
