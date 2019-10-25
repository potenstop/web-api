package top.potens.web.response;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumResponse
 * @projectName web-api
 * @date 2019/10/22 10:22
 */
@Data
public class AlbumResponse {
    @ApiModelProperty(value = "专辑id", name = "albumId", required = true, example = "1")
    private Integer albumId;

    @ApiModelProperty(value = "创建时间", name = "createTime", required = true, example = "2019-01-01 10:10:10.999")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", name = "updateTime", required = true, example = "2019-01-01 10:10:10.999")
    private Date updateTime;

    @ApiModelProperty(value = "专辑名称", name = "albumName", required = true, example = "")
    private String albumName;

    @ApiModelProperty(value = "专辑描述", name = "albumDesc", required = true, example = "")
    private String albumDesc;
}
