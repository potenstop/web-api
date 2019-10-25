package top.potens.web.dao.db.ext;

import top.potens.web.model.ContentLabel;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentLabelExMapper
 * @projectName web-api
 * @date 2019/10/22 14:56
 */
public interface ContentLabelExMapper {
    public void insertOrUpdate(ContentLabel record);
}
