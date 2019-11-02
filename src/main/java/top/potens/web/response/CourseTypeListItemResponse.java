package top.potens.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

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
public class CourseTypeListItemResponse {
    @ApiModelProperty(value = "课程分类id主键", name = "courseTypeId", required = true, example = "")
    private Integer courseTypeId;

    @ApiModelProperty(value = "创建时间", name = "createTime", required = true, example = "2019-01-01 00:0:00.0")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", name = "updateTime", required = true, example = "2019-01-01 00:0:00.0")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "名称", name = "typeName", required = true, example = "")
    private String typeName;

    @ApiModelProperty(value = "代码", name = "typeCode", required = true, example = "")
    private String typeCode;

    @ApiModelProperty(value = "级别  1: 一级分类 2:  二级分类 3: 三级分类", name = "rank", required = true, example = "1")
    private Integer rank;

    @ApiModelProperty(value = "父级id", name = "parentId", required = true, example = "0")
    private Integer parentId;

    @ApiModelProperty(value = "序号  越小越往前", name = "sequence", required = true, example = "1")
    private Integer sequence;
}
