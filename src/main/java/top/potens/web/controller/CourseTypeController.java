package top.potens.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.potens.framework.model.ApiResult;
import top.potens.web.response.CourseListItemResponse;
import top.potens.web.response.CourseTypeTreeItemResponse;
import top.potens.web.service.CourseTypeService;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseTypeController
 * @projectName web-api
 * @date 2019/11/1 11:10
 */
@RestController
@RequestMapping("/course-type")
@Api(description = "课程类型操作接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class CourseTypeController {
    private final CourseTypeService courseTypeService;
    @GetMapping("/no-page/list")
    @ApiOperation("查询课程分类列表 不分页")
    public ApiResult<List<CourseListItemResponse>> listByFilterNotPage() {
        return null;
    }

    @GetMapping("/no-page/tree")
    @ApiOperation("查询课程分类树形 不分页")
    public ApiResult<List<CourseTypeTreeItemResponse>> treeByFilterNotPage() {
        ApiResult<List<CourseTypeTreeItemResponse>> result = new ApiResult<>();
        result.setData(courseTypeService.treeByFilterNotPage());
        return result;
    }
}
