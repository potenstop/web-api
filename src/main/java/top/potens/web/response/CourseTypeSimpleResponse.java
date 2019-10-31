package top.potens.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseTypeSimpleResponse
 * @projectName web-api
 * @date 2019/10/31 14:10
 */
@Data
public class CourseTypeSimpleResponse {
    @ApiModelProperty(value = "分类id", name = "courseTypeId", required = true, example = "1")
    private Integer courseTypeId;

    @ApiModelProperty(value = "分类名称", name = "typeName", required = true, example = "名称")
    private String typeName;
}
