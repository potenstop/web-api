package top.potens.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppUtil;
import top.potens.framework.model.ApiResult;
import top.potens.framework.serialization.JSON;
import top.potens.web.common.constant.CodeConstant;
import top.potens.web.common.enums.CodeEnums;
import top.potens.web.request.CodeRunRequest;
import top.potens.web.service.CodeService;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CodeController
 * @projectName web-api
 * @date 2019/6/27 17:38
 */
@RestController
@RequestMapping("/code")
@Api(description = "运行代码")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CodeController {
    private final CodeService codeService;

    @PostMapping("/run")
    @ApiOperation(value = "运行代码")
    @Validated
    public ApiResult<String> codeRun(@RequestBody CodeRunRequest request) {
        AppUtil.info("start request:[{}]", JSON.toJSONString(request));
        ApiResult<String> apiResult = new ApiResult<>();
        if (CodeConstant.CodeType.JAVA_SCRIPT.equals(request.getLanguage())) {
            apiResult.setData(codeService.runNodeJs(request.getCode()));
        } else if (CodeConstant.CodeType.JAVA.equals(request.getLanguage())) {
            apiResult.setData(codeService.runJava(request.getCode()));
        } else {
            throw new ApiException(CodeEnums.PARAM_ERROR.getCode(), CodeEnums.PARAM_ERROR.getMsg());
        }
        AppUtil.info("end result[{}]", JSON.toJSONString(apiResult));
        return apiResult;
    }


}
