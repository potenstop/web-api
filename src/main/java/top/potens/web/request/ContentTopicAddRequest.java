package top.potens.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentTopicAddRequest
 * @projectName web-api
 * @date 2019/11/6 11:27
 */
@Data
public class ContentTopicAddRequest {
    @ApiModelProperty(value = "状态 1 展示 2 不展示", name = "state", required = true, example = "1")
    @NotNull
    private Integer state;

    @ApiModelProperty(value = "题目类型 1: 单选选择题 2 多选选择题 3 填空题 4 简答题", name = "topicType", required = true, example = "1")
    @NotNull
    private Integer topicType;

    @ApiModelProperty(value = "题目描述", name = "title", required = true, example = "")
    @NotBlank
    @Size(max = 2000)
    private String title;

    @ApiModelProperty(value = "答案", name = "answer", required = true, example = "")
    @NotBlank
    @Size(max = 5000)
    private String answer;

    @ApiModelProperty(value = "解析描述", name = "analysis", required = true, example = "")
    @NotBlank
    @Size(max = 2000)
    private String analysis;

    @ApiModelProperty(value = "新增的选项列表", name = "addOptionList", required = false)
    @Valid
    private List<ContentTopicSelectOptionRequest> addOptionList;
}
