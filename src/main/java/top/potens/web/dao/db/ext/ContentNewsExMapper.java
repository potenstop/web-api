package top.potens.web.dao.db.ext;

import top.potens.web.bmo.ContentNewsSelectSimpleListBo;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentNewsExMapper
 * @projectName web-api
 * @date 2019/9/25 12:47
 */
public interface ContentNewsExMapper {

    /**
    *
    * 方法功能描述:
    *
    * @author yanshaowen
    * @date 2019/9/25 12:48
    * @param record
    * @return
    * @throws
    */
    List<Integer> selectSimpleList(ContentNewsSelectSimpleListBo record);
}
