package top.potens.web.service.impl;

import com.google.common.base.Function;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.log.AppLogger;
import top.potens.framework.util.BeanCopierUtil;
import top.potens.framework.util.StringUtil;
import top.potens.web.common.constant.CourseConstant;
import top.potens.web.dao.db.auto.CourseTypeMapper;
import top.potens.web.model.CourseType;
import top.potens.web.model.CourseTypeExample;
import top.potens.web.response.CourseTypeListItemResponse;
import top.potens.web.response.CourseTypeTreeItemResponse;
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

    @Override
    public List<CourseTypeTreeItemResponse> treeByFilterNotPage() {
        ArrayList<CourseTypeTreeItemResponse> courseTypeTreeItemResponseList = new ArrayList<>();
        HashMap<Integer, List<CourseTypeTreeItemResponse>> parentMap = new HashMap<>();
        cacheServiceLogic.getCourseTypeAll().forEach(courseType -> {
            CourseTypeTreeItemResponse courseTypeTreeItemResponse = BeanCopierUtil.convert(courseType, CourseTypeTreeItemResponse.class);
            List<CourseTypeTreeItemResponse> childCourseTypeTreeItemResponseList = new ArrayList<>();
            parentMap.put(courseType.getCourseTypeId(), childCourseTypeTreeItemResponseList);

            if (CourseConstant.Rank.STAIR.equals(courseType.getRank())) {
                courseTypeTreeItemResponse.setChildList(childCourseTypeTreeItemResponseList);
                courseTypeTreeItemResponseList.add(courseTypeTreeItemResponse);
            } else if (CourseConstant.Rank.SECOND.equals(courseType.getRank()) || CourseConstant.Rank.THREE.equals(courseType.getRank())) {
                if (parentMap.containsKey(courseType.getParentId())) {
                    List<CourseTypeTreeItemResponse> child = parentMap.get(courseType.getParentId());
                    courseTypeTreeItemResponse.setChildList(childCourseTypeTreeItemResponseList);
                    child.add(courseTypeTreeItemResponse);
                } else {
                    AppLogger.info("从map没有找到parentId对应的value parentId:[{}]", courseType.getParentId());
                }
            } else {
                AppLogger.error("课程分类rank错误 rank:[{}]", courseType.getRank());
            }
        });

        return courseTypeTreeItemResponseList;
    }

    @Override
    public List<CourseTypeListItemResponse> listByIdList(List<Integer> idList) {
        ArrayList<CourseTypeListItemResponse> courseTypeListItemResponses = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(idList)) {
            CourseTypeExample courseTypeExample = new CourseTypeExample();
            courseTypeExample.createCriteria().andCourseTypeIdIn(idList);
            List<CourseType> courseTypes = courseTypeMapper.selectByExample(courseTypeExample);
            courseTypes.forEach(courseType -> {
                CourseTypeListItemResponse convert = BeanCopierUtil.convert(courseType, CourseTypeListItemResponse.class);
                courseTypeListItemResponses.add(convert);
            });

        }
        return courseTypeListItemResponses;
    }

    @Override
    public List<CourseTypeListItemResponse> listByFilterNotPage(Integer rank, List<Integer> idList) {
        ArrayList<CourseTypeListItemResponse> courseTypeListItemResponses = new ArrayList<>();
        CourseTypeExample courseTypeExample = new CourseTypeExample();
        CourseTypeExample.Criteria criteria = courseTypeExample.createCriteria();
        if (rank != null) {
            criteria.andRankEqualTo(rank);
        }
        if (CollectionUtils.isNotEmpty(idList)) {
            criteria.andCourseTypeIdIn(idList);
        }
        List<CourseType> courseTypes = courseTypeMapper.selectByExample(courseTypeExample);
        courseTypes.forEach(courseType -> {
            CourseTypeListItemResponse convert = BeanCopierUtil.convert(courseType, CourseTypeListItemResponse.class);
            courseTypeListItemResponses.add(convert);
        });
        return courseTypeListItemResponses;

    }
}
