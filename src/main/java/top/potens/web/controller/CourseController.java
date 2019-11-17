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
import top.potens.web.request.CourseAddRequest;
import top.potens.web.request.CourseListItemRequest;
import top.potens.web.request.CourseUpdateRequest;
import top.potens.web.response.CourseListItemResponse;
import top.potens.web.response.CourseViewResponse;
import top.potens.web.service.CourseService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseController
 * @projectName web-api
 * @date 2019/10/28 6:45
 */
@RestController
@RequestMapping("/course")
@Api(description = "课程操作接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/no-page/list")
    @ApiOperation("查询课程列表 不分页")
    public ApiResult<List<CourseListItemResponse>> listByFilterNotPage(
            @ApiParam(value = "课程id", example = "1") @RequestParam(required = false) Integer courseId,
            @ApiParam(value = "课程名称 模糊搜索", example = "1") @RequestParam(required = false) String courseName,
            @ApiParam(value = "课程代号", example = "1") @RequestParam(required = false) String courseCode,
            @ApiParam(value = "类型一级id", example = "1") @RequestParam(required = false) Integer courseStairId,
            @ApiParam(value = "类型二级id", example = "1") @RequestParam(required = false) Integer courseSecondId,
            @ApiParam(value = "类型三级id", example = "1") @RequestParam(required = false) Integer courseThreeId
    ) {
        ApiResult<List<CourseListItemResponse>> result = new ApiResult<>();
        List<CourseListItemResponse> courseListItemResponses = courseService.selectListByFilterNotPage(courseId, courseName, courseCode, courseStairId, courseSecondId, courseThreeId);
        result.setData(courseListItemResponses);
        return result;
    }

    @PostMapping("/add")
    @ApiOperation("创建一个课程记录")
    public ApiResult<Integer> add(@RequestBody @Valid CourseAddRequest request) {
        ApiResult<Integer> result = new ApiResult<>();
        result.setData(courseService.insertOne(request));
        return result;
    }
    @PostMapping("/list")
    @ApiOperation("查询课程列表 分页")
    public ApiResult<PageResponse<CourseListItemResponse>> list(@RequestBody @Valid CourseListItemRequest request){
        ApiResult<PageResponse<CourseListItemResponse>> result = new ApiResult<>();
        result.setData(courseService.selectList(request));
        return result;
    }
    @GetMapping("/view")
    @ApiOperation("查看一个课程记录")
    public ApiResult<CourseViewResponse> view(@ApiParam(value = "课程id", example = "1") @RequestParam @NotNull Integer courseId) {
        ApiResult<CourseViewResponse> result = new ApiResult<>();
        result.setData(courseService.selectById(courseId));
        return result;
    }
    @PostMapping("/update")
    @ApiOperation("更新一个课程记录")
    public ApiResult<Integer> update(@RequestBody @Valid CourseUpdateRequest request) {
        ApiResult<Integer> result = new ApiResult<>();
        result.setData(courseService.updateById(request));
        return result;
    }
}
