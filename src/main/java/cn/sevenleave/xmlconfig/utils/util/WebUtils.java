package cn.sevenleave.xmlconfig.utils.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author SevenLeave
 * @date 2018-08-06 16:48
 */
public final class WebUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);
    
    /**
     * 判断是否为ajax请求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null) && ("XMLHttpRequest").equals(request.getHeader("X-Requested-With"));
    }
    
}
