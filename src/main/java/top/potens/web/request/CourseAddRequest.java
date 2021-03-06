package top.potens.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseAddRequest
 * @projectName web-api
 * @date 2019/11/1 11:00
 */
@ApiModel("添加单个课程")
@Data
public class CourseAddRequest {
    @ApiModelProperty(value = "课程名称", name = "courseName", required = true, example = "")
    @NotBlank
    @Size(max = 255)
    private String courseName;

    @ApiModelProperty(value = "课程代码", name = "courseCode", required = true, example = "")
    @NotBlank
    @Size(max = 50)
    private String courseCode;

    @ApiModelProperty(value = "一级分类id", name = "courseStairId", required = true, example = "0")
    @NotNull
    private Integer courseStairId;

    @ApiModelProperty(value = "二级分类id", name = "courseSecondId", required = true, example = "0")
    @NotNull
    private Integer courseSecondId;

    @ApiModelProperty(value = "三级分类id列表", name = "courseThreeIdList", required = true)
    @NotNull
    @Size(min = 1)
    private List<Integer> courseThreeIdList;
}
