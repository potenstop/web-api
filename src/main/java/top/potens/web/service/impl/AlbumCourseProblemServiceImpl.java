package top.potens.web.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.model.DateScope;
import top.potens.framework.model.PageResponse;
import top.potens.framework.service.impl.AbstractSimpleTableCommonServiceImpl;
import top.potens.framework.util.BeanCopierUtil;
import top.potens.framework.util.DateUtil;
import top.potens.web.common.constant.AlbumCourseProblemConstant;
import top.potens.web.dao.db.auto.AlbumCourseProblemMapper;
import top.potens.web.dao.db.auto.AlbumCourseProblemTopicMapper;
import top.potens.web.model.*;
import top.potens.web.request.AlbumCourseProblemAddRequest;
import top.potens.web.request.AlbumCourseProblemListItemRequest;
import top.potens.web.response.AlbumCourseProblemListItemResponse;
import top.potens.web.response.AlbumCourseProblemTopicResponse;
import top.potens.web.service.AlbumCourseProblemService;
import top.potens.web.service.AlbumCourseService;

import java.util.Date;
import java.util.List;

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
public class AlbumCourseProblemServiceImpl extends AbstractSimpleTableCommonServiceImpl<AlbumCourseProblem> implements AlbumCourseProblemService {
    private final AlbumCourseProblemMapper albumCourseProblemMapper;
    private final AlbumCourseProblemTopicMapper albumCourseProblemTopicMapper;
    private final AlbumCourseService albumCourseService;

    @Override
    protected AlbumCourseProblem mapperByPrimaryKey(Integer id) {
        return albumCourseProblemMapper.selectByPrimaryKey(id);
    }

    @Override
    protected AlbumCourseProblem mapperBySecondPrimaryKey(Integer id) {
        return null;
    }

    @Override
    protected Boolean isDelete(AlbumCourseProblem albumCourseProblem) {
        return false;
    }

    @Override
    public Integer insertOne(AlbumCourseProblemAddRequest request, Integer userId) {
        AlbumCourse albumCourse = albumCourseService.bySecondPrimaryKeyException(request.getAlbumId());
        AlbumCourseProblem albumCourseProblem = new AlbumCourseProblem();
        Date now = new Date();
        albumCourseProblem.setAlbumId(albumCourse.getAlbumId());
        albumCourseProblem.setCreateTime(now);
        albumCourseProblem.setUpdateTime(now);
        albumCourseProblem.setUserId(userId);
        albumCourseProblem.setAlbumCourseId(albumCourse.getAlbumCourseId());
        albumCourseProblem.setState(AlbumCourseProblemConstant.State.SAVE);
        albumCourseProblemMapper.insertSelective(albumCourseProblem);
        return albumCourseProblem.getAlbumCourseProblemId();
    }

    @Override
    public PageResponse<AlbumCourseProblemListItemResponse> selectList(AlbumCourseProblemListItemRequest request) {
        PageResponse<AlbumCourseProblemListItemResponse> response = new PageResponse<>();
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        AlbumCourseProblemExample albumCourseProblemExample = new AlbumCourseProblemExample();
        DateScope dateScope = DateUtil.getDateScope(request.getCreateTime());
        AlbumCourseProblemExample.Criteria criteria = albumCourseProblemExample.createCriteria();
        if (dateScope.getStartDate() != null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(dateScope.getStartDate());
        }
        if (dateScope.getEndDate() != null) {
            criteria.andCreateTimeLessThanOrEqualTo(dateScope.getEndDate());
        }
        if (request.getAlbumId() != null) {
            criteria.andAlbumIdEqualTo(request.getAlbumId());
        }
        if (request.getUserId() != null) {
            criteria.andUserIdEqualTo(request.getUserId());
        }
        if (request.getState() != null) {
            criteria.andStateEqualTo(request.getState());
        }
        List<AlbumCourseProblem> albumCourseProblemList = albumCourseProblemMapper.selectByExample(albumCourseProblemExample);
        List<AlbumCourseProblemListItemResponse> convert = BeanCopierUtil.convert(albumCourseProblemList, AlbumCourseProblemListItemResponse.class);
        PageSerializable<AlbumCourseProblem> of = PageSerializable.of(albumCourseProblemList);
        response.setTotal(of.getTotal());
        response.setList(convert);
        return response;
    }

    @Override
    public List<AlbumCourseProblemTopicResponse> selectTopicList(Integer albumCourseProblemTopicId) {
        AlbumCourseProblemTopicExample albumCourseProblemTopicExample = new AlbumCourseProblemTopicExample();
        albumCourseProblemTopicExample.createCriteria().andAlbumCourseProblemIdEqualTo(albumCourseProblemTopicId);
        List<AlbumCourseProblemTopic> albumCourseProblemTopics = albumCourseProblemTopicMapper.selectByExample(albumCourseProblemTopicExample);
        return BeanCopierUtil.convert(albumCourseProblemTopics, AlbumCourseProblemTopicResponse.class);
    }
}
