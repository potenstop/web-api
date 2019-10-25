package top.potens.web.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumResponse
 * @projectName web-api
 * @date 2019/10/22 10:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AlbumCourseListItemResponse extends AlbumResponse {
    private String courseTypeNames;
    private Long contentCount;

}
