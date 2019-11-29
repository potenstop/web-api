package top.potens.web.bmo;

import lombok.Data;
import top.potens.web.model.Content;
import top.potens.web.model.ContentTopic;
import top.potens.web.model.ContentTopicSelectOption;

import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentTopicCompleteBo
 * @projectName web-api
 * @date 2019/11/29 15:10
 */
@Data
public class ContentTopicCompleteBo {
    private Content content;
    private ContentTopic contentTopic;
    private List<ContentTopicSelectOption> ContentTopicSelectOptionList;
}
