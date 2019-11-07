package top.potens.web.code;

import top.potens.framework.annotation.ResultMessage;

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

    @ResultMessage("内容没有找到")
    public static final String CONTENT_ID_NOT_FOUND = "30002";

    @ResultMessage("题目类型错误")
    public static final String CONTENT_TOPIC_TYPE_ERROR = "30003";

    @ResultMessage("内容状态错误")
    public static final String CONTENT_STATE_ERROR = "30004";

    @ResultMessage("内容状态错误")
    public static final String CONTENT_SELECT_NOT_OPTION = "30005";

    @ResultMessage("选项id没有传")
    public static final String CONTENT_SELECT_OPTION_ID_NOT_PARAMS = "30006";

    @ResultMessage("选项id没有找到")
    public static final String CONTENT_SELECT_OPTION_ID_NOT_FOUND = "30007";
}
