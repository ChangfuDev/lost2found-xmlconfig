package cn.sevenleave.xmlconfig.utils.exception;

import cn.sevenleave.xmlconfig.utils.model.JsonResult;
import cn.sevenleave.xmlconfig.utils.util.WebUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：异常全局处理类
 *
 * @author SevenLeave
 * @date 2018-08-06 16:40
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        LOGGER.error(e.getMessage(), e);
        String errorCode = "";
        String errorMsg = e.getMessage();
        
        // 如果异常是我们我们自己定义的
        if (e instanceof GenericException) {
            GenericException exception = (GenericException) e;
            errorCode = exception.getErrorCode();
            errorMsg = exception.getMessage();
        }

        // 如果异常不是我们自己定义的
        if (WebUtils.isAjaxRequest(httpServletRequest)) {
            // 如果异常是ajax异常
            try {
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setStatus(500);
                JsonResult result = JsonResult.fail(errorCode, errorMsg);
                
                // 转化为json格式
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(result);
                
                // 写入response
                PrintWriter pw = httpServletResponse.getWriter();
                pw.write(json);
                pw.close();
            } catch (Exception exception) {
                LOGGER.error(errorMsg, exception);
            }
            
            // response正常返回或者异常被捕获，都执行不到这里
            return null;
        } else {
            // 如果是其他未知异常
            Map<String, Object> model = new HashMap<>();
            model.put("exception", e);
            return new ModelAndView("/errorpages/error", model);
        }
    }
}
