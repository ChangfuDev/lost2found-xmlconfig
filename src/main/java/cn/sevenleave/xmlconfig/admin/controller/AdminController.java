package cn.sevenleave.xmlconfig.admin.controller;

import cn.sevenleave.xmlconfig.admin.service.IAdminService;
import cn.sevenleave.xmlconfig.utils.model.JsonResult;
import cn.sevenleave.xmlconfig.waiter.model.WaiterInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：admin_info 对应的控制器类.<br>
 *
 * @author SevenLeave
 * @date 2018-07-31 11:27
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private IAdminService adminService;

    /**
     * 描述：到新增waiter页面
     *
     * @return
     */
    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd() {
        return "admin/addWaiter";
    }

    /**
     * 描述：新增waiter
     *
     * @param waiterInfo 服务员信息
     * @return 新增结果
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public JsonResult add(@RequestBody WaiterInfo waiterInfo) throws Exception {
        int rows = adminService.addWaiter(waiterInfo);
        if (rows == 1) {
            return JsonResult.success("新增waiter成功！");
        } else {
            return JsonResult.fail("新增waiter失败！");
        }
    }

}
