package top.potens.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.potens.framework.log.AppLogger;
import top.potens.framework.model.ApiResult;
import top.potens.framework.serialization.JSON;
import top.potens.web.request.ContentNewsOutRequest;
import top.potens.web.service.ContentNewsService;

/**
 * Created by wenshao on 2019/7/20.
 */
@RestController
@RequestMapping("/content-news")
@Api(description = "新闻操作接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentNewsController {
    private final ContentNewsService contentNewsService;
    @PostMapping("/push")
    public ApiResult<Boolean> outPush(@RequestBody ContentNewsOutRequest request) {
        AppLogger.info("controller-start-request request:[{}]", JSON.toJSONString(request));
        ApiResult<Boolean> result = new ApiResult<>();
        contentNewsService.outPush(request);
        result.setData(true);
        AppLogger.info("controller-end-request response:[{}]", true);
        return result;
    }

}
