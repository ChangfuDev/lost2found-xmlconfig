package cn.sevenleave.xmlconfig.business.user.controller;

import cn.sevenleave.xmlconfig.business.lost.cache.record.model.LostCacheRecord;
import cn.sevenleave.xmlconfig.business.lost.cache.record.service.ILostCacheRecordService;
import cn.sevenleave.xmlconfig.business.lost.release.record.dto.LostReleaseRecordDto;
import cn.sevenleave.xmlconfig.business.lost.release.record.model.LostReleaseRecord;
import cn.sevenleave.xmlconfig.business.lost.release.record.service.ILostReleaseRecordService;
import cn.sevenleave.xmlconfig.business.user.model.SysUser;
import cn.sevenleave.xmlconfig.business.user.service.ISysUserService;
import cn.sevenleave.xmlconfig.support.constants.LostCacheRecordStatusEnum;
import cn.sevenleave.xmlconfig.support.constants.LostReleaseRecordStatusEnum;
import cn.sevenleave.xmlconfig.support.model.JsonResult;
import cn.sevenleave.xmlconfig.support.model.PageRequest;
import cn.sevenleave.xmlconfig.support.model.PageResponse;
import cn.sevenleave.xmlconfig.support.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-13
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);
    private static final Logger AOP_LOGGER = LoggerFactory.getLogger("aopLogger");

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ILostReleaseRecordService lostReleaseRecordService;
    @Autowired
    private ILostCacheRecordService lostCacheRecordService;

    /**
     * 描述：跳转至用户注册页面
     *
     * @return
     * @throws Exception
     */
    // @Log(name = "AOP-Log（用户注册）")
    @RequestMapping(value = "/toRegister", method = RequestMethod.GET)
    public String toRegister() throws Exception {
        AOP_LOGGER.info(LocalDateTime.now().toString() + " - " + "/toRegister");
        return "/business/user/register";
    }

    /**
     * 描述：新增用户
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    // @AuthCheck
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult register(@RequestBody SysUser sysUser) throws Exception {
        int rows = sysUserService.addUser(sysUser);
        if (rows == 1) {
            return JsonResult.success("用户注册成功！");
        } else {
            return JsonResult.fail("用户注册失败！");
        }
    }

    /**
     * 描述：用户登录信息验证
     * todo 没有做用户登录后的跳转及其他逻辑
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(@RequestBody SysUser sysUser) throws Exception {
        boolean existed = sysUserService.isUserExisted(sysUser);
        if (existed) {
            return JsonResult.success("用户信息匹配成功,允许登录！");
        } else {
            return JsonResult.fail("用户信息匹配失败,拒绝登录！");
        }
    }

    /**
     * 描述：用户查询正式发布的“招领”记录
     *
     * @param pageRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/record/release/query", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<LostReleaseRecordDto> queryRelease(@RequestBody PageRequest pageRequest) throws Exception {
        // 设置用户只能查看正在发布中的正式招领记录
        pageRequest.getParamMap().put("status", LostReleaseRecordStatusEnum.STATUS_PUBLISHING);
        List<LostReleaseRecordDto> lostReleaseRecordDtoList = lostReleaseRecordService.getLostReleaseRecordDtoList(pageRequest);
        PageResponse<LostReleaseRecordDto> lostReleaseRecordPageResponse = new PageResponse<>(lostReleaseRecordDtoList);
        return lostReleaseRecordPageResponse;
    }

    /**
     * 描述：用户提交“招领”记录
     *
     * @param lostCacheRecord
     * @param userUuid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/record/cache/issue", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult issueCache(@RequestBody LostCacheRecord lostCacheRecord, @RequestParam String userUuid) throws Exception {
        lostCacheRecord.setUuid(StringUtils.uuid());
        lostCacheRecord.setUserUuid(userUuid);
        lostCacheRecord.setStatus(LostCacheRecordStatusEnum.STATUS_UNPROCESSED.getStatus());
        int rows = lostCacheRecordService.addLostCacheRecord(lostCacheRecord);
        if (rows == 1) {
            return JsonResult.success("招领信息提交成功！");
        } else {
            return JsonResult.fail("招领记录提交失败！");
        }
    }

    /**
     * 描述：用户查询自己提交的“招领”记录
     *
     * @param pageRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/record/issue/query", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<LostCacheRecord> queryIssue(@RequestBody PageRequest pageRequest) throws Exception {
        List<LostCacheRecord> lostCacheRecordList = lostCacheRecordService.getLostCacheRecordList(pageRequest);
        return new PageResponse<>(lostCacheRecordList);
    }

    /**
     * 描述：用户申请下架自己提交过的招领信息
     *
     * @param releaseRecordUuid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/record/release/request/offline", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult offlineRelease(@RequestParam String releaseRecordUuid) throws Exception {
        // 1.查询自己提交的已经正式发布的招领记录,并把正式招领信息的状态修改为申请下架中
        LostReleaseRecord lostReleaseRecord = lostReleaseRecordService.getLostReleaseRecordByUuid(releaseRecordUuid);
        lostReleaseRecord.setStatus(LostReleaseRecordStatusEnum.STATUS_REQUEST_OFFLINE.getStatus());
        int modifyReleaseRows = lostReleaseRecordService.modifyLostReleaseRecord(lostReleaseRecord);
        // 2.查询招领记录对应的缓存提交记录,并修改对应的状态
        LostCacheRecord lostCacheRecord = lostCacheRecordService.getLostCacheRecordByUuid(lostReleaseRecord.getCacheRecordUuid());
        lostCacheRecord.setStatus(LostCacheRecordStatusEnum.STATUS_REQUEST_OFFLINE.getStatus());
        int modifyCacheRows = lostCacheRecordService.modifyLostCacheRecord(lostCacheRecord);
        // 3.判断
        if (modifyReleaseRows == 1 && modifyCacheRows == 1) {
            // 申请成功
            return JsonResult.success("申请提交成功！");
        } else {
            // 申请失败,内部出错
            return JsonResult.fail("申请提交处理失败！");
        }
    }


}
