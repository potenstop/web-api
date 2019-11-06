package top.potens.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.potens.framework.model.PageRequest;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentTopicListItemRequest
 * @projectName web-api
 * @date 2019/11/6 11:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContentTopicListItemRequest extends PageRequest {
    @ApiModelProperty(value = "id", name = "contentId", required = true, example = "1")
    private Integer contentId;

    @ApiModelProperty(value = "创建时间范围搜索", example = "2019-09-01,2019-09-10")
    private String createTime;

    @ApiModelProperty(value = "状态 1 展示 2 不展示", name = "state", required = true, example = "1")
    private Integer state;

    @ApiModelProperty(value = "标题", name = "title", required = true, example = "1")
    private String title;
}
