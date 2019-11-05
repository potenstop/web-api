package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.model.DateScope;
import top.potens.framework.model.PageResponse;
import top.potens.framework.util.BeanCopierUtil;
import top.potens.framework.util.DateUtil;
import top.potens.framework.util.StringUtil;
import top.potens.web.bmo.CommonIdCountBo;
import top.potens.web.dao.db.auto.AlbumCourseMapper;
import top.potens.web.dao.db.auto.AlbumMapper;
import top.potens.web.dao.db.ext.AlbumContentRelationExMapper;
import top.potens.web.dao.db.ext.AlbumCourseExMapper;
import top.potens.web.model.*;
import top.potens.web.request.AlbumCourseListItemRequest;
import top.potens.web.response.AlbumCourseListItemResponse;
import top.potens.web.service.AlbumCourseService;
import top.potens.web.service.CourseService;
import top.potens.web.service.CourseTypeService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class AlbumCourseServiceImpl implements AlbumCourseService {

    private final AlbumCourseExMapper albumCourseExMapper;
    private final AlbumMapper albumMapper;
    private final AlbumCourseMapper albumCourseMapper;
    private final CourseTypeService courseTypeService;
    private final AlbumContentRelationExMapper albumContentRelationExMapper;
    private final CourseService courseService;

    @Override
    public PageResponse<AlbumCourseListItemResponse> selectCourseList(AlbumCourseListItemRequest request) {
        PageResponse<AlbumCourseListItemResponse> response = new PageResponse<>();
        DateScope dateScope = DateUtil.getDateScope(request.getCreateTime());
        Long count = albumCourseExMapper.selectAlbumCount(request.getAlbumId(), request.getAlbumName(), dateScope.getStartDate(), dateScope.getEndDate());
        Long offset = BigDecimal.valueOf(request.getPageNum()).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(request.getPageSize())).longValue();
        if (StringUtil.isNotBlank(request.getAlbumName())) {
            request.setAlbumName("%" + request.getAlbumName() + "%");
        }
        List<Integer> idList = albumCourseExMapper.selectAlbumIdList(request.getPageSize(), offset, request.getOrderBy(),
                request.getAlbumId(), request.getAlbumName(), dateScope.getStartDate(), dateScope.getEndDate());

        List<AlbumCourseListItemResponse> albumCourseListItemResponseList = new ArrayList<>();
        if (idList.size() > 0){
            // 获取专辑表的信息
            AlbumExample albumExample = new AlbumExample();
            albumExample.createCriteria().andAlbumIdIn(idList);
            List<Album> albums = albumMapper.selectByExample(albumExample);
            albumCourseListItemResponseList = BeanCopierUtil.convert(albums, AlbumCourseListItemResponse.class);
            // 获取课程专辑的信息
            AlbumCourseExample albumCourseExample = new AlbumCourseExample();
            albumCourseExample.createCriteria().andAlbumIdIn(idList);
            Map<Integer, AlbumCourse> albumCourseMap = albumCourseMapper.selectByExample(albumCourseExample).stream().collect(Collectors.toMap(AlbumCourse::getAlbumId, albumCourse -> albumCourse));

            // 获取课程名称和id
            Map<Integer, Course> courseMap = courseService.selectNameByIdList(idList);

            // 获取课程专辑的内容总数
            Map<Integer, Long> albumIdCount = albumContentRelationExMapper.countContentRelationByAlbumId(idList)
                    .stream()
                    .collect(Collectors.toMap(CommonIdCountBo::getId, CommonIdCountBo::getCount));
            albumCourseListItemResponseList.forEach(item -> {
                Integer albumId = item.getAlbumId();
                if (albumCourseMap.containsKey(albumId)) {
                    AlbumCourse albumCourse = albumCourseMap.get(albumId);
                    item.setCourseId(albumCourse.getCourseId());
                }
                Integer courseId = item.getCourseId();
                if (courseMap.containsKey(courseId)) {
                    item.setCourseId(courseId);
                    item.setCourseName(courseMap.get(courseId).getCourseName());
                }
                // 设置分类

                item.setContentCount(albumIdCount.getOrDefault(albumId, 0L));
            });

        }
        response.setTotal(count);
        response.setList(albumCourseListItemResponseList);
        return response;
    }
}
