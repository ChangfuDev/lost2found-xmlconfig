package cn.sevenleave.xmlconfig.business.waiter.controller;

import cn.sevenleave.xmlconfig.business.lost.cache.check.model.LostCacheCheck;
import cn.sevenleave.xmlconfig.business.lost.cache.check.service.ILostCacheCheckService;
import cn.sevenleave.xmlconfig.business.lost.cache.record.model.LostCacheRecord;
import cn.sevenleave.xmlconfig.business.lost.cache.record.service.ILostCacheRecordService;
import cn.sevenleave.xmlconfig.business.lost.release.record.dto.LostReleaseRecordDto;
import cn.sevenleave.xmlconfig.business.lost.release.record.model.LostReleaseRecord;
import cn.sevenleave.xmlconfig.business.lost.release.record.service.ILostReleaseRecordService;
import cn.sevenleave.xmlconfig.business.lost.release.update.model.LostReleaseUpdate;
import cn.sevenleave.xmlconfig.business.lost.release.update.service.ILostReleaseUpdateService;
import cn.sevenleave.xmlconfig.business.owner.model.SysOwner;
import cn.sevenleave.xmlconfig.business.owner.service.ISysOwnerService;
import cn.sevenleave.xmlconfig.business.waiter.service.ISysWaiterService;
import cn.sevenleave.xmlconfig.support.constants.LostCacheCheckStatusEnum;
import cn.sevenleave.xmlconfig.support.constants.LostCacheRecordStatusEnum;
import cn.sevenleave.xmlconfig.support.constants.LostReleaseRecordStatusEnum;
import cn.sevenleave.xmlconfig.support.constants.LostReleaseUpdateStatusEnum;
import cn.sevenleave.xmlconfig.support.model.JsonResult;
import cn.sevenleave.xmlconfig.support.model.PageRequest;
import cn.sevenleave.xmlconfig.support.model.PageResponse;
import cn.sevenleave.xmlconfig.support.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-15
 */
@Controller
@RequestMapping(value = "/waiter")
public class SysWaiterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysWaiterController.class);

    @Autowired
    private ISysWaiterService sysWaiterService;
    @Autowired
    private ISysOwnerService sysOwnerService;
    @Autowired
    private ILostCacheRecordService lostCacheRecordService;
    @Autowired
    private ILostCacheCheckService lostCacheCheckService;
    @Autowired
    private ILostReleaseRecordService lostReleaseRecordService;
    @Autowired
    private ILostReleaseUpdateService lostReleaseUpdateService;

    /**
     * 描述：查询用户提交的“招领”记录
     *
     * @param pageRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/record/cache/query", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<LostCacheRecord> query(@RequestBody PageRequest pageRequest) throws Exception {
        List<LostCacheRecord> lostCacheRecordList = lostCacheRecordService.getLostCacheRecordList(pageRequest);
        return new PageResponse<>(lostCacheRecordList);
    }

    /**
     * 描述：服务员审核用户提交的“招领”信息
     *
     * @param cacheRecordUuid
     * @param checkCommand
     * @param waiterUuid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/record/cache/check", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult check(@RequestParam String cacheRecordUuid, @RequestParam String checkCommand, @RequestParam String waiterUuid) throws Exception {
        // 1.查询要审核的“招领”信息
        LostCacheRecord lostCacheRecord = lostCacheRecordService.getLostCacheRecordByUuid(cacheRecordUuid);
        // 2.做出审核,修改“招领”信息的状态
        lostCacheRecord.setStatus(checkCommand);
        int modifyCacheRows = lostCacheRecordService.modifyLostCacheRecord(lostCacheRecord);
        // 3.新增服务员的操作记录
        LostCacheCheck lostCacheCheck = new LostCacheCheck();
        lostCacheCheck.setUuid(StringUtils.uuid());
        lostCacheCheck.setWaiterUuid(waiterUuid);
        lostCacheCheck.setHandle(checkCommand);
        lostCacheCheck.setCacheRecordUuid(lostCacheRecord.getUuid());
        int addCheckRows = lostCacheCheckService.addLostCacheCheckRecord(lostCacheCheck);
        // 4.审核结果
        boolean passed = false;
        int addReleaseRows = 0;
        if (LostCacheCheckStatusEnum.STATUS_REJECT.getStatus().equals(checkCommand)) {
            // 审核结果为拒绝
        } else {
            // 通过审核,新增正式“招领”的记录
            passed = true;
            LostReleaseRecord lostReleaseRecord = new LostReleaseRecord();
            lostReleaseRecord.setUuid(StringUtils.uuid());
            lostReleaseRecord.setCacheRecordUuid(cacheRecordUuid);
            lostReleaseRecord.setStatus(LostReleaseRecordStatusEnum.STATUS_PUBLISHING.getStatus());
            addReleaseRows = lostReleaseRecordService.addLostReleaseRecord(lostReleaseRecord);
        }
        // 5.审核操作的情况
        if (passed) {
            // 审核给予通过
            if (modifyCacheRows == 1 && addCheckRows == 1 && addReleaseRows == 1) {
                // 数据正常
                return JsonResult.success("审核操作成功！", LostCacheCheckStatusEnum.STATUS_PASS.getStatus());
            } else {
                // 数据异常
                return JsonResult.fail("500", "内部未知错误,审核操作失败！");
            }
        } else {
            // 审核给予拒绝
            if (modifyCacheRows == 1 && addCheckRows == 1) {
                // 数据正常
                return JsonResult.success("审核操作成功！", LostCacheCheckStatusEnum.STATUS_REJECT.getStatus());
            } else {
                // 数据异常
                return JsonResult.fail("500", "内部未知错误,审核操作失败！");
            }
        }
    }

    /**
     * 描述：服务员查询正式发布的“招领”记录
     *
     * @param pageRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/record/release/query", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<LostReleaseRecordDto> queryRelease(@RequestBody PageRequest pageRequest) throws Exception {
        List<LostReleaseRecordDto> lostReleaseRecordDtoList = lostReleaseRecordService.getLostReleaseRecordDtoList(pageRequest);
        PageResponse<LostReleaseRecordDto> lostReleaseRecordPageResponse = new PageResponse<>(lostReleaseRecordDtoList);
        return lostReleaseRecordPageResponse;
    }

    /**
     * 描述：服务员批准用户提交的申请下架请求
     *
     * @param releaseRecordUuid
     * @param waiterUuid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/record/release/update/offline/request/agree", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult releaseRequestOffline(@RequestParam String releaseRecordUuid, @RequestParam String waiterUuid) throws Exception {
        // 1.查询并修改上架中的招领记录
        LostReleaseRecord lostReleaseRecord = lostReleaseRecordService.getLostReleaseRecordByUuid(releaseRecordUuid);
        lostReleaseRecord.setStatus(LostReleaseRecordStatusEnum.STATUS_OFFLINE.getStatus());
        int modifyReleaseRows = lostReleaseRecordService.modifyLostReleaseRecord(lostReleaseRecord);
        // 2.查询并修改用户提交的缓存记录
        LostCacheRecord lostCacheRecord = lostCacheRecordService.getLostCacheRecordByUuid(lostReleaseRecord.getCacheRecordUuid());
        lostCacheRecord.setStatus(LostCacheRecordStatusEnum.STATUS_OFFLINE.getStatus());
        int modifyCacheRows = lostCacheRecordService.modifyLostCacheRecord(lostCacheRecord);
        // 3.新增服务员的操作记录
        LostReleaseUpdate lostReleaseUpdate = new LostReleaseUpdate();
        lostReleaseUpdate.setUuid(StringUtils.uuid());
        lostReleaseUpdate.setCacheRecordUuid(lostReleaseRecord.getCacheRecordUuid());
        lostReleaseUpdate.setWaiterUuid(waiterUuid);
        lostReleaseUpdate.setHandle(LostReleaseUpdateStatusEnum.STATUS_OFFLINE.getStatus());
        int addUpdateRows = lostReleaseUpdateService.addLostReleaseUpdate(lostReleaseUpdate);
        // 4.判断
        if (modifyReleaseRows == 1 && modifyCacheRows == 1 && addUpdateRows == 1) {
            // 内部执行成功
            return JsonResult.success("用户的申请已经成功下架！");
        } else {
            // 内部执行失败
            return JsonResult.fail("用户的申请在下架处理时发送错误！");
        }
    }

    /**
     * 描述：物主找到失物,服务员进行处理
     *
     * @param releaseRecordUuid
     * @param waiterUuid
     * @param sysOwner
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/record/release/update/found", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult releaseFound(@RequestParam String releaseRecordUuid, @RequestParam String waiterUuid, @RequestBody SysOwner sysOwner) throws Exception {
        // 1.保存该记录关联的物主信息
        sysOwner.setReleaseRecordUuid(releaseRecordUuid);
        int addOwnerRows = sysOwnerService.addSysOwner(sysOwner);
        // 2.查询并修改上架中的招领记录
        LostReleaseRecord lostReleaseRecord = lostReleaseRecordService.getLostReleaseRecordByUuid(releaseRecordUuid);
        lostReleaseRecord.setStatus(LostReleaseRecordStatusEnum.STATUS_FOUND.getStatus());
        int modifyReleaseRows = lostReleaseRecordService.modifyLostReleaseRecord(lostReleaseRecord);
        // 3.查询并修改用户提交的缓存记录
        LostCacheRecord lostCacheRecord = lostCacheRecordService.getLostCacheRecordByUuid(lostReleaseRecord.getCacheRecordUuid());
        lostCacheRecord.setStatus(LostCacheRecordStatusEnum.STATUS_FOUND.getStatus());
        int modifyCacheRows = lostCacheRecordService.modifyLostCacheRecord(lostCacheRecord);
        // 4.新增服务员的操作记录
        LostReleaseUpdate lostReleaseUpdate = new LostReleaseUpdate();
        lostReleaseUpdate.setUuid(StringUtils.uuid());
        lostReleaseUpdate.setCacheRecordUuid(lostReleaseRecord.getCacheRecordUuid());
        lostReleaseUpdate.setWaiterUuid(waiterUuid);
        lostReleaseUpdate.setHandle(LostReleaseUpdateStatusEnum.STATUS_FOUND.getStatus());
        int addUpdateRows = lostReleaseUpdateService.addLostReleaseUpdate(lostReleaseUpdate);
        // 5.判断
        if (addOwnerRows == 1 && modifyReleaseRows == 1 && modifyCacheRows == 1 && addUpdateRows == 1) {
            // 内部流程正确执行
            return JsonResult.success("失物已成功寻回，即刻下架！");
        } else {
            // 内部流程执行出错
            return JsonResult.fail("内部流程出错，未能完成！");
        }
    }

}
