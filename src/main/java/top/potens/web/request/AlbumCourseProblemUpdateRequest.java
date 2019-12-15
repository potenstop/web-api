package top.potens.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumCourseAddRequest
 * @projectName web-api
 * @date 2019/10/27 17:36
 */
@Data
public class AlbumCourseProblemUpdateRequest {
    @ApiModelProperty(value = "答卷id", name = "albumCourseProblemId", required = true, example = "")
    @NotNull
    private Integer albumCourseProblemId;

    @Valid
    private List<ProblemContentTopic> problemContentTopicList;

    @Data
    public static class ProblemContentTopic {
        @ApiModelProperty(value = "作答的答案 选择题为选项的id 问答题为输入的内容", name = "chooseValue", required = true, example = "")
        @NotBlank
        private String chooseValue;

        @ApiModelProperty(value = "题目id", name = "chooseValue", required = true, example = "")
        @NotNull
        private Integer contentId;
    }

}
