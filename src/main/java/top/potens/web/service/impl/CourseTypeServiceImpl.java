package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.util.StringUtil;
import top.potens.web.dao.db.auto.CourseTypeMapper;
import top.potens.web.model.CourseType;
import top.potens.web.service.CourseTypeService;
import top.potens.web.service.logic.CacheServiceLogic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseTypeServiceImpl
 * @projectName web-api
 * @date 2019/10/25 12:14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseTypeServiceImpl implements CourseTypeService {
    private final CourseTypeMapper courseTypeMapper;
    private final CacheServiceLogic cacheServiceLogic;
    private final String defaultChar = ",";
    @Override
    public String getName(List<Integer> courseTypeIdList) {
        return getName(courseTypeIdList, defaultChar);
    }

    @Override

    public String getName(List<Integer> courseTypeIdList, String character) {
        List<String> stringList = new ArrayList<>();
        if (StringUtil.isBlank(character)) {
            character = defaultChar;
        }
        Map<Integer, String> nameMap = getNameMap(courseTypeIdList);
        nameMap.forEach((k, v) -> {
            stringList.add(v);
        });
        return String.join(character, stringList);
    }

    @Override
    public String getName(Integer courseTypeId) {
        return getName(Collections.singletonList(courseTypeId));
    }

    @Override
    public Map<Integer, String> getNameMap(List<Integer> courseTypeIdList) {
        HashMap<Integer, String> map = new HashMap<>();
        if (CollectionUtils.isNotEmpty(courseTypeIdList)) {
            Map<Integer, CourseType> dataMap = cacheServiceLogic.getCourseTypeAll().stream().collect(Collectors.toMap(CourseType::getCourseTypeId, courseType -> courseType));
            courseTypeIdList.forEach(item -> {
                if (dataMap.containsKey(item)) {
                    map.put(item, dataMap.get(item).getTypeName());
                }
            });
        }
        return map;
    }

    @Override
    public Map<Integer, String> getNameMap(Integer courseTypeId) {
        return getNameMap(Collections.singletonList(courseTypeId));
    }
}
