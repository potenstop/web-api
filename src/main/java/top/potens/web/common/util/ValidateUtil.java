package top.potens.web.common.util;

import top.potens.framework.log.AppUtil;
import top.potens.web.common.constant.RegexpConstant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wenshao on 2019/6/23.
 */
public class ValidateUtil {
    /**
     * 检验手机号是否合法
     * @param mobile    手机号
     * @return          true false
     */
    public static Boolean mobile(String mobile) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile(RegexpConstant.MOBILE);
            Matcher matcher = regex.matcher(mobile);
            flag = matcher.matches();
        } catch (Exception e) {
            AppUtil.error("check error", e);
        }
        return flag;
    }
    /**
     * 检验邮箱是否合法
     * @param mail    邮箱
     * @return          true false
     */
    public static Boolean mail(String mail) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile(RegexpConstant.MAIL);
            Matcher matcher = regex.matcher(mail);
            flag = matcher.matches();
        } catch (Exception e) {
            AppUtil.error("check error", e);
        }
        return flag;
    }
}
