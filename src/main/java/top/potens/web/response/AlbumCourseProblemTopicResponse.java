package top.potens.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.potens.framework.annotation.JsonFormatDatetime;

import java.math.BigDecimal;
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
public class AlbumCourseProblemTopicResponse {
    @ApiModelProperty(value = "答题记录的题目", name = "albumCourseProblemTopicId", required = true, example = "")
    private Integer albumCourseProblemTopicId;

    @ApiModelProperty(value = "创建时间", name = "createTime", required = true, example = "2019-01-01 11:11:11.0")
    @JsonFormatDatetime
    private Date createTime;

    @ApiModelProperty(value = "更新时间", name = "updateTime", required = true, example = "2019-01-01 11:11:11.0")
    @JsonFormatDatetime
    private Date updateTime;

    @ApiModelProperty(value = "记录id", name = "albumCourseProblemId", required = true, example = "1")
    private Integer albumCourseProblemId;

    @ApiModelProperty(value = "如果是选择则题 则为选项的id 多个以逗号分隔 非选择题为空字符串", name = "inputSelectOption", required = true, example = "")
    private String inputSelectOption;

    @ApiModelProperty(value = "输入的答案 选择题为选项的描述 多选的逗号隔开", name = "inputProblem", required = true, example = "")
    private String inputProblem;

    @ApiModelProperty(value = "状态 1 未答 2 : 正确 3: 错误 4 部分正确", name = "state", required = true, example = "1")
    private Integer state;

    @ApiModelProperty(value = "分数", name = "gradeAmount", required = true, example = "0.00")
    private BigDecimal gradeAmount;
}
