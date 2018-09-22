package cn.sevenleave.xmlconfig.business.lost.cache.check.controller;

import cn.sevenleave.xmlconfig.business.lost.cache.check.service.ILostCacheCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-15
 */
@Controller
@RequestMapping(value = "/lost/cache/check")
public class LostCacheCheckController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LostCacheCheckController.class);

    @Autowired
    private ILostCacheCheckService lostCacheCheckService;

}
