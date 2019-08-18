package top.potens.web.code;

import top.potens.framework.response.ResultMessage;

/**
 * 功能描述: 内容code码  30000-39999
 *
 * @author yanshaowen
 * @className ContentCode
 * @projectName web-api
 * @date 2019/8/18 13:20
 */
public class ContentCode {
    @ResultMessage("内容已经存在")
    public static final String CONTENT_EXIST = "30000";

    @ResultMessage("分区没有找到")
    public static final String CONTENT_ZONE_NOT_FOUND = "30001";
}
