package top.potens.web.bmo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.potens.web.model.Course;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CourseInfoTypeBo
 * @projectName web-api
 * @date 2019/10/31 14:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CourseInfoTypeBo extends Course {
    private Integer courseStairId;

    private Integer courseSecondId;

    private Integer courseThreeId;
}

