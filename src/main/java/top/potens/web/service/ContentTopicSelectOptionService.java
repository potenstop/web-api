package top.potens.web.service;

import top.potens.framework.service.TableCommonService;
import top.potens.web.model.ContentTopicSelectOption;
import top.potens.web.response.ContentTopicSelectOptionResponse;

import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentTopicSelectOptionService
 * @projectName web-api
 * @date 2019/12/14 15:30
 */
public interface ContentTopicSelectOptionService extends TableCommonService<ContentTopicSelectOption> {
    /**
     *
     * 方法功能描述: 根据contentId列表获取选项列表
     *
     * @author yanshaowen
     * @param contentIdList
     * @date 2019/10/22 10:03
     * @return
     * @throws
     */
    List<ContentTopicSelectOption> selectListByContentId(List<Integer> contentIdList);

    /**
     *
     * 方法功能描述: 根据contentId列表获取选项列表
     *
     * @author yanshaowen
     * @param contentIdList
     * @date 2019/10/22 10:03
     * @return
     * @throws
     */
    Map<Integer,List<ContentTopicSelectOption>> selectMapByContentId(List<Integer> contentIdList);
}
