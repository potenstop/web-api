package top.potens.cms.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.potens.cms.code.WordCode;
import top.potens.cms.response.TopicResponse;
import top.potens.cms.service.WordAnalysisService;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private List<TopicResponse> handleSelect(List<String> select, Integer type) {
        List<TopicResponse> topicResponseList = new ArrayList<>();
        String titlePattern = "^\\d+．";
        Iterator<String> iterator = select.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            str = str.replaceAll("\r|\n", "");
            TopicResponse topicResponse = new TopicResponse();
            Pattern p = Pattern.compile(titlePattern);
            Matcher m = p.matcher(str);
            if (m.find()) {
                str = str.trim().replaceAll("【\\s+】$", "");
                topicResponse.setTitle(str.substring(m.end(), str.length() - 1).trim());
                topicResponse.setTopicType(type);
                List<String> optionList = new ArrayList<>();
                // 找选项
                String option = iterator.next();
                if (option.contains("A．") && option.contains("B．")) {
                    // AB在一行
                    String[] split = option.split("B．");
                    if (split.length == 2) {
                        optionList.add(split[0].substring(2, split[0].length() - 1).trim());
                        optionList.add(split[1].trim());
                        String optionCD = iterator.next();
                        String[] splitCD = optionCD.split("D．");
                        if (splitCD.length == 2) {
                            optionList.add(splitCD[0].substring(2, splitCD[0].length() - 1).trim());
                            optionList.add(splitCD[1].trim());
                        }
                    }
                } else {
                    // ABCD各占一行
                    String optionB = iterator.next();
                    String optionC = iterator.next();
                    String optionD = iterator.next();
                    optionList.add(option.substring(2, option.length() - 1).trim());
                    optionList.add(optionB.substring(2, optionB.length() - 1).trim());
                    optionList.add(optionC.substring(2, optionC.length() - 1).trim());
                    optionList.add(optionD.substring(2, optionD.length() - 1).trim());
                }
                topicResponse.setOptionList(optionList);
                topicResponseList.add(topicResponse);
            }
        }
        return topicResponseList;
    }
    private List<TopicResponse> handleDiscuss(List<String> select, Integer type) {
        List<TopicResponse> topicResponseList = new ArrayList<>();
        String titlePattern = "^\\d+．";
        Iterator<String> iterator = select.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            str = str.replaceAll("\r|\n", "");
            TopicResponse topicResponse = new TopicResponse();
            Pattern p = Pattern.compile(titlePattern);
            Matcher m = p.matcher(str);
            if (m.find()) {
                topicResponse.setTitle(str.substring(m.end(), str.length() - 1).trim());
                topicResponse.setTopicType(type);
                topicResponseList.add(topicResponse);
            }
        }
        return topicResponseList;
    }

    @Override
    public List<TopicResponse> batchCourseTopic(MultipartFile file) {
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
        ArrayList<TopicResponse> topicResponseList = new ArrayList<>();
        List<TopicResponse> signTopicResponseList = this.handleSelect(signSelect, 1);
        List<TopicResponse> mulTopicResponseList = this.handleSelect(mulSelect, 2);
        List<TopicResponse> discussTopicResponseList = this.handleDiscuss(discuss, 4);
        topicResponseList.addAll(signTopicResponseList);
        topicResponseList.addAll(mulTopicResponseList);
        topicResponseList.addAll(discussTopicResponseList);

        return topicResponseList;
    }
}
