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
 * @className ContentTopicSelectOptionResponse
 * @projectName web-api
 * @date 2019/11/6 15:31
 */
@Data
public class ContentTopicSelectOptionResponse {
    @ApiModelProperty(value = "", name = "contentTopicSelectOptionId", required = true, example = "0")
    private Integer contentTopicSelectOptionId;

    @ApiModelProperty(value = "创建时间", name = "createTime", required = true, example = "2019-01-01 00:00:00")
    @JsonFormatDatetime
    private Date createTime;

    @ApiModelProperty(value = "更新时间", name = "updateTime", required = true, example = "2019-01-01 00:00:00")
    @JsonFormatDatetime
    private Date updateTime;

    @ApiModelProperty(value = "内容id", name = "contentId", required = true, example = "1")
    private Integer contentId;

    @ApiModelProperty(value = "题号表", name = "contentTopicId", required = true, example = "1")
    private Integer contentTopicId;

    @ApiModelProperty(value = "选项", name = "optionLabel", required = true, example = "")
    private String optionLabel;

    @ApiModelProperty(value = "是否为正确选项 1:是 2:否", name = "isOptionAnswer", required = true, example = "2")
    private Integer isOptionAnswer;
}
