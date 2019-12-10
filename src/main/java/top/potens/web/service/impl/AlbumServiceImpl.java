package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.service.impl.AbstractSimpleTableCommonServiceImpl;
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
public class AlbumServiceImpl extends AbstractSimpleTableCommonServiceImpl<Album> implements AlbumService  {
    private final AlbumMapper albumMapper;
    @Override
    protected Album mapperByPrimaryKey(Integer id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    @Override
    protected Album mapperBySecondPrimaryKey(Integer id) {
        return null;
    }

    @Override
    protected Boolean isDelete(Album album) {
        return false;
    }
}
