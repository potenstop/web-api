package top.potens.web.service.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.potens.web.dao.db.auto.AlbumCourseMapper;
import top.potens.web.dao.db.auto.AlbumMapper;
import top.potens.web.model.Album;
import top.potens.web.model.AlbumCourse;

import java.util.Date;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumCourseServiceLogic
 * @projectName web-api
 * @date 2019/11/5 11:41
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlbumCourseServiceLogic {
    private final AlbumMapper albumMapper;
    private final AlbumCourseMapper albumCourseMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insertAlbumCourseAndAlbum(Album album, AlbumCourse albumCourse) {
        Date now = new Date();
        album.setCreateTime(now);
        album.setUpdateTime(now);
        albumMapper.insertSelective(album);
        albumCourse.setAlbumId(album.getAlbumId());
        albumCourse.setCreateTime(now);
        albumCourse.setUpdateTime(now);
        albumCourseMapper.insertSelective(albumCourse);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateAlbumCourseAndAlbum(Album album, AlbumCourse albumCourse) {
        Date now = new Date();
        album.setUpdateTime(now);
        albumMapper.updateByPrimaryKeySelective(album);
        albumCourse.setUpdateTime(now);
        albumCourseMapper.updateByPrimaryKeySelective(albumCourse);
    }
}
