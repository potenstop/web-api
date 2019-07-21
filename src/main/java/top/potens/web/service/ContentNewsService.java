package top.potens.web.service;

import top.potens.web.model.Content;
import top.potens.web.model.ContentNews;
import top.potens.web.request.ContentNewsOutRequest;

/**
 * Created by wenshao on 2019/7/20.
 */
public interface ContentNewsService {

    /***
     * 接受外部新闻
     * @param request
     */
    public void outPush(ContentNewsOutRequest request);

}
