package top.potens.web.dao.db.ext;

import org.apache.ibatis.annotations.Param;
import top.potens.web.bmo.CommonIdCountBo;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumContentRelationExMapper
 * @projectName web-api
 * @date 2019/10/25 14:46
 */
public interface AlbumContentRelationExMapper {
    /**
    *
    * 方法功能描述: 分组查询专辑id 对应的内容个数
    *
    * @author yanshaowen
    * @date 2019/10/25 16:59
    * @param albumIdList
    * @return
    * @throws
    */
    List<CommonIdCountBo> countContentRelationByAlbumId(@Param("albumIdList") List<Integer> albumIdList);
}
