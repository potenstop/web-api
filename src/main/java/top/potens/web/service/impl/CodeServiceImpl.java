package top.potens.web.service.impl;

import org.jruby.embed.ScriptingContainer;
//import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppLogger;
import top.potens.web.code.CommonCode;
import top.potens.web.common.util.CustomStringJavaCompiler;
import top.potens.web.service.CodeService;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

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

        PrintStream out = System.out;
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine nashorn = manager.getEngineByName("nashorn");

        ScriptEngine engine = manager.getEngineByName("ECMAScript");
        //注册脚本
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);
            System.out.println("31111111111111112222");
            nashorn.eval(code);
            String runResult = new String(outputStream.toByteArray(), "utf-8");
            //调用注册函数
            return "11";
        } catch (ScriptException | UnsupportedEncodingException e) {
            AppLogger.error("运行失败", e);
            throw new ApiException(CommonCode.CODE_COMPILER_ERROR);
        } finally {
            //还原默认打印的对象
             System.setOut(out);
        }
    }

    @Override
    public String runJava(String code) {
        CustomStringJavaCompiler compiler = new CustomStringJavaCompiler(code);
        boolean res = compiler.compiler();
        if (res) {
            AppLogger.info("编译成功");
            AppLogger.info("compilerTakeTime：" + compiler.getCompilerTakeTime());
            try {
                compiler.runMainMethod();
                AppLogger.info("runTakeTime：" + compiler.getRunTakeTime());
                return compiler.getRunResult();
            } catch (Exception e) {
                AppLogger.error("运行失败", e);
                throw new ApiException(CommonCode.CODE_COMPILER_ERROR, e.getMessage());
            }
        } else {
            throw new ApiException(CommonCode.CODE_COMPILER_ERROR, compiler.getCompilerMessage());
        }
    }

    @Override
    public String runPython(String code) {

        /*PythonInterpreter interpreter = new PythonInterpreter();
        //注册脚本
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            interpreter.setOut(outputStream);
            interpreter.exec(code);
            return new String(outputStream.toByteArray(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new ApiException(CodeEnums.CODE_COMPILER_ERROR.getCode(), e.getMessage());
        }*/
        return null;
    }

    @Override
    public String runRuby(String code) {
        try {
            ScriptingContainer container = new ScriptingContainer();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            container.setOutput(printStream);
            container.runScriptlet(code);
            return new String(outputStream.toByteArray(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "------";
    }

}
