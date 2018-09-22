package cn.sevenleave.xmlconfig.business.owner.controller;

import cn.sevenleave.xmlconfig.business.owner.service.ISysOwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-16
 */
@Controller
@RequestMapping(value = "/owner")
public class SysOwnerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysOwnerController.class);

    @Autowired
    private ISysOwnerService sysOwnerService;

}
