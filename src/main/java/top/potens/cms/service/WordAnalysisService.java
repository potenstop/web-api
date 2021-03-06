package top.potens.cms.service;


import org.springframework.web.multipart.MultipartFile;
import top.potens.cms.response.UploadTopicListItemResponse;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WordAnalysisService
 * @projectName web-api
 * @date 2019/11/26 6:04
 */
public interface WordAnalysisService {
    List<UploadTopicListItemResponse> batchCourseTopic(MultipartFile file);
}
