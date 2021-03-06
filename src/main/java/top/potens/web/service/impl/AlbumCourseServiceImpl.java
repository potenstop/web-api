package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.model.DateScope;
import top.potens.framework.model.PageResponse;
import top.potens.framework.service.impl.AbstractSimpleTableCommonServiceImpl;
import top.potens.framework.util.BeanCopierUtil;
import top.potens.framework.util.CollectionUtil;
import top.potens.framework.util.DateUtil;
import top.potens.framework.util.StringUtil;
import top.potens.web.bmo.CommonIdCountBo;
import top.potens.web.code.AlbumCode;
import top.potens.web.code.CommonCode;
import top.potens.web.code.ContentCode;
import top.potens.web.common.constant.ContentConstant;
import top.potens.web.dao.db.auto.AlbumContentRelationMapper;
import top.potens.web.dao.db.auto.AlbumCourseMapper;
import top.potens.web.dao.db.auto.AlbumMapper;
import top.potens.web.dao.db.ext.AlbumContentRelationExMapper;
import top.potens.web.dao.db.ext.AlbumCourseExMapper;
import top.potens.web.model.*;
import top.potens.web.request.AlbumCourseAddRequest;
import top.potens.web.request.AlbumCourseListItemRequest;
import top.potens.web.request.AlbumCourseUpdateCourseRelationRequest;
import top.potens.web.request.AlbumCourseUpdateRequest;
import top.potens.web.response.AlbumCourseListItemResponse;
import top.potens.web.response.AlbumCourseTopicViewResponse;
import top.potens.web.response.AlbumCourseViewResponse;
import top.potens.web.response.ContentTopicViewResponse;
import top.potens.web.service.*;
import top.potens.web.service.logic.AlbumCourseServiceLogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
public class AlbumCourseServiceImpl extends AbstractSimpleTableCommonServiceImpl<AlbumCourse> implements AlbumCourseService {

    private final AlbumCourseExMapper albumCourseExMapper;
    private final AlbumMapper albumMapper;
    private final AlbumCourseMapper albumCourseMapper;
    private final CourseTypeService courseTypeService;
    private final AlbumContentRelationExMapper albumContentRelationExMapper;
    private final CourseService courseService;
    private final AlbumCourseServiceLogic albumCourseServiceLogic;
    private final AlbumService albumService;
    private final ContentService contentService;
    private final AlbumContentRelationMapper albumContentRelationMapper;
    private final ContentTopicService contentTopicService;

    @Override
    protected AlbumCourse mapperByPrimaryKey(Integer id) {
        return albumCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    protected AlbumCourse mapperBySecondPrimaryKey(Integer id) {
        AlbumCourseExample albumCourseExample = new AlbumCourseExample();
        albumCourseExample.createCriteria().andAlbumIdEqualTo(id);
        List<AlbumCourse> albumCourseList = albumCourseMapper.selectByExample(albumCourseExample);
        if (CollectionUtils.isEmpty(albumCourseList)) {
            return null;
        }
        return albumCourseList.get(0);
    }

    @Override
    protected Boolean isDelete(AlbumCourse albumCourse) {
        return false;
    }

    @Override
    public PageResponse<AlbumCourseListItemResponse> selectList(AlbumCourseListItemRequest request) {
        PageResponse<AlbumCourseListItemResponse> response = new PageResponse<>();
        DateScope dateScope = DateUtil.getDateScope(request.getCreateTime());
        Long count = albumCourseExMapper.selectAlbumCount(request.getAlbumId(), request.getAlbumName(), dateScope.getStartDate(), dateScope.getEndDate());
        Long offset = BigDecimal.valueOf(request.getPageNum()).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(request.getPageSize())).longValue();
        if (StringUtil.isNotBlank(request.getAlbumName())) {
            request.setAlbumName("%" + request.getAlbumName() + "%");
        }
        List<Integer> albumIdList = albumCourseExMapper.selectAlbumIdList(request.getPageSize(), offset, request.getOrderBy(),
                request.getAlbumId(), request.getAlbumName(), dateScope.getStartDate(), dateScope.getEndDate());

        List<AlbumCourseListItemResponse> albumCourseListItemResponseList = new ArrayList<>();
        if (albumIdList.size() > 0){
            // 获取专辑表的信息
            AlbumExample albumExample = new AlbumExample();
            albumExample.createCriteria().andAlbumIdIn(albumIdList);
            List<Album> albums = albumMapper.selectByExample(albumExample);
            albumCourseListItemResponseList = BeanCopierUtil.convert(albums, AlbumCourseListItemResponse.class);
            // 获取课程专辑的信息
            AlbumCourseExample albumCourseExample = new AlbumCourseExample();
            albumCourseExample.createCriteria().andAlbumIdIn(albumIdList);
            List<Integer> courseIdList = new ArrayList<>();
            Map<Integer, AlbumCourse> albumCourseMap = albumCourseMapper.selectByExample(albumCourseExample).stream().collect(Collectors.toMap(AlbumCourse::getAlbumId, albumCourse -> {
                courseIdList.add(albumCourse.getCourseId());
                return albumCourse;
            }));

            // 获取课程名称和id
            Map<Integer, Course> courseMap = courseService.selectNameByIdList(courseIdList);

            // 获取课程专辑的内容总数
            Map<Integer, Long> albumIdCount = albumContentRelationExMapper.countContentRelationByAlbumId(albumIdList)
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
                item.setContentCount(albumIdCount.getOrDefault(albumId, 0L));
            });

        }
        response.setTotal(count);
        CollectionUtil.referenceSort(albumCourseListItemResponseList, albumIdList, AlbumCourseListItemResponse::getAlbumId);
        response.setList(albumCourseListItemResponseList);
        return response;
    }

    @Override
    public Integer insertOne(AlbumCourseAddRequest request) {
        // 判断课程id是否存在
        courseService.byPrimaryKeyException(request.getCourseId());
        // 组装数据
        Album album = new Album();
        AlbumCourse albumCourse = new AlbumCourse();
        album.setAlbumName(request.getAlbumName());
        album.setAlbumDesc(request.getAlbumDesc());
        albumCourse.setCourseId(request.getCourseId());
        // 入库
        albumCourseServiceLogic.insertAlbumCourseAndAlbum(album, albumCourse);
        return album.getAlbumId();
    }

    @Override
    public AlbumCourseViewResponse selectById(Integer albumId) {
        AlbumCourse albumCourse = bySecondPrimaryKeyException(albumId);
        Album album = albumService.byPrimaryKeyException(albumId);
        Course course = courseService.byPrimaryKeyException(albumCourse.getCourseId());
        AlbumCourseViewResponse albumCourseViewResponse = new AlbumCourseViewResponse();
        albumCourseViewResponse.setAlbumId(album.getAlbumId());
        albumCourseViewResponse.setAlbumName(album.getAlbumName());
        albumCourseViewResponse.setAlbumDesc(album.getAlbumDesc());
        albumCourseViewResponse.setCourseId(course.getCourseId());
        albumCourseViewResponse.setCourseName(course.getCourseName());

        AlbumContentRelationExample albumContentRelationExample = new AlbumContentRelationExample();
        albumContentRelationExample.createCriteria().andAlbumIdEqualTo(albumCourse.getAlbumId());
        List<AlbumContentRelation> albumContentRelations = albumContentRelationMapper.selectByExample(albumContentRelationExample);
        List<Integer> idList = new ArrayList<>();
        albumContentRelations.forEach(item -> {
            idList.add(item.getContentId());
        });
        albumCourseViewResponse.setContentIdList(idList);
        return albumCourseViewResponse;
    }

    @Override
    public Integer updateById(AlbumCourseUpdateRequest request) {
        AlbumCourse albumCourse = bySecondPrimaryKeyException(request.getAlbumId());
        Album album = albumService.byPrimaryKeyException(request.getAlbumId());
        courseService.byPrimaryKeyException(request.getCourseId());
        Album updateAlbum = new Album();
        AlbumCourse updateAlbumCourse = new AlbumCourse();
        updateAlbum.setAlbumId(album.getAlbumId());
        updateAlbum.setAlbumName(request.getAlbumName());
        updateAlbum.setAlbumDesc(request.getAlbumDesc());
        updateAlbumCourse.setCourseId(request.getCourseId());
        updateAlbumCourse.setAlbumCourseId(albumCourse.getAlbumCourseId());
        albumCourseServiceLogic.updateAlbumCourseAndAlbum(updateAlbum, updateAlbumCourse);
        return album.getAlbumId();
    }

    @Override
    public Integer updateCourseRelationById(AlbumCourseUpdateCourseRelationRequest request) {
        bySecondPrimaryKeyException(request.getAlbumId());
        List<AlbumContentRelation> albumContentRelationList = new ArrayList<>();
        if (request.getContentIdList() != null) {
            List<Content> contents = contentService.byIdList(request.getContentIdList());
            if (contents.size() != request.getContentIdList().size()) {
                throw new ApiException(ContentCode.CONTENT_ID_NOT_FOUND);
            }
            contents.forEach(item -> {
                if (!ContentConstant.ContentType.TOPIC.equals(item.getContentType())) {
                    throw new ApiException(ContentCode.CONTENT_TYPE_NOT_IS_TOPIC);
                }
            });
            request.getContentIdList().forEach(item -> {
                AlbumContentRelation albumContentRelation = new AlbumContentRelation();
                albumContentRelation.setAlbumId(request.getAlbumId());
                albumContentRelation.setContentId(item);
                albumContentRelationList.add(albumContentRelation);
            });
        }
        albumCourseServiceLogic.updateAlbumContentRelation(request.getAlbumId(), albumContentRelationList);
        return 1;
    }

    @Override
    public AlbumCourseTopicViewResponse selectTopicListById(Integer albumId) {
        AlbumCourseViewResponse albumCourseViewResponse = selectById(albumId);
        AlbumCourseTopicViewResponse albumCourseTopicViewResponse = BeanCopierUtil.convert(albumCourseViewResponse, AlbumCourseTopicViewResponse.class);
        List<ContentTopicViewResponse> contentTopicViewResponses = contentTopicService.selectByIdList(albumCourseViewResponse.getContentIdList());
        albumCourseTopicViewResponse.setContentList(contentTopicViewResponses);
        return albumCourseTopicViewResponse;
    }

    @Override
    public Map<Integer, ContentTopic> selectTopicListByAlbumId(Integer albumId) {
        Map<Integer, ContentTopic> contentTopicMap = new HashMap<>();
        AlbumContentRelationExample albumContentRelationExample = new AlbumContentRelationExample();
        albumContentRelationExample.createCriteria().andAlbumIdEqualTo(albumId);
        List<AlbumContentRelation> albumContentRelations = albumContentRelationMapper.selectByExample(albumContentRelationExample);
        List<Integer> idList = new ArrayList<>();
        albumContentRelations.forEach(item -> {
            idList.add(item.getContentId());
        });
        if (CollectionUtils.isEmpty(idList)) {
            return contentTopicMap;
        }
        return contentTopicService.byIdListResultMap(idList);
    }
}
