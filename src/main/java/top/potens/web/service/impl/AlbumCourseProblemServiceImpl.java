package top.potens.web.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.web.dao.db.auto.AlbumCourseProblemMapper;
import top.potens.web.model.AlbumCourse;
import top.potens.web.model.AlbumCourseProblem;
import top.potens.web.request.AlbumCourseProblemAddRequest;
import top.potens.web.service.AlbumCourseProblemService;
import top.potens.web.service.AlbumCourseService;

import java.util.Date;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumCourseServiceImpl
 * @projectName web-api
 * @date 2019/10/22 10:02
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlbumCourseProblemServiceImpl implements AlbumCourseProblemService {
    private final AlbumCourseProblemMapper albumCourseProblemMapper;
    private final AlbumCourseService albumCourseService;

    @Override
    public Integer insertOne(AlbumCourseProblemAddRequest request, Integer userId) {
        AlbumCourse albumCourse = albumCourseService.bySecondPrimaryKeyException(request.getAlbumId());
        AlbumCourseProblem albumCourseProblem = new AlbumCourseProblem();
        Date now = new Date();
        albumCourseProblem.setAlbumId(request.getAlbumId());
        albumCourseProblem.setCreateTime(now);
        albumCourseProblem.setUpdateTime(now);
        albumCourseProblem.setUserId(userId);

        // albumCourseProblemMapper.insertSelective()
        return null;
    }
}
