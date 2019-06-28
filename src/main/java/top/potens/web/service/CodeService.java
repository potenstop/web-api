package top.potens.web.service;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CodeService
 * @projectName web-api
 * @date: 2019/6/27 17:41
 */
public interface CodeService {
    /**
    *
    * 方法功能描述: 运行nodejs代码
    *
    * @author yanshaowen
    * @date 2019/6/27 18:10
    * @param code   代码
    * @return       执行结果
    */
    String runNodeJs(String code);
    /**
     *
     * 方法功能描述: 运行java代码
     *
     * @author yanshaowen
     * @date 2019/6/27 18:10
     * @param code      代码
     * @return          执行结果
     */
    String runJava(String code);
}
