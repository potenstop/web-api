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
 * @className ContentTopicUpdateRequest
 * @projectName web-api
 * @date 2019/11/6 11:30
 */
@Data
public class ContentTopicUpdateRequest {
    @ApiModelProperty(value = "内容id", name = "contentId", required = true, example = "1")
    @NotNull
    private Integer contentId;

    @ApiModelProperty(value = "状态 1 展示 2 不展示", name = "state", required = true, example = "1")
    @NotNull
    private Integer state;

    @ApiModelProperty(value = "题目描述", name = "title", required = true, example = "")
    @NotBlank
    @Size(max = 2000)
    private String title;

    @ApiModelProperty(value = "答案", name = "answer", required = true, example = "")
    @NotBlank
    @Size(max = 5000)
    private String answer;

    @ApiModelProperty(value = "解析描述", name = "analysis", required = true, example = "")
    @NotBlank
    @Size(max = 2000)
    private String analysis;

    @ApiModelProperty(value = "新增的选项列表", name = "addOptionList", required = false)
    @Valid
    private List<ContentTopicSelectOptionRequest> addOptionList;

    @ApiModelProperty(value = "删除的id列表", name = "removeOptionIdList", required = false)
    @Valid
    private List<Integer> removeOptionIdList;

    @ApiModelProperty(value = "修改的选项列表", name = "modifyOptionList", required = false)
    @Valid
    private List<ContentTopicSelectOptionRequest> modifyOptionList;

}
