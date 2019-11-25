package top.potens.cms.service;


import org.springframework.web.multipart.MultipartFile;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WordAnalysisService
 * @projectName web-api
 * @date 2019/11/26 6:04
 */
public interface WordAnalysisService {
    String batchCourseTopic(MultipartFile file);
}
