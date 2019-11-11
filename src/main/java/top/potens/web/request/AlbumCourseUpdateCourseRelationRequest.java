package top.potens.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class AlbumCourseUpdateCourseRelationRequest {
    @ApiModelProperty(value = "专辑id", name = "albumId", required = true, example = "1")
    @NotNull
    private Integer albumId;

    @ApiModelProperty(value = "专辑绑定的id列表", name = "contentIdList", required = false)
    private List<Integer> contentIdList;
}
