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
import top.potens.web.request.ContentTopicAddRequest;
import top.potens.web.request.ContentTopicListItemRequest;
import top.potens.web.request.ContentTopicUpdateRequest;
import top.potens.web.response.ContentTopicListItemResponse;
import top.potens.web.response.ContentTopicViewResponse;
import top.potens.web.service.ContentTopicService;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentSelectController
 * @projectName web-api
 * @date 2019/10/22 9:57
 */
@RestController
@RequestMapping("/content-topic")
@Api(description = "题目操作接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class ContentTopicController {
    private final ContentTopicService contentTopicService;

    @PostMapping("/list")
    @ApiOperation("查询题目列表")
    public ApiResult<PageResponse<ContentTopicListItemResponse>> contentTopicList(@RequestBody @Valid ContentTopicListItemRequest request) {
        ApiResult<PageResponse<ContentTopicListItemResponse>> result = new ApiResult<>();
        PageResponse<ContentTopicListItemResponse> response = contentTopicService.selectList(request);
        result.setData(response);
        return result;
    }
    @PostMapping("/add")
    @ApiOperation("添加题目")
    public ApiResult<Integer> contentTopicAdd(@RequestBody @Valid ContentTopicAddRequest request) {
        ApiResult<Integer> result = new ApiResult<>();
        result.setData(contentTopicService.insertOne(request));
        return result;
    }
    @GetMapping("/view")
    @ApiOperation("查看题目")
    public ApiResult<ContentTopicViewResponse> contentTopicView(@ApiParam(value = "内容id", example = "1") @RequestParam @NotNull Integer contentId) {
        ApiResult<ContentTopicViewResponse> result = new ApiResult<>();
        result.setData(contentTopicService.selectById(contentId));
        return result;
    }
    @PostMapping("/update")
    @ApiOperation("更新题目")
    public ApiResult<Integer> contentTopicUpdate(@RequestBody @Valid ContentTopicUpdateRequest request) {
        ApiResult<Integer> result = new ApiResult<>();
        result.setData(contentTopicService.updateById(request));
        return result;
    }
    @PostMapping("/mul-add")
    @ApiOperation("批量添加题目")
    public ApiResult<Integer> contentTopicMulAdd(@RequestBody @Valid List<ContentTopicAddRequest> request) {
        ApiResult<Integer> result = new ApiResult<>();
        result.setData(contentTopicService.insertMultiple(request));
        return result;
    }
}
