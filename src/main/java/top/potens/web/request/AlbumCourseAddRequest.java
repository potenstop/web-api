package top.potens.web.request;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumCourseAddRequest
 * @projectName web-api
 * @date 2019/10/27 17:36
 */
@Data
public class AlbumCourseAddRequest {
    @ApiModelProperty(value = "专辑名称", name = "albumName", required = true, example = "")
    @NotNull
    @Size(max = 255)
    private String albumName;

    @ApiModelProperty(value = "专辑描述", name = "albumDesc", required = true, example = "")
    @Size(max = 2000)
    private String albumDesc;

    @ApiModelProperty(value = "课程id", name = "courseId", required = true, example = "")
    @NotNull
    private Integer courseId;
}
