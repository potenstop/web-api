package top.potens.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.potens.web.request.ContentTopicSelectOptionRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentTopicViewResponse
 * @projectName web-api
 * @date 2019/11/6 11:29
 */
@Data
public class ContentTopicViewResponse {
    @ApiModelProperty(value = "内容id", name = "contentId", required = true, example = "1")
    private Integer contentId;

    @ApiModelProperty(value = "题目id 不对外展示", name = "contentTopicId", required = true, example = "1")
    private Integer contentTopicId;

    @ApiModelProperty(value = "状态 1 展示 2 不展示", name = "state", required = true, example = "1")
    private Integer state;

    @ApiModelProperty(value = "题目类型 2:select选择题 3:表述题", name = "contentType", required = true, example = "1")
    private Integer contentType;

    @ApiModelProperty(value = "题目描述", name = "title", required = true, example = "")
    private String title;

    @ApiModelProperty(value = "答案", name = "answer", required = true, example = "")
    private String answer;

    @ApiModelProperty(value = "解析描述", name = "analysis", required = true, example = "")
    private String analysis;

    @ApiModelProperty(value = "题目类型 1: 选择题 2 填空题 3 简答题", name = "topicType", required = true, example = "1")
    private Integer topicType;

    @ApiModelProperty(value = "新增的选项列表", name = "addOptionList", required = false)
    private List<ContentTopicSelectOptionResponse> addOptionList;
}
