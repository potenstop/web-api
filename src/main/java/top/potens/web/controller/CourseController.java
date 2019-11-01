package top.potens.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.potens.framework.model.ApiResult;
import top.potens.web.request.CourseAddRequest;
import top.potens.web.response.CourseListItemResponse;
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
@Api(description = "课程专辑操作接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/no-page/list")
    @ApiOperation("查询课程列表 不分页")
    public ApiResult<List<CourseListItemResponse>> listByFilterNotPage(
            @ApiParam(value = "courseId", example = "1") @RequestParam(required = false) Integer courseId,
            @ApiParam(value = "courseName", example = "1") @RequestParam(required = false) String courseName,
            @ApiParam(value = "courseStairId", example = "1") @RequestParam(required = false) Integer courseStairId,
            @ApiParam(value = "courseSecondId", example = "1") @RequestParam(required = false) Integer courseSecondId,
            @ApiParam(value = "courseThreeId", example = "1") @RequestParam(required = false) Integer courseThreeId
    ) {
        ApiResult<List<CourseListItemResponse>> result = new ApiResult<>();
        List<CourseListItemResponse> courseListItemResponses = courseService.selectListByFilterNotPage(courseId, courseName, courseStairId, courseSecondId, courseThreeId);
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

}
