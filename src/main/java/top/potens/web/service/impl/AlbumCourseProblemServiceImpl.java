package top.potens.web.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.log.AppLogger;
import top.potens.framework.model.DateScope;
import top.potens.framework.model.PageResponse;
import top.potens.framework.serialization.JSON;
import top.potens.framework.service.impl.AbstractSimpleTableCommonServiceImpl;
import top.potens.framework.util.BeanCopierUtil;
import top.potens.framework.util.CollectionUtil;
import top.potens.framework.util.DateUtil;
import top.potens.web.bmo.CommonIdCountBo;
import top.potens.web.common.constant.AlbumCourseProblemConstant;
import top.potens.web.dao.db.auto.AlbumCourseProblemMapper;
import top.potens.web.dao.db.auto.AlbumCourseProblemTopicMapper;
import top.potens.web.dao.db.ext.AlbumContentRelationExMapper;
import top.potens.web.model.*;
import top.potens.web.request.AlbumCourseProblemAddRequest;
import top.potens.web.request.AlbumCourseProblemListItemRequest;
import top.potens.web.response.AlbumCourseProblemListItemResponse;
import top.potens.web.response.AlbumCourseProblemTopicResponse;
import top.potens.web.service.AlbumCourseProblemService;
import top.potens.web.service.AlbumCourseService;

import java.util.Date;
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
public class AlbumCourseProblemServiceImpl extends AbstractSimpleTableCommonServiceImpl<AlbumCourseProblem> implements AlbumCourseProblemService {
    private final AlbumCourseProblemMapper albumCourseProblemMapper;
    private final AlbumCourseProblemTopicMapper albumCourseProblemTopicMapper;
    private final AlbumCourseService albumCourseService;
    private final AlbumContentRelationExMapper albumContentRelationExMapper;

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

        List<Integer> albumIdList = albumCourseProblemList.stream().map(AlbumCourseProblem::getAlbumId).collect(Collectors.toList());
        List<Integer> albumCourseProblemIdList = albumCourseProblemList.stream().map(AlbumCourseProblem::getAlbumCourseProblemId).collect(Collectors.toList());
        // 获取课程专辑的内容总数
        Map<Integer, Long> albumIdCountMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(albumIdList)) {
            Map<Integer, Long> albumIdCountMap1 = albumContentRelationExMapper.countContentRelationByAlbumId(albumIdList)
                    .stream()
                    .collect(Collectors.toMap(CommonIdCountBo::getId, CommonIdCountBo::getCount));
            albumIdCountMap.putAll(albumIdCountMap1);
        }

        // 获取已答的题目数据
        Map<Integer, List<AlbumCourseProblemTopic>> albumCourseProblemTopicMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(albumCourseProblemIdList)) {
            AlbumCourseProblemTopicExample albumCourseProblemTopicExample = new AlbumCourseProblemTopicExample();
            albumCourseProblemTopicExample.createCriteria().andAlbumCourseProblemIdIn(albumCourseProblemIdList);
            List<AlbumCourseProblemTopic> albumCourseProblemTopicsList = albumCourseProblemTopicMapper.selectByExample(albumCourseProblemTopicExample);
            Map<Integer, List<AlbumCourseProblemTopic>> albumCourseProblemTopicMap1 = albumCourseProblemTopicsList.stream().collect(Collectors.groupingBy(AlbumCourseProblemTopic::getAlbumCourseProblemId));
            albumCourseProblemTopicMap.putAll(albumCourseProblemTopicMap1);
        }


        List<AlbumCourseProblemListItemResponse> list = BeanCopierUtil.convert(albumCourseProblemList, AlbumCourseProblemListItemResponse.class);
        for (AlbumCourseProblemListItemResponse item : list) {
            item.setTotalContentCount(albumIdCountMap.getOrDefault(item.getAlbumId(), 0L));
            if (albumCourseProblemTopicMap.containsKey(item.getAlbumCourseProblemId())) {
                item.setAnswerContentCount((long) albumCourseProblemTopicMap.get(item.getAlbumCourseProblemId()).size());
            } else {
                item.setAnswerContentCount(0L);
            }
        };
        PageSerializable<AlbumCourseProblem> of = PageSerializable.of(albumCourseProblemList);
        response.setTotal(of.getTotal());
        response.setList(list);
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
