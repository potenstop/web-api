package top.potens.cms.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.potens.cms.code.WordCode;
import top.potens.cms.service.WordAnalysisService;
import top.potens.framework.exception.ApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className WordAnalysisServiceImpl
 * @projectName web-api
 * @date 2019/11/26 6:07
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WordAnalysisServiceImpl implements WordAnalysisService {
    private HWPFDocument fileToDoc(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        InputStream streamToUploadFrom = null;
        try {
            streamToUploadFrom = file.getInputStream();
            return new HWPFDocument(streamToUploadFrom);
        } catch (IOException e) {
            throw new ApiException(WordCode.FILE_READ_ERROR);
        }
    }

    private List<String> getRandList(MultipartFile file) {
        HWPFDocument hwpfDocument = this.fileToDoc(file);
        Range range = hwpfDocument.getRange();
        //获取段落数
        int paraNum = range.numParagraphs();
        List<String> randList = new ArrayList<String>();
        for (int i = 0; i < paraNum; i++) {
            randList.add(range.getParagraph(i).text());
        }
        return randList;
    }

    @Override
    public String batchCourseTopic(MultipartFile file) {
        List<String> randList = getRandList(file);
        List<Integer> questionNumberList = new ArrayList<>();
        // 解析出题目和选项
        int index = 0;
        for (String item : randList) {
            if (item.contains("单项选择题")) {
                questionNumberList.add(index);
            }
            if (item.contains("多项选择题")) {
                questionNumberList.add(index);
            }
            if (item.contains("简答题")) {
                questionNumberList.add(index);
            }
            if (item.contains("论述题")) {
                questionNumberList.add(index);
            }
            index++;
        }
        index = 0;
        Integer end = 0;
        List<String> signSelect = new ArrayList<>();
        List<String> mulSelect = new ArrayList<>();
        List<String> discuss = new ArrayList<>();
        for (Integer questionNumber : questionNumberList) {
            if (index == questionNumberList.size() -1) {
                end = randList.size() - 1;
            } else {
                end = questionNumberList.get(index + 1);
            }
            if (randList.get(questionNumber).contains("单项选择题")) {
                signSelect.addAll(randList.subList(questionNumber+1, end));
            }
            if (randList.get(questionNumber).contains("多项选择题")) {
                mulSelect.addAll(randList.subList(questionNumber+1, end));
            }
            if (randList.get(questionNumber).contains("简答题") || randList.get(questionNumber).contains("论述题")) {
                discuss.addAll(randList.subList(questionNumber+1, end));
            }
            index++;
        }

        return randList.toString();
    }
}
