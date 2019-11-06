package top.potens.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentTopicSelectOptionRequest
 * @projectName web-api
 * @date 2019/11/6 15:36
 */
@Data
public class ContentTopicSelectOptionRequest {
    @ApiModelProperty(value = "", name = "contentTopicSelectOptionId", required = true, example = "1")
    private Integer contentTopicSelectOptionId;

    @ApiModelProperty(value = "选项", name = "optionLabel", required = true, example = "")
    @Size(max = 255)
    @NotBlank
    private String optionLabel;

    @ApiModelProperty(value = "是否为正确选项 1:是 2:否", name = "isOptionAnswer", required = true, example = "2")
    @NotNull
    private Integer isOptionAnswer;
}
