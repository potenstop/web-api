package top.potens.framework.util;

import java.util.UUID;

/**
 * Created by wenshao on 2019/7/21.
 */
public class StringUtil {
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
