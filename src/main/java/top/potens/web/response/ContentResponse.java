package top.potens.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.potens.framework.annotation.JsonFormatDatetime;

import java.util.Date;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentResponse
 * @projectName web-api
 * @date 2019/9/25 12:36
 */
@Data
public class ContentResponse {
    @ApiModelProperty(value = "主键id", example = "1")
    private Integer contentId = 1;

    @ApiModelProperty(value = "创建时间", name = "createTime", required = true, example = "CURRENT_TIMESTAMP(3)")
    @JsonFormatDatetime
    private Date createTime;

    @ApiModelProperty(value = "更新时间", name = "updateTime", required = true, example = "CURRENT_TIMESTAMP(3)")
    @JsonFormatDatetime
    private Date updateTime;

    @ApiModelProperty(value = "状态 1 展示 2 不展示", name = "state", required = true, example = "")
    private Integer state;

    @ApiModelProperty(value = "外部id", name = "outId", required = true, example = "")
    private String outId;

    @ApiModelProperty(value = "内容来源id", name = "channelId", required = true, example = "1")
    private Integer channelId;

    @ApiModelProperty(value = "内容来源名称", name = "channelName", required = true, example = "1")
    private String channelName;

    @ApiModelProperty(value = "外部链接", name = "outUrl", required = true, example = "")
    private String outUrl;

    @ApiModelProperty(value = "内容类型 1:news新闻 2:select选择题 3:formulation", name = "contentType", required = true, example = "1")
    private Integer contentType;
}
