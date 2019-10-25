package top.potens.web.dao.db.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.potens.web.model.AlbumContentRelation;
import top.potens.web.model.AlbumContentRelationExample;

public interface AlbumContentRelationMapper {
    long countByExample(AlbumContentRelationExample example);

    int deleteByExample(AlbumContentRelationExample example);

    int deleteByPrimaryKey(Integer albumContentRelationId);

    int insert(AlbumContentRelation record);

    int insertSelective(AlbumContentRelation record);

    List<AlbumContentRelation> selectByExample(AlbumContentRelationExample example);

    AlbumContentRelation selectByPrimaryKey(Integer albumContentRelationId);

    int updateByExampleSelective(@Param("record") AlbumContentRelation record, @Param("example") AlbumContentRelationExample example);

    int updateByExample(@Param("record") AlbumContentRelation record, @Param("example") AlbumContentRelationExample example);

    int updateByPrimaryKeySelective(AlbumContentRelation record);

    int updateByPrimaryKey(AlbumContentRelation record);
}