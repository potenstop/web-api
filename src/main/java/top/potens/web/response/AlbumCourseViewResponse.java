package top.potens.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumCourseViewResponse
 * @projectName web-api
 * @date 2019/11/5 11:52
 */
@Data
public class AlbumCourseViewResponse {
    @ApiModelProperty(value = "专辑id", name = "albumId", required = true, example = "1")
    private Integer albumId;

    @ApiModelProperty(value = "专辑名称", name = "albumName", required = true, example = "")
    private String albumName;

    @ApiModelProperty(value = "专辑描述", name = "albumDesc", required = true, example = "")
    private String albumDesc;

    @ApiModelProperty(value = "课程id", name = "courseId", required = true, example = "1")
    private Integer courseId;

    @ApiModelProperty(value = "课程名称", name = "courseName", required = true, example = "")
    private String courseName;
}
