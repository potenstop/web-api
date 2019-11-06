package top.potens.web.service;

import top.potens.web.model.Content;

import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentService
 * @projectName web-api
 * @date 2019/11/6 14:49
 */
public interface ContentService {
    /**
    *
    * 方法功能描述: 按单个内容id获取单个对象
    *
    * @author yanshaowen
    * @date 2019/11/6 14:52
    * @param contentId
    * @return
    * @throws
    */
    Content byId(Integer contentId);

    /**
    *
    * 方法功能描述: 按多个内容id获取多个对象
    *
    * @author yanshaowen
    * @date 2019/11/6 14:53
    * @param contentIdList
    * @return
    * @throws
    */
    List<Content> byIdList(List<Integer> contentIdList);

    /**
     *
     * 方法功能描述: 按多个内容id获取多个对象
     *
     * @author yanshaowen
     * @date 2019/11/6 14:53
     * @param contentIdList
     * @return
     * @throws
     */
    Map<Integer, Content> byIdListResultMap(List<Integer> contentIdList);
}
