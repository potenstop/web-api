package top.potens.web.service;


import top.potens.web.model.Album;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className AlbumService
 * @projectName web-api
 * @date 2019/11/5 12:05
 */
public interface AlbumService {
    /**
     *
     * 方法功能描述: 按专辑id 查询专辑对象
     *
     * @author yanshaowen
     * @date 2019/11/5 11:56
     * @param albumId
     * @return
     * @throws
     */
    Album byId(Integer albumId);
}
