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
}
