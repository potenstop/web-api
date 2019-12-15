package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.service.impl.AbstractSimpleTableCommonServiceImpl;
import top.potens.web.dao.db.auto.ContentTopicSelectOptionMapper;
import top.potens.web.model.ContentTopicSelectOption;
import top.potens.web.model.ContentTopicSelectOptionExample;
import top.potens.web.service.ContentTopicSelectOptionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className contentTopicSelectOptionServiceImpl
 * @projectName web-api
 * @date 2019/12/14 15:36
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentTopicSelectOptionServiceImpl extends AbstractSimpleTableCommonServiceImpl<ContentTopicSelectOption> implements ContentTopicSelectOptionService {
    private final ContentTopicSelectOptionMapper contentTopicSelectOptionMapper;
    @Override
    protected ContentTopicSelectOption mapperByPrimaryKey(Integer id) {
        return contentTopicSelectOptionMapper.selectByPrimaryKey(id);
    }

    @Override
    protected ContentTopicSelectOption mapperBySecondPrimaryKey(Integer id) {
        return null;
    }

    @Override
    protected Boolean isDelete(ContentTopicSelectOption contentTopicSelectOption) {
        return false;
    }

    @Override
    public List<ContentTopicSelectOption> selectListByContentId(List<Integer> contentIdList) {
        if (CollectionUtils.isEmpty(contentIdList)) {
            return new ArrayList<>();
        }
        ContentTopicSelectOptionExample contentTopicSelectOptionExample = new ContentTopicSelectOptionExample();
        contentTopicSelectOptionExample.createCriteria().andContentIdIn(contentIdList);
        return contentTopicSelectOptionMapper.selectByExample(contentTopicSelectOptionExample);
    }

    @Override
    public Map<Integer, List<ContentTopicSelectOption>> selectMapByContentId(List<Integer> contentIdList) {
        List<ContentTopicSelectOption> contentTopicSelectOptions = selectListByContentId(contentIdList);
        return contentTopicSelectOptions.stream().collect(Collectors.groupingBy(ContentTopicSelectOption::getContentId));
    }
}
