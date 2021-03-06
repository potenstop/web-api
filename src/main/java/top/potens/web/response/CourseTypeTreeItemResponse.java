package top.potens.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.potens.framework.annotation.JsonFormatDatetime;

import java.util.Date;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseTypeListItemResponse
 * @projectName web-api
 * @date 2019/11/1 11:14
 */
@ApiModel(description = "课程分类列表对象")
@Data
public class CourseTypeTreeItemResponse {
    @ApiModelProperty(value = "课程分类id主键", name = "courseTypeId", required = true, example = "")
    private Integer courseTypeId;

    @ApiModelProperty(value = "创建时间", name = "createTime", required = true, example = "2019-01-01 00:0:00.0")
    @JsonFormatDatetime
    private Date createTime;

    @ApiModelProperty(value = "更新时间", name = "updateTime", required = true, example = "2019-01-01 00:0:00.0")
    @JsonFormatDatetime
    private Date updateTime;

    @ApiModelProperty(value = "名称", name = "typeName", required = true, example = "")
    private String typeName;

    @ApiModelProperty(value = "级别  1: 一级分类 2:  二级分类 3: 三级分类", name = "rank", required = true, example = "1")
    private Integer rank;

    @ApiModelProperty(value = "父级id", name = "parentId", required = true, example = "0")
    private Integer parentId;

    @ApiModelProperty(value = "序号  越小越往前", name = "sequence", required = true, example = "1")
    private Integer sequence;

    @ApiModelProperty(value = "下级列表", name = "childList", required = true)
    private List<CourseTypeTreeItemResponse> childList;
}
