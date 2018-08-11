package cn.sevenleave.xmlconfig.user.controller;

import cn.sevenleave.xmlconfig.system.aspect.authcheck.AuthCheck;
import cn.sevenleave.xmlconfig.system.aspect.log.Log;
import cn.sevenleave.xmlconfig.user.model.UserInfo;
import cn.sevenleave.xmlconfig.user.service.IUserInfoService;
import cn.sevenleave.xmlconfig.utils.model.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * @author: SevenLeave
 * @date: 2018-07-29 14:30
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    private static final Logger aopLogger = LoggerFactory.getLogger("aopLogger");
    
    @Autowired
    private IUserInfoService userInfoService;
    
    /**
     * 描述：到用户注册页
     *
     * @return
     */
    @Log(name = "我的Log-aspect")
    @RequestMapping(value = "/toRegister", method = RequestMethod.GET)
    public String toRegister() throws Exception {
        aopLogger.debug(LocalDateTime.now().toString() + " - " + "/toRegister");
        return "user/registerUser";
    }
    
    /**
     * 描述：用户注册
     *
     * @param userInfo
     * @return
     */
    @AuthCheck  // 用户认证
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JsonResult register(@RequestBody UserInfo userInfo) throws Exception {
        int rows = userInfoService.addUser(userInfo);
        if (rows == 1) {
            return JsonResult.success("新增成功！");
        } else {
            return JsonResult.fail("新增失败！");
        }
    }
    
    /**
     * 描述：用户登录验证
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(@RequestBody UserInfo userInfo) throws Exception {
        boolean result = userInfoService.isUserExisted(userInfo);
        if (result) {
            return JsonResult.success("匹配成功！");
        } else {
            return JsonResult.fail("匹配失败！");
        }
    }
    
    
}
