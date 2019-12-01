package top.potens.web.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.web.request.AlbumCourseProblemAddRequest;
import top.potens.web.service.AlbumCourseProblemService;

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


    @Override
    public Integer insertOne(AlbumCourseProblemAddRequest request, Integer userId) {
        return null;
    }
}
