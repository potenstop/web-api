package top.potens.web.common.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentTopicConstant
 * @projectName web-api
 * @date 2019/11/6 16:04
 */
public class ContentTopicConstant {
    public static class TopicType {
        /**
         * 单选选择
         */
        public final static Integer SIGN_SELECT = 1;
        /**
         * 多选选择
         */
        public final static Integer MUL_SELECT = 2;
        /**
         * 填空
         */
        public final static Integer FILL_BLANK = 3;
        /**
         * 简答
         */
        public final static Integer SHORT_ANSWER = 4;
        public final static Set<Integer> ALL_SET = new HashSet<>();
        static {
            ALL_SET.add(SIGN_SELECT);
            ALL_SET.add(MUL_SELECT);
            ALL_SET.add(FILL_BLANK);
            ALL_SET.add(SHORT_ANSWER);
        }
    }

}
