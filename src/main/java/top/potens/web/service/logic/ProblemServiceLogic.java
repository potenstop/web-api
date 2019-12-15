package top.potens.web.service.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.potens.web.dao.db.auto.AlbumCourseProblemMapper;
import top.potens.web.dao.db.auto.AlbumCourseProblemTopicMapper;
import top.potens.web.model.AlbumCourseProblem;
import top.potens.web.model.AlbumCourseProblemTopic;

import java.util.Date;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ProblemServiceLogic
 * @projectName web-api
 * @date 2019/12/15 12:05
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProblemServiceLogic {
    private final AlbumCourseProblemTopicMapper albumCourseProblemTopicMapper;
    private final AlbumCourseProblemMapper albumCourseProblemMapper;
    @Transactional(rollbackFor = Exception.class)
    public void updateProblemTopic(
            List<AlbumCourseProblemTopic> updateAlbumCourseProblemTopicList,
            List<AlbumCourseProblemTopic> insertAlbumCourseProblemTopicList,
            Integer AlbumCourseProblemId
    ) {
        insertAlbumCourseProblemTopicList.forEach(albumCourseProblemTopicMapper::insertSelective);
        updateAlbumCourseProblemTopicList.forEach(albumCourseProblemTopicMapper::updateByPrimaryKeySelective);
        AlbumCourseProblem albumCourseProblem = new AlbumCourseProblem();
        albumCourseProblem.setAlbumCourseProblemId(AlbumCourseProblemId);
        albumCourseProblem.setUpdateTime(new Date());
        albumCourseProblemMapper.updateByPrimaryKeySelective(albumCourseProblem);
    }
}
