package cn.sevenleave.xmlconfig.business.admin.controller;

import cn.sevenleave.xmlconfig.business.admin.model.SysAdmin;
import cn.sevenleave.xmlconfig.business.admin.service.ISysAdminService;
import cn.sevenleave.xmlconfig.business.waiter.model.SysWaiter;
import cn.sevenleave.xmlconfig.business.waiter.service.ISysWaiterService;
import cn.sevenleave.xmlconfig.support.encrypt.PasswordStorage;
import cn.sevenleave.xmlconfig.support.model.JsonResult;
import cn.sevenleave.xmlconfig.support.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-13
 */
@Controller
@RequestMapping(value = "/admin")
public class SysAdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysAdminController.class);

    @Autowired
    private ISysAdminService sysAdminService;
    @Autowired
    private ISysWaiterService sysWaiterService;

    /**
     * 描述：到管理员登录页面
     *
     * @return
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        // todo 以后补充
        return "/business/admin/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestBody SysAdmin sysAdmin) {
        // todo 登录留到以后实现
        return JsonResult.success("还没有实现！");
    }

    /**
     * 描述：到新增waiter页面
     *
     * @return
     */
    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd() {
        return "/business/admin/addWaiter";
    }


    /**
     * 描述：新增waiter
     *
     * @param sysWaiter
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add/waiter", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addWaiter(@RequestBody SysWaiter sysWaiter) throws Exception {
        // 生成uuid
        sysWaiter.setUuid(StringUtils.uuid());
        // 密码加盐hash
        sysWaiter.setPassword(PasswordStorage.createHash(sysWaiter.getPassword()));
        int rows = sysWaiterService.addWaiter(sysWaiter);
        if (rows == 1) {
            return JsonResult.success("新增waiter成功！");
        } else {
            return JsonResult.fail("新增waiter失败！");
        }
    }


}
