package top.potens.cms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.potens.cms.service.WordAnalysisService;
import top.potens.framework.model.ApiResult;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WordAnalysisController
 * @projectName web-api
 * @date 2019/11/26 6:01
 */
@RestController
@RequestMapping("/word")
@Api(description = "word文档解析")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WordAnalysisController {
    private final WordAnalysisService wordAnalysisService;
    @ApiOperation(value = "批量题目解析")
    @PostMapping(value = "/batch/course/topic")
    public ApiResult<String> batchCourseTopic(MultipartFile file) {
        ApiResult<String> result = new ApiResult<>();
        result.setData(wordAnalysisService.batchCourseTopic(file));
        return result;
    }
}
