package top.potens.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.web.dao.db.auto.ContentMapper;
import top.potens.web.service.ContentCourseService;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentCourseServiceImpl
 * @projectName web-api
 * @date 2019/10/25 14:24
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentCourseServiceImpl implements ContentCourseService {
    private final ContentMapper contentMapper;

}
