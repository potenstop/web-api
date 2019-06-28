package top.potens.web.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CodeRunRequest
 * @projectName web-api
 * @date 2019/6/27 17:44
 */
public class CodeRunRequest {
    @NotBlank
    @ApiModelProperty(value = "代码类型", example = "java")
    private String language;

    @NotBlank
    @ApiModelProperty(value = "代码", example = "console.log(1);")
    private String code;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
