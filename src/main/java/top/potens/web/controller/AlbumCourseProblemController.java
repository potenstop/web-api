package top.potens.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.potens.framework.annotation.UserAuthToken;
import top.potens.framework.model.ApiResult;
import top.potens.framework.model.PageResponse;
import top.potens.framework.model.TokenUser;
import top.potens.web.request.*;
import top.potens.web.response.AlbumCourseListItemResponse;
import top.potens.web.response.AlbumCourseTopicViewResponse;
import top.potens.web.response.AlbumCourseViewResponse;
import top.potens.web.service.AlbumCourseProblemService;
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
@RequestMapping("/album/course/problem")
@Api(description = "课程答题操作接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class AlbumCourseProblemController {
    private final AlbumCourseProblemService albumCourseProblemService;

    @PostMapping("/add")
    @ApiOperation("新增答卷")
    @UserAuthToken
    public ApiResult<Integer> albumCourseProblemAdd(@RequestBody @Valid AlbumCourseProblemAddRequest request, TokenUser tokenUser) {
        ApiResult<Integer> result = new ApiResult<>();
        result.setData(albumCourseProblemService.insertOne(request, tokenUser.getUserId()));
        return result;
    }
}
