package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.web.model.Course;
import top.potens.web.response.CourseListItemResponse;
import top.potens.web.service.CourseService;
import top.potens.web.service.logic.CacheServiceLogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<Course> courseAll = cacheServiceLogic.getCourseAll();
        // courseAll.forEach();
        return null;
    }

}
