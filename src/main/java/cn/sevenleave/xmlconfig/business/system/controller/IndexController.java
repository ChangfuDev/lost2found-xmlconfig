package cn.sevenleave.xmlconfig.business.system.controller;

import cn.sevenleave.xmlconfig.business.user.model.SysUser;
import cn.sevenleave.xmlconfig.support.model.ErrorInfo;
import cn.sevenleave.xmlconfig.support.model.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：示例程序,有各种请求参数接收的demo和文件接收的demo
 *
 * @author: SevenLeave
 * @date: 2018-07-15 17:31
 */
@Controller
@RequestMapping
public class IndexController {

    /**
     * 描述：日志记录器
     */
    private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    /**
     * 描述：跳转到首页
     *
     * @return
     */
    @RequestMapping(value = {"", "/"})
    public String index() {
        return "/business/system/index";
    }

    /**
     * 描述：单文件上传+表单数据
     *
     * @param name
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String processUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        System.out.println("name = " + name);

        if (!file.isEmpty()) {
            String fileRealName = file.getOriginalFilename();
            String savedDir = request.getSession().getServletContext().getRealPath("") + "/uploadFiles/";
            File savedFile = new File(savedDir, fileRealName);
            if (!savedFile.exists()) {
                savedFile.mkdirs();
            }
            try {
                file.transferTo(savedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "/business/system/result";
    }

    /**
     * 描述：多文件上传+表单数据
     *
     * @param name
     * @param files
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
    public String processUploads(@RequestParam("name") String name, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        System.out.println("name = " + name);

        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                String fileRealName = file.getOriginalFilename();
                String savedDir = request.getSession().getServletContext().getRealPath("") + "/uploadFiles/";
                File savedFile = new File(savedDir, fileRealName);
                if (!savedFile.exists()) {
                    savedFile.mkdirs();
                }
                try {
                    file.transferTo(savedFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "/business/system/result";
    }

    /**
     * 描述：直接获取GET传送的参数
     *
     * @param firstname
     * @param lastname
     * @return
     */
    @RequestMapping(value = "/get/method1", method = RequestMethod.GET)
    public String getMethod1(@RequestParam(name = "firstname") String firstname, @RequestParam(name = "lastname") String lastname) {
        System.out.println("firstname = " + firstname);
        System.out.println("lastname = " + lastname);
        return "/business/system/result";
    }

    /**
     * 描述：获取GET传送的参数,并转化为Model
     *
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/get/method2", method = RequestMethod.GET)
    public String getMethod2(@ModelAttribute SysUser sysUser) {
        System.out.println(sysUser.getUsername());
        return "/business/system/result";
    }

    /**
     * 描述：直接获取POST的urlParams
     *
     * @param firstname
     * @param lastname
     * @return
     */
    @RequestMapping(value = "post/method1", method = RequestMethod.POST)
    public String postMethod1(@RequestParam(name = "firstname") String firstname, @RequestParam(name = "lastname") String lastname) {
        System.out.println("firstname = " + firstname);
        System.out.println("lastname = " + lastname);
        return "/business/system/result";
    }

    /**
     * 描述：获取POST传送的urlParams,并转化为Model
     *
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/post/method2", method = RequestMethod.POST)
    public String postMethod2(@ModelAttribute SysUser sysUser) {
        System.out.println(sysUser.getUsername());
        return "/business/system/result";
    }

    /**
     * 描述：获取POST的urlParams和form-data
     *
     * @param cookieId
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/post/method3", method = RequestMethod.POST)
    public String postMethod3(@RequestParam(name = "cookieId") String cookieId, @ModelAttribute SysUser sysUser, @RequestParam(name = "overflow") String overflow) {
        System.out.println("cookieId = " + cookieId);
        System.out.println("sysUser.getUsername() = " + sysUser.getUsername());
        System.out.println("overflow = " + overflow);
        return "/business/system/result";
    }

    /**
     * 描述：获取POST的urlParams和form-data
     *
     * @param cookieId
     * @param firstname
     * @param lastname
     * @param overflow
     * @return
     */
    @RequestMapping(value = "/post/method4", method = RequestMethod.POST)
    public String postMethod4(@RequestParam(name = "cookieId") String cookieId, @RequestParam(name = "firstname") String firstname, @RequestParam(name = "lastname") String lastname, @RequestParam(name = "overflow") String overflow) {
        System.out.println("cookieId = " + cookieId);
        System.out.println("firstname = " + firstname);
        System.out.println("lastname = " + lastname);
        System.out.println("overflow = " + overflow);
        return "/business/system/result";
    }

    /**
     * 描述：获取POST的urlParams和x-www-urlencoded
     *
     * @param cookieId
     * @param firstname
     * @param lastname
     * @param overflow
     * @return
     */
    @RequestMapping(value = "/post/method5", method = RequestMethod.POST)
    public String postMethod5(@RequestParam(name = "cookieId") String cookieId, @RequestParam(name = "firstname") String firstname, @RequestParam(name = "lastname") String lastname, @RequestParam(name = "overflow") String overflow) {
        System.out.println("cookieId=" + cookieId);
        System.out.println("firstname = " + firstname);
        System.out.println("lastname = " + lastname);
        System.out.println("overflow = " + overflow);
        return "/business/system/result";
    }

    /**
     * 描述：获取POST的urlParams和application/json
     *
     * @param cookieId
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/post/method6", method = RequestMethod.POST)
    public String postMethod6(@RequestParam(name = "cookieId") String cookieId, @RequestBody SysUser sysUser) {
        System.out.println("cookieId = " + cookieId);
        System.out.println("sysUser = " + sysUser.getUsername());
        return "/business/system/result";
    }

    /**
     * 描述：获取POST的urlParams和application/json
     *
     * @param cookieId
     * @param sysUsers
     * @return
     */
    @RequestMapping(value = "/post/method7", method = RequestMethod.POST)
    public String postMethod7(@RequestParam(name = "cookieId") String cookieId, @RequestBody SysUser[] sysUsers) {
        System.out.println("cookieId = " + cookieId);
        System.out.println("sysUsers.length = " + sysUsers.length);
        return "/business/system/result";
    }

    /**
     * 描述：携带参数返回视图
     *
     * @param cookieId
     * @param model
     * @return
     */
    @RequestMapping(value = "/return/method1", method = RequestMethod.GET)
    public String returnMethod1(@RequestParam(name = "cookieId") String cookieId, Model model) {
        System.out.println("cookieId = " + cookieId);
        model.addAttribute("k1", "v1");
        model.addAttribute("k2", "v2");
        model.addAttribute("k3", "v3");
        return "/business/system/param";
    }

    /**
     * 描述：携带参数返回视图
     *
     * @param cookieId
     * @return
     */
    @RequestMapping(value = "/return/method2", method = RequestMethod.GET)
    public ModelAndView returnMethod2(@RequestParam(name = "cookieId") String cookieId) {
        System.out.println("cookieId = " + cookieId);
        ModelAndView mv = new ModelAndView("/system/param");
        mv.addObject("k1", "v1");
        mv.addObject("k2", "v2");
        mv.addObject("k3", "v3");
        return mv;
    }

    /**
     * 描述：返回json格式的数据
     *
     * @param cookieId
     * @return
     */
    @RequestMapping(value = "/return/method3", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult returnMethod3(@RequestParam(name = "cookieId") String cookieId) {
        System.out.println("cookieId = " + cookieId);

        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(JsonResult.STATUS_FAIL);
        jsonResult.setErrorCode("404");
        jsonResult.setMsg("未找到！");
        List<ErrorInfo> errorInfoList = new ArrayList<>();
        ErrorInfo errorInfo1 = new ErrorInfo();
        errorInfo1.setField("异常一");
        errorInfo1.setInfo("未找到对应的Class");
        ErrorInfo errorInfo2 = new ErrorInfo();
        errorInfo2.setField("异常二");
        errorInfo2.setInfo("未能处理错误");
        errorInfoList.add(errorInfo1);
        errorInfoList.add(errorInfo2);
        jsonResult.setErrors(errorInfoList);
        Map<Integer, String> paramMap = new HashMap<>(10);
        paramMap.put(1, "Jack Lee");
        paramMap.put(2, "Tom Kum");
        jsonResult.setData(paramMap);
        return jsonResult;
    }

}
