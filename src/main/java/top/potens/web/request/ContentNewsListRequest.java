package top.potens.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.potens.framework.model.PageRequest;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentNewsListRequest
 * @projectName web-api
 * @date 2019/9/25 12:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContentNewsListRequest extends PageRequest {
    @ApiModelProperty(value = "主键id", example = "1")
    private Integer contentId;

    @ApiModelProperty(value = "创建时间范围搜索", example = "2019-09-01,2019-09-10")
    private String createTime;

    @ApiModelProperty(value = "更新时间范围搜索", example = "2019-09-01,2019-09-10")
    private String updateTime;

    @ApiModelProperty(value = "新闻标题 模糊搜索", example = "111")
    private String title;

    @ApiModelProperty(value = "发布时间范围搜索", example = "2019-09-01,2019-09-10")
    private String publishTime;

    @ApiModelProperty(value = "状态 1:上线 2: 下线", example = "1")
    private Integer state;
}
