package top.potens.cms.controller;

import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.potens.cms.response.TokenResponse;
import top.potens.cms.service.QiniuService;
import top.potens.framework.model.ApiResult;

import javax.validation.Valid;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className QiniuController
 * @projectName web-api
 * @date 2019/9/25 19:41
 */
@RestController
@RequestMapping("/qiniu")
@Api(description = "七牛相关接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QiniuController {
    private final QiniuService qiniuService;

    @GetMapping("/token")
    @ApiOperation(value = "获取token")
    public ApiResult<String> token() {
        ApiResult<String> result = new ApiResult<>();
        result.setData(qiniuService.token());
        return result;
    }
}
