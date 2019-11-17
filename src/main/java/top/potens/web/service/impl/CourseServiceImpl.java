package top.potens.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppLogger;
import top.potens.framework.model.PageResponse;
import top.potens.framework.serialization.JSON;
import top.potens.framework.util.BeanCopierUtil;
import top.potens.framework.util.CollectionUtil;
import top.potens.web.bmo.CourseInfoTypeBo;
import top.potens.web.code.CourseCode;
import top.potens.web.common.constant.CourseConstant;
import top.potens.web.dao.db.auto.CourseMapper;
import top.potens.web.dao.db.ext.CourseExMapper;
import top.potens.web.model.Course;
import top.potens.web.model.CourseTypeRelation;
import top.potens.web.request.CourseAddRequest;
import top.potens.web.request.CourseListItemRequest;
import top.potens.web.request.CourseUpdateRequest;
import top.potens.web.response.CourseListItemResponse;
import top.potens.web.response.CourseTypeListItemResponse;
import top.potens.web.response.CourseTypeSimpleResponse;
import top.potens.web.response.CourseViewResponse;
import top.potens.web.service.CourseService;
import top.potens.web.service.CourseTypeService;
import top.potens.web.service.logic.CacheServiceLogic;
import top.potens.web.service.logic.CourseServiceLogic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseServiceImpl
 * @projectName web-api
 * @date 2019/10/28 6:13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseServiceImpl implements CourseService {
    private final CacheServiceLogic cacheServiceLogic;
    private final CourseTypeService courseTypeService;
    private final CourseExMapper courseExMapper;
    private final CourseMapper courseMapper;
    private final CourseServiceLogic courseServiceLogic;

    @Override
    public Course byId(Integer courseId) {
        if (courseId == null) {
            throw new ApiException(CourseCode.COURSE_ID_NOT_FOUND);
        }
        Course course = courseMapper.selectByPrimaryKey(courseId);
        if (course == null) {
            throw new ApiException(CourseCode.COURSE_ID_NOT_FOUND);
        }
        return course;
    }
    @Override
    public Map<Integer, Course> selectNameByIdList(List<Integer> idList) {
        List<Course> courseAll = cacheServiceLogic.getCourseAll();
        Map<Integer, Course> collect = courseAll.stream().collect(Collectors.toMap(Course::getCourseId, course -> course));

        HashMap<Integer, Course> data = new HashMap<>();
        if (CollectionUtils.isNotEmpty(idList)) {
            idList.forEach(id -> {
                if (collect.containsKey(id)) {
                    data.put(id, collect.get(id));
                }
            });
        }
        return data;
    }
    private List<CourseListItemResponse> selectItemListByIdList(List<Integer> courseIdList) {
        ArrayList<CourseListItemResponse> courseListItemResponseList = new ArrayList<>();
        if (CollectionUtils.isEmpty(courseIdList)) {
            return courseListItemResponseList;
        }
        List<CourseInfoTypeBo> courseInfoTypeBos = courseExMapper.selectCourseListByIds(courseIdList);
        Set<Integer> courseTypeIdSet = new HashSet<>();
        Map<Integer, List<CourseInfoTypeBo>> courseInfoTypeBoMap = new HashMap<>();
        courseInfoTypeBos.forEach(courseInfoTypeBo -> {
            courseTypeIdSet.add(courseInfoTypeBo.getCourseStairId());
            courseTypeIdSet.add(courseInfoTypeBo.getCourseSecondId());
            courseTypeIdSet.add(courseInfoTypeBo.getCourseThreeId());
            if (courseInfoTypeBoMap.containsKey(courseInfoTypeBo.getCourseId())) {
                List<CourseInfoTypeBo> list = courseInfoTypeBoMap.get(courseInfoTypeBo.getCourseId());
                list.add(courseInfoTypeBo);
            } else {
                ArrayList<CourseInfoTypeBo> list = new ArrayList<>();
                list.add(courseInfoTypeBo);
                courseInfoTypeBoMap.put(courseInfoTypeBo.getCourseId(), list);
            }
        });
        List<Integer> courseTypeIdList = new ArrayList<>(courseTypeIdSet);
        Map<Integer, String> nameMap = courseTypeService.getNameMap(courseTypeIdList);

        courseInfoTypeBoMap.forEach((k, v) -> {
            CourseInfoTypeBo first = v.get(0);
            CourseListItemResponse courseListItemResponse = BeanCopierUtil.convert(first, CourseListItemResponse.class);
            if (nameMap.containsKey(first.getCourseStairId())) {
                courseListItemResponse.setCourseStairName(nameMap.get(first.getCourseStairId()));
            }
            if (nameMap.containsKey(first.getCourseSecondId())) {
                courseListItemResponse.setCourseSecondName(nameMap.get(first.getCourseSecondId()));
            }
            ArrayList<CourseTypeSimpleResponse> courseTypeSimpleResponseList = new ArrayList<>();
            v.forEach(courseInfoTypeBo -> {
                CourseTypeSimpleResponse courseTypeSimpleResponse = new CourseTypeSimpleResponse();
                courseTypeSimpleResponse.setCourseTypeId(courseInfoTypeBo.getCourseThreeId());
                courseTypeSimpleResponse.setTypeName(nameMap.getOrDefault(courseInfoTypeBo.getCourseThreeId(), ""));
                courseTypeSimpleResponseList.add(courseTypeSimpleResponse);
            });
            courseListItemResponse.setCourseThreeList(courseTypeSimpleResponseList);
            courseListItemResponseList.add(courseListItemResponse);
        });
        CollectionUtil.referenceSort(courseListItemResponseList, courseIdList, CourseListItemResponse::getCourseId);
        return courseListItemResponseList;
    }

    @Override
    public List<CourseListItemResponse> selectListByFilterNotPage(Integer courseId, String courseName, String courseCode, Integer courseStairId, Integer courseSecondId, Integer courseThreeId) {
        if (courseName != null) {
            courseName = "%" + courseName + "%";
        }
        List<Integer> courseIdList = courseExMapper.selectCourseIdList(courseId, courseName, courseCode, courseStairId, courseSecondId, courseThreeId, null);
        return selectItemListByIdList(courseIdList);

    }

    private List<CourseTypeRelation> checkCourseRelation(Integer courseStairId, Integer courseSecondId, List<Integer> courseThreeIdList) {
        // 校验选择的分类关系是否正确
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(courseStairId);
        idList.add(courseSecondId);
        idList.addAll(courseThreeIdList);
        List<CourseTypeListItemResponse> courseTypeListItemResponses = courseTypeService.listByIdList(idList);
        if (idList.size() != courseTypeListItemResponses.size()) {
            AppLogger.warn("id有不存在");
            throw new ApiException(CourseCode.COURSE_TYPE_ID_ERROR);
        }
        courseTypeListItemResponses.forEach(courseTypeListItemResponse -> {
            if (courseTypeListItemResponse.getCourseTypeId().equals(courseSecondId)) {
                if (!courseTypeListItemResponse.getParentId().equals(courseStairId)) {
                    AppLogger.warn("二级的父id不等于一级id courseSecondId:[{}] ParentId:[{}]", courseSecondId, courseTypeListItemResponse.getParentId());
                    throw new ApiException(CourseCode.COURSE_TYPE_ID_ERROR);
                }
                if (!CourseConstant.Rank.SECOND.equals(courseTypeListItemResponse.getRank())) {
                    AppLogger.warn("级别不是二级 courseTypeListItemResponse:[{}]", JSON.toJSONString(courseTypeListItemResponse));
                    throw new ApiException(CourseCode.COURSE_TYPE_ID_ERROR);
                }
            }
            // 判断三级id
            if (CourseConstant.Rank.THREE.equals(courseTypeListItemResponse.getRank())) {
                if (!courseTypeListItemResponse.getParentId().equals(courseSecondId)) {
                    AppLogger.warn("三级的父级错误 CourseSecondId:[{}] ParentId:[{}]", courseSecondId, courseTypeListItemResponse.getParentId());
                    throw new ApiException(CourseCode.COURSE_TYPE_ID_ERROR);
                }
            }
        });
        List<CourseTypeRelation> courseTypeRelationList = new ArrayList<>();
        courseThreeIdList.forEach(threeId -> {
            CourseTypeRelation courseTypeRelation = new CourseTypeRelation();
            courseTypeRelation.setCourseStairId(courseStairId);
            courseTypeRelation.setCourseSecondId(courseSecondId);
            courseTypeRelation.setCourseThreeId(threeId);
            courseTypeRelationList.add(courseTypeRelation);
        });
        return courseTypeRelationList;
    }

    @Override
    public Integer insertOne(CourseAddRequest request) {
        List<CourseTypeRelation> courseTypeRelationList = checkCourseRelation(request.getCourseStairId(), request.getCourseSecondId(), request.getCourseThreeIdList());

        // 组装数据
        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setCourseCode(request.getCourseCode());

        // 入库
        courseServiceLogic.insertDataAndRelation(course, courseTypeRelationList);

        return course.getCourseId();
    }

    @Override
    public PageResponse<CourseListItemResponse> selectList(CourseListItemRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        String courseName = request.getCourseName();
        if (courseName != null) {
            courseName = "%" + courseName + "%";
        }
        List<Integer> courseIdList = courseExMapper.selectCourseIdList(request.getCourseId(), courseName, request.getCourseCode(), request.getCourseStairId(), request.getCourseSecondId(), request.getCourseThreeId(), request.getOrderBy());
        List<CourseListItemResponse> courseListItemResponses = selectItemListByIdList(courseIdList);
        PageResponse<CourseListItemResponse> response = new PageResponse<>();
        PageSerializable<Integer> of = PageSerializable.of(courseIdList);
        response.setTotal(of.getTotal());
        response.setList(courseListItemResponses);
        return response;
    }

    @Override
    public CourseViewResponse selectById(Integer courseId) {
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(courseId);
        List<CourseInfoTypeBo> courseInfoTypeBos = courseExMapper.selectCourseListByIds(idList);
        if (CollectionUtils.isEmpty(courseInfoTypeBos)) {
            throw new ApiException(CourseCode.COURSE_ID_NOT_FOUND);
        }
        CourseViewResponse courseViewResponse = BeanCopierUtil.convert(courseInfoTypeBos.get(0), CourseViewResponse.class);
        courseViewResponse.setCourseStairName(courseTypeService.getName(courseViewResponse.getCourseStairId()));
        courseViewResponse.setCourseSecondName(courseTypeService.getName(courseViewResponse.getCourseSecondId()));

        ArrayList<CourseTypeSimpleResponse> threeList = new ArrayList<>();
        courseInfoTypeBos.forEach(item -> {
            CourseTypeSimpleResponse courseTypeSimpleResponse = new CourseTypeSimpleResponse();
            courseTypeSimpleResponse.setCourseTypeId(item.getCourseThreeId());
            courseTypeSimpleResponse.setTypeName(courseTypeService.getName(item.getCourseThreeId()));
            threeList.add(courseTypeSimpleResponse);
        });
        courseViewResponse.setCourseThreeList(threeList);
        return courseViewResponse;
    }

    @Override
    public Integer updateById(CourseUpdateRequest request) {
        // 判断id是否存在
        byId(request.getCourseId());
        // 校验关系
        List<CourseTypeRelation> courseTypeRelationList = checkCourseRelation(request.getCourseStairId(), request.getCourseSecondId(), request.getCourseThreeIdList());
        // 入库
        Course updateCourse = new Course();
        updateCourse.setCourseId(request.getCourseId());
        updateCourse.setCourseCode(request.getCourseCode());
        updateCourse.setCourseName(request.getCourseName());

        courseServiceLogic.deleteRelationAndUpdate(updateCourse, courseTypeRelationList);
        return updateCourse.getCourseId();
    }
}

