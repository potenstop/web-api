package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
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
public class ContentServiceImpl implements ContentService {
    private final ContentMapper contentMapper;
    @Override
    public Content byId(Integer contentId) {
        if (contentId == null) {
            throw new ApiException(ContentCode.CONTENT_ID_NOT_FOUND);
        }
        Content content = contentMapper.selectByPrimaryKey(contentId);
        if (content == null) {
            throw new ApiException(ContentCode.CONTENT_ID_NOT_FOUND);
        }
        return content;
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
