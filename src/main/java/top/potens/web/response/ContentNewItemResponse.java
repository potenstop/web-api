package top.potens.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentNewItemResponse
 * @projectName web-api
 * @date 2019/9/25 12:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContentNewItemResponse extends ContentResponse {
    @ApiModelProperty(value = "新闻内的来源描述", name = "articleSource", required = true, example = "")
    private String articleSource;

    @ApiModelProperty(value = "标题", name = "title", required = true, example = "")
    private String title;

    @ApiModelProperty(value = "编辑", name = "editor", required = true, example = "")
    private String editor;

    @ApiModelProperty(value = "发布时间", name = "publishTime", required = true, example = "2019-09-09 01:01:11.009")
    private Date publishTime;

    @ApiModelProperty(value = "频道id ", name = "contentZoneId", required = true, example = "1")
    private Integer contentZoneId;

    @ApiModelProperty(value = "频道名称", name = "contentZoneName", required = true, example = "1")
    private String contentZoneName;

    @ApiModelProperty(value = "对外的id", name = "token", required = true, example = "")
    private String token;

    @ApiModelProperty(value = "新闻内容", name = "article", required = true, example = "")
    private String article;
}
