package cn.sevenleave.xmlconfig.support.utils;

import java.util.UUID;

/**
 * @author SevenLeave
 * @date 2018-07-30 16:12
 */
public final class StringUtils {

    /**
     * 描述：生成32位的uuid字符串
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

}
