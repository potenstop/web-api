package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.service.impl.AbstractSimpleTableCommonServiceImpl;
import top.potens.framework.util.CollectionUtil;
import top.potens.web.code.ContentCode;
import top.potens.web.dao.db.auto.ContentMapper;
import top.potens.web.model.Content;
import top.potens.web.model.ContentExample;
import top.potens.web.service.ContentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentServiceImpl
 * @projectName web-api
 * @date 2019/11/6 14:50
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentServiceImpl extends AbstractSimpleTableCommonServiceImpl<Content> implements ContentService {
    private final ContentMapper contentMapper;

    @Override
    protected Content mapperByPrimaryKey(Integer id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    @Override
    protected Content mapperBySecondPrimaryKey(Integer id) {
        return null;
    }

    @Override
    protected Boolean isDelete(Content content) {
        return false;
    }

    @Override
    public List<Content> byIdList(List<Integer> contentIdList) {
        if (CollectionUtils.isEmpty(contentIdList)) {
            return new ArrayList<>();
        }
        ContentExample contentExample = new ContentExample();
        contentExample.createCriteria().andContentIdIn(contentIdList);
        return contentMapper.selectByExample(contentExample);
    }

    @Override
    public Map<Integer, Content> byIdListResultMap(List<Integer> contentIdList) {
        List<Content> contentList = byIdList(contentIdList);
        return contentList.stream().collect(Collectors.toMap(Content::getContentId, content -> content));
    }
}
