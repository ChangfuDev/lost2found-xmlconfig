package cn.sevenleave.xmlconfig.business.waiter.controller;

import cn.sevenleave.xmlconfig.business.lost.cache.check.service.ILostCacheCheckService;
import cn.sevenleave.xmlconfig.business.lost.cache.record.model.LostCacheRecord;
import cn.sevenleave.xmlconfig.business.lost.cache.record.service.ILostCacheRecordService;
import cn.sevenleave.xmlconfig.business.lost.release.record.dto.LostReleaseRecordDto;
import cn.sevenleave.xmlconfig.business.lost.release.record.service.ILostReleaseRecordService;
import cn.sevenleave.xmlconfig.business.lost.release.update.service.ILostReleaseUpdateService;
import cn.sevenleave.xmlconfig.business.owner.model.SysOwner;
import cn.sevenleave.xmlconfig.business.owner.service.ISysOwnerService;
import cn.sevenleave.xmlconfig.business.waiter.service.ISysWaiterService;
import cn.sevenleave.xmlconfig.support.model.JsonResult;
import cn.sevenleave.xmlconfig.support.model.PageRequest;
import cn.sevenleave.xmlconfig.support.model.PageResponse;
import cn.sevenleave.xmlconfig.support.model.ServiceMessage;
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
        // 1.处理
        ServiceMessage serviceMessage = sysWaiterService.checkCacheRecord(cacheRecordUuid, checkCommand, waiterUuid);
        // 2.判断并返回
        boolean success = serviceMessage.isSuccess();
        if (success) {
            // 操作成功
            return JsonResult.success(serviceMessage.getMessage());
        } else {
            // 操作失败
            return JsonResult.fail(serviceMessage.getMessage());
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
    @RequestMapping(value = "/record/release/update/offline", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult releaseRequestOffline(@RequestParam String releaseRecordUuid, @RequestParam String waiterUuid) throws Exception {
        // 1.处理
        ServiceMessage serviceMessage = sysWaiterService.updateReleaseRecordToOffline(releaseRecordUuid, waiterUuid);
        // 2.判断并返回
        boolean success = serviceMessage.isSuccess();
        if (success) {
            return JsonResult.success(serviceMessage.getMessage());
        } else {
            return JsonResult.fail(serviceMessage.getMessage());
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
        // 1.封装对象
        sysOwner.setReleaseRecordUuid(releaseRecordUuid);
        // 2.处理
        ServiceMessage serviceMessage = sysWaiterService.updateReleaseRecordToFound(releaseRecordUuid, waiterUuid, sysOwner);
        // 3.判断并返回
        boolean success = serviceMessage.isSuccess();
        if (success) {
            return JsonResult.success(serviceMessage.getMessage());
        } else {
            return JsonResult.fail(serviceMessage.getMessage());
        }
    }

}
