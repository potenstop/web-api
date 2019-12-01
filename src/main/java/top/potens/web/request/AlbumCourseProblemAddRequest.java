package top.potens.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumCourseAddRequest
 * @projectName web-api
 * @date 2019/10/27 17:36
 */
@Data
public class AlbumCourseProblemAddRequest {
    @ApiModelProperty(value = "专辑id", name = "albumId", required = true, example = "")
    private Integer albumId;
}
