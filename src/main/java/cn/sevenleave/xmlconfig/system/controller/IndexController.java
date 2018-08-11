package cn.sevenleave.xmlconfig.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: SevenLeave
 * @date: 2018-07-15 17:31
 */
@Controller
@RequestMapping
public class IndexController {

    /**
     * 日志记录器
     */
    static Logger logger = LoggerFactory.getLogger(IndexController.class);
    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = {"","/"})
    public String index() {
        return "system/index";
    }
}
