package top.potens.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.potens.framework.model.ApiResult;
import top.potens.framework.model.PageResponse;
import top.potens.web.request.AlbumCourseAddRequest;
import top.potens.web.request.AlbumCourseListItemRequest;
import top.potens.web.request.AlbumCourseUpdateCourseRelationRequest;
import top.potens.web.request.AlbumCourseUpdateRequest;
import top.potens.web.response.AlbumCourseListItemResponse;
import top.potens.web.response.AlbumCourseViewResponse;
import top.potens.web.service.AlbumCourseService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    public ApiResult<PageResponse<AlbumCourseListItemResponse>> albumCourseList(@RequestBody @Valid AlbumCourseListItemRequest request) {
        ApiResult<PageResponse<AlbumCourseListItemResponse>> result = new ApiResult<>();
        PageResponse<AlbumCourseListItemResponse> response = albumCourseService.selectList(request);
        result.setData(response);
        return result;
    }
    @PostMapping("/add")
    @ApiOperation("添加课程专辑")
    public ApiResult<Integer> albumCourseAdd(@RequestBody @Valid AlbumCourseAddRequest request) {
        ApiResult<Integer> result = new ApiResult<>();
        result.setData(albumCourseService.insertOne(request));
        return result;
    }
    @GetMapping("/view")
    @ApiOperation("查看课程专辑")
    public ApiResult<AlbumCourseViewResponse> albumCourseView(@ApiParam(value = "专辑id", example = "1") @RequestParam @NotNull Integer albumId) {
        ApiResult<AlbumCourseViewResponse> result = new ApiResult<>();
        result.setData(albumCourseService.viewById(albumId));
        return result;
    }
    @PostMapping("/update")
    @ApiOperation("更新课程专辑")
    public ApiResult<Integer> albumCourseUpdate(@RequestBody @Valid AlbumCourseUpdateRequest request) {
        ApiResult<Integer> result = new ApiResult<>();
        result.setData(albumCourseService.updateById(request));
        return result;
    }
    @PostMapping("/update-course-relation")
    @ApiOperation("专辑绑定课程")
    public ApiResult<Integer> albumCourseUpdateCourseRelation(@RequestBody @Valid AlbumCourseUpdateCourseRelationRequest request) {
        ApiResult<Integer> result = new ApiResult<>();
        result.setData(albumCourseService.updateCourseRelationById(request));
        return result;
    }
}
