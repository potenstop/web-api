package top.potens.cms.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className TopicResponse
 * @projectName web-api
 * @date 2019/11/26 16:39
 */
@Data
public class TopicResponse {
    @ApiModelProperty(value = "题目标题", name = "title", required = true, example = "1")
    private String title;
    @ApiModelProperty(value = "题目类型 1: 单选 2 多选 3 填空题 4 简答题", name = "topicType", required = true, example = "1")
    private Integer topicType;
    @ApiModelProperty(value = "选项列表", name = "optionList", required = true)
    private List<String> optionList;
}
