package cn.sevenleave.xmlconfig.business.lost.cache.record.controller;

import cn.sevenleave.xmlconfig.business.lost.cache.record.service.ILostCacheRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-13
 */
@Controller
@RequestMapping(value = "/lost/cache/record")
public class LostCacheRecordController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LostCacheRecordController.class);

    @Autowired
    private ILostCacheRecordService lostCacheRecordService;


}
