package top.potens.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.potens.framework.annotation.UserAuthToken;
import top.potens.framework.log.AppLogger;
import top.potens.framework.model.ApiResult;
import top.potens.framework.model.PageResponse;
import top.potens.framework.serialization.JSON;
import top.potens.web.request.ContentNewsListRequest;
import top.potens.web.request.ContentNewsOutRequest;
import top.potens.web.response.ContentNewItemResponse;
import top.potens.web.service.ContentNewsService;

import javax.validation.Valid;

/**
 * Created by wenshao on 2019/7/20.
 */
@RestController
@RequestMapping("/content-news")
@Api(description = "新闻操作接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class ContentNewsController {

    private final ContentNewsService contentNewsService;

    @PostMapping("/push")
    public ApiResult<Boolean> outPush(@RequestBody @Valid ContentNewsOutRequest request) {
        AppLogger.info("controller-start-request request:[{}]", JSON.toJSONString(request));
        ApiResult<Boolean> result = new ApiResult<>();
        contentNewsService.outPush(request);
        result.setData(true);
        AppLogger.info("controller-end-request response:[{}]", true);
        return result;
    }

    @PostMapping("/list")
    @UserAuthToken
    public ApiResult<PageResponse<ContentNewItemResponse>> list(@RequestBody @Valid ContentNewsListRequest request) {
        ApiResult<PageResponse<ContentNewItemResponse>> result = new ApiResult<>();
        result.setData(contentNewsService.list(request));
        return result;
    }

}
