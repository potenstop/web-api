package top.potens.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumCourseAddRequest
 * @projectName web-api
 * @date 2019/10/27 17:36
 */
@Data
public class AlbumCourseUpdateRequest {
    @ApiModelProperty(value = "专辑id", name = "albumId", required = true, example = "1")
    @NotNull
    private Integer albumId;

    @ApiModelProperty(value = "专辑名称", name = "albumName", required = true, example = "")
    @NotNull
    @Size(max = 255)
    private String albumName;

    @ApiModelProperty(value = "专辑描述", name = "albumDesc", required = true, example = "")
    @Size(max = 2000)
    private String albumDesc;

    @ApiModelProperty(value = "课程id", name = "courseId", required = true, example = "1")
    @NotNull
    private Integer courseId;
}
