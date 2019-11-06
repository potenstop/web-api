package top.potens.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentTopicListItemResponse
 * @projectName web-api
 * @date 2019/11/6 11:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContentTopicListItemResponse extends ContentResponse {
    @ApiModelProperty(value = "id", name = "contentTopicId", required = true, example = "")
    private Integer contentTopicId;

    @ApiModelProperty(value = "题目描述", name = "title", required = true, example = "")
    private String title;

    @ApiModelProperty(value = "内容id", name = "contentId", required = true, example = "")
    private Integer contentId;

    @ApiModelProperty(value = "答案", name = "answer", required = true, example = "")
    private String answer;

    @ApiModelProperty(value = "解析描述", name = "analysis", required = true, example = "")
    private String analysis;

    @ApiModelProperty(value = "题目类型 1: 选择题 2 填空题 3 简答题", name = "topicType", required = true, example = "1")
    private Integer topicType;
}
