package top.potens.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseTypeRelationResponse
 * @projectName web-api
 * @date 2019/10/28 6:04
 */
@Data
public class CourseTypeRelationResponse {
    @ApiModelProperty(value = "一级分类id", name = "contentCount", required = true, example = "1")
    private Integer courseStairId;

    @ApiModelProperty(value = "一级分类名称", name = "courseStairName", required = true, example = "一级")
    private String courseStairName;

    @ApiModelProperty(value = "二级分类id", name = "courseSecondId", required = true, example = "1")
    private Integer courseSecondId;

    @ApiModelProperty(value = "二级分类名称", name = "courseSecondName", required = true, example = "二级")
    private String courseSecondName;

    @ApiModelProperty(value = "三级分类id", name = "courseThreeId", required = true, example = "1")
    private Integer courseThreeId;

    @ApiModelProperty(value = "三级分类名称", name = "courseThreeName", required = true, example = "三级")
    private String courseThreeName;
}
