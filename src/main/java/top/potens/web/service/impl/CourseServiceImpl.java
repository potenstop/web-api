package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.util.BeanCopierUtil;
import top.potens.web.bmo.CourseInfoTypeBo;
import top.potens.web.dao.db.ext.CourseExMapper;
import top.potens.web.model.Course;
import top.potens.web.response.CourseListItemResponse;
import top.potens.web.response.CourseTypeSimpleResponse;
import top.potens.web.service.CourseService;
import top.potens.web.service.CourseTypeService;
import top.potens.web.service.logic.CacheServiceLogic;

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

    @Override
    public List<CourseListItemResponse> selectListByFilterNotPage(Integer courseId, String courseName, Integer courseStairId, Integer courseSecondId, Integer courseThreeId) {
        ArrayList<CourseListItemResponse> courseListItemResponseList = new ArrayList<>();
        if (courseName != null) {
            courseName = "%" + courseName + "%";
        }

        List<Integer> courseIdList = courseExMapper.selectCourseIdList(courseId, courseName, courseStairId, courseSecondId, courseThreeId);
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
        return courseListItemResponseList;
    }

}

