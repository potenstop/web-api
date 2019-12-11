package top.potens.web.service;

import top.potens.framework.service.TableCommonService;
import top.potens.web.model.ContentComment;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentCommentService
 * @projectName web-api
 * @date 2019/11/5 12:05
 */
public interface ContentCommentService extends TableCommonService<ContentComment> {
    public void insert();
}
