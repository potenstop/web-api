package top.potens.web.service.impl;

import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppUtil;
import top.potens.web.common.enums.CodeEnums;
import top.potens.web.common.util.CustomStringJavaCompiler;
import top.potens.web.service.CodeService;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CodeServiceImpl
 * @projectName web-api
 * @date 2019/6/27 17:56
 */
@Service
public class CodeServiceImpl implements CodeService {
    @Override
    public String runNodeJs(String code) {
        return null;
    }

    @Override
    public String runJava(String code) {
        CustomStringJavaCompiler compiler = new CustomStringJavaCompiler(code);
        boolean res = compiler.compiler();
        if (res) {
            AppUtil.info("编译成功");
            AppUtil.info("compilerTakeTime：" + compiler.getCompilerTakeTime());
            try {
                compiler.runMainMethod();
                AppUtil.info("runTakeTime：" + compiler.getRunTakeTime());
                return compiler.getRunResult();
            } catch (Exception e) {
                AppUtil.error("运行失败", e);
                throw new ApiException(CodeEnums.CODE_COMPILER_ERROR.getCode(), e.getMessage());
            }
        } else {
            throw new ApiException(CodeEnums.CODE_COMPILER_ERROR.getCode(), compiler.getCompilerMessage());
        }
    }
}
