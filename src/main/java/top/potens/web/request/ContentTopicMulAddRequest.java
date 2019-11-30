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
public class ContentTopicMulAddRequest {
    @ApiModelProperty(value = "数据列表", name = "contentTopicAddRequestList", required = true)
    @Valid
    @Size(min = 1)
    @NotNull
    private List<ContentTopicAddRequest> contentTopicAddRequestList;
}
