package top.potens.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentSelectController
 * @projectName web-api
 * @date 2019/10/22 9:57
 */
@RestController
@RequestMapping("/content-select")
@Api(description = "选择题目操作接口")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class ContentSelectController {
}
