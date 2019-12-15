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
import top.potens.web.response.*;
import top.potens.web.service.AlbumCourseProblemService;
import top.potens.web.service.AlbumCourseService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @PostMapping("/list")
    @ApiOperation("列表查询")
    @UserAuthToken
    public ApiResult<PageResponse<AlbumCourseProblemListItemResponse>> albumCourseProblemList(@RequestBody @Valid AlbumCourseProblemListItemRequest request, TokenUser tokenUser) {
        ApiResult<PageResponse<AlbumCourseProblemListItemResponse>> result = new ApiResult<>();
        result.setData(albumCourseProblemService.selectList(request));
        return result;
    }

    @GetMapping("/topic")
    @ApiOperation("查询试卷下已经答题目列表")
    @UserAuthToken
    public ApiResult<List<AlbumCourseProblemTopicResponse>> albumCourseProblemTopicList(
            @ApiParam(value = "试卷id", example = "1") @RequestParam @NotNull Integer albumCourseProblemTopicId,
            TokenUser tokenUser
    ) {
        ApiResult<List<AlbumCourseProblemTopicResponse>> result = new ApiResult<>();
        result.setData(albumCourseProblemService.selectTopicList(albumCourseProblemTopicId));
        return result;
    }
    @PostMapping("/update")
    @ApiOperation("更新试卷的答案")
    @UserAuthToken
    public ApiResult<Integer> albumCourseProblemUpdate(@RequestBody @Valid AlbumCourseProblemUpdateRequest request, TokenUser tokenUser) {
        ApiResult<Integer> result = new ApiResult<>();
        result.setData(albumCourseProblemService.updateTopic(request));
        return result;
    }
}
