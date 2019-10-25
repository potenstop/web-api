package top.potens.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.potens.framework.model.ApiResult;
import top.potens.framework.model.PageResponse;
import top.potens.web.request.AlbumCourseListItemRequest;
import top.potens.web.response.AlbumCourseListItemResponse;
import top.potens.web.service.AlbumCourseService;

import javax.validation.Valid;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumController
 * @projectName web-api
 * @date 2019/10/22 9:59
 */
@RestController
@RequestMapping("/album/course")
@Api(description = "课程专辑操作接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class AlbumCourseController {
    private final AlbumCourseService albumCourseService;

    @PostMapping("/list")
    @ApiOperation("查询课程专辑列表")
    public ApiResult<PageResponse<AlbumCourseListItemResponse>> courseList(@RequestBody @Valid AlbumCourseListItemRequest request) {
        ApiResult<PageResponse<AlbumCourseListItemResponse>> result = new ApiResult<>();
        PageResponse<AlbumCourseListItemResponse> response = albumCourseService.selectCourseList(request);
        result.setData(response);
        return result;
    }
}
