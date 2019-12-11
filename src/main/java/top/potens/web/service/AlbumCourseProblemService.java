package top.potens.web.service;

import top.potens.framework.service.TableCommonService;
import top.potens.web.model.AlbumCourseProblem;
import top.potens.web.request.AlbumCourseProblemAddRequest;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentCourseService
 * @projectName web-api
 * @date 2019/10/25 14:22
 */
public interface AlbumCourseProblemService extends TableCommonService<AlbumCourseProblem> {
    /**
     *
     * 方法功能描述: 插入一个答题记录
     *
     * @author yanshaowen
     * @date 2019/10/22 10:35
     * @param request    request
     * @return
     * @throws
     */
    Integer insertOne(AlbumCourseProblemAddRequest request, Integer userId);
}
