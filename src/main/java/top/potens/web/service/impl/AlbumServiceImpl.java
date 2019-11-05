package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.web.code.AlbumCode;
import top.potens.web.dao.db.auto.AlbumMapper;
import top.potens.web.model.Album;
import top.potens.web.service.AlbumService;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumServiceImpl
 * @projectName web-api
 * @date 2019/11/5 12:06
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlbumServiceImpl implements AlbumService {
    private final AlbumMapper albumMapper;
    @Override
    public Album byId(Integer albumId) {
        if (albumId == null) {
            throw new ApiException(AlbumCode.ALBUM_ID_ERROR);
        }
        Album album = albumMapper.selectByPrimaryKey(albumId);
        if (album == null) {
            throw new ApiException(AlbumCode.ALBUM_ID_ERROR);
        }
        return album;
    }
}
