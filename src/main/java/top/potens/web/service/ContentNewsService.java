package top.potens.web.service;

import top.potens.framework.model.PageResponse;
import top.potens.web.model.Content;
import top.potens.web.model.ContentNews;
import top.potens.web.request.ContentNewsListRequest;
import top.potens.web.request.ContentNewsOutRequest;
import top.potens.web.response.ContentNewItemResponse;

/**
 * Created by wenshao on 2019/7/20.
 */
public interface ContentNewsService {

    /**
    *
    * 方法功能描述: 接受外部新闻
    *
    * @author yanshaowen
    * @date 2019/9/25 12:40
    * @param request
    * @return
    * @throws
    */
    void outPush(ContentNewsOutRequest request);

    /**
    *
    * 方法功能描述: 获取新闻列表
    *
    * @author yanshaowen
    * @date 2019/9/25 12:41
    * @param request
    * @return
    * @throws
    */
    PageResponse<ContentNewItemResponse> list(ContentNewsListRequest request);
}
