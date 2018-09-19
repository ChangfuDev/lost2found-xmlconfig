package cn.sevenleave.xmlconfig.business.waiter.service.impl;

import cn.sevenleave.xmlconfig.business.lost.cache.check.model.LostCacheCheck;
import cn.sevenleave.xmlconfig.business.lost.cache.check.service.ILostCacheCheckService;
import cn.sevenleave.xmlconfig.business.lost.cache.record.model.LostCacheRecord;
import cn.sevenleave.xmlconfig.business.lost.cache.record.service.ILostCacheRecordService;
import cn.sevenleave.xmlconfig.business.lost.release.record.model.LostReleaseRecord;
import cn.sevenleave.xmlconfig.business.lost.release.record.service.ILostReleaseRecordService;
import cn.sevenleave.xmlconfig.business.lost.release.update.model.LostReleaseUpdate;
import cn.sevenleave.xmlconfig.business.lost.release.update.service.ILostReleaseUpdateService;
import cn.sevenleave.xmlconfig.business.owner.model.SysOwner;
import cn.sevenleave.xmlconfig.business.owner.service.impl.SysOwnerServiceImpl;
import cn.sevenleave.xmlconfig.business.waiter.mapper.SysWaiterMapper;
import cn.sevenleave.xmlconfig.business.waiter.model.SysWaiter;
import cn.sevenleave.xmlconfig.business.waiter.service.ISysWaiterService;
import cn.sevenleave.xmlconfig.support.constants.LostCacheCheckStatusEnum;
import cn.sevenleave.xmlconfig.support.constants.LostCacheRecordStatusEnum;
import cn.sevenleave.xmlconfig.support.constants.LostReleaseRecordStatusEnum;
import cn.sevenleave.xmlconfig.support.constants.LostReleaseUpdateStatusEnum;
import cn.sevenleave.xmlconfig.support.exception.TransactionFlagException;
import cn.sevenleave.xmlconfig.support.model.ServiceMessage;
import cn.sevenleave.xmlconfig.support.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-15
 */
@Service
public class SysWaiterServiceImpl implements ISysWaiterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysWaiterServiceImpl.class);

    @Autowired
    private SysWaiterMapper sysWaiterMapper;
    @Autowired
    private SysOwnerServiceImpl sysOwnerServiceImpl;
    @Autowired
    private ILostCacheRecordService lostCacheRecordService;
    @Autowired
    private ILostCacheCheckService lostCacheCheckService;
    @Autowired
    private ILostReleaseRecordService lostReleaseRecordService;
    @Autowired
    private ILostReleaseUpdateService lostReleaseUpdateService;


    /**
     * 描述：新增waiter
     *
     * @param sysWaiter
     * @return
     * @throws Exception
     */
    @Override
    public int addWaiter(SysWaiter sysWaiter) throws Exception {
        int rows = sysWaiterMapper.insert(sysWaiter);
        return rows;
    }

    /**
     * 描述：服务员审核用户提交的招领申请的缓存记录
     *
     * @param cacheRecordUuid
     * @param checkCommand
     * @param waiterUuid
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = TransactionFlagException.class)
    @Override
    public ServiceMessage checkCacheRecord(String cacheRecordUuid, String checkCommand, String waiterUuid) throws Exception {
        // 1.修改要审核的“招领”信息的status为checkCommand
        LostCacheRecord lostCacheRecord = lostCacheRecordService.getLostCacheRecordByUuid(cacheRecordUuid);
        lostCacheRecord.setStatus(checkCommand);
        int modifyCacheRows = lostCacheRecordService.modifyLostCacheRecord(lostCacheRecord);
        // 2.新增服务员的操作记录
        LostCacheCheck lostCacheCheck = new LostCacheCheck();
        lostCacheCheck.setUuid(StringUtils.uuid());
        lostCacheCheck.setWaiterUuid(waiterUuid);
        lostCacheCheck.setHandle(checkCommand);
        lostCacheCheck.setCacheRecordUuid(lostCacheRecord.getUuid());
        int addCheckRows = lostCacheCheckService.addLostCacheCheckRecord(lostCacheCheck);
        // 3.匹配审核结果,服务员是否给予了通过
        boolean pass = false;
        int addReleaseRows = 0;
        if (LostCacheCheckStatusEnum.STATUS_REJECT.getStatus().equals(checkCommand)) {
            // 服务员拒绝了申请
        } else if (LostCacheCheckStatusEnum.STATUS_PASS.getStatus().equals(checkCommand)) {
            // 服务员通过了申请,然后新增正式“招领”的记录
            pass = true;
            LostReleaseRecord lostReleaseRecord = new LostReleaseRecord();
            lostReleaseRecord.setUuid(StringUtils.uuid());
            lostReleaseRecord.setCacheRecordUuid(cacheRecordUuid);
            lostReleaseRecord.setStatus(LostReleaseRecordStatusEnum.STATUS_PUBLISHING.getStatus());
            addReleaseRows = lostReleaseRecordService.addLostReleaseRecord(lostReleaseRecord);
        }

        // 4.判断内部流程的正确性,事务判断
        try {
            if (pass) {
                // 服务员通过审核
                if (modifyCacheRows == 1 && addCheckRows == 1 && addReleaseRows == 1) {
                    // 内部流程正确
                    return ServiceMessage.success("申请已通过！");
                } else {
                    // 内部流程出错
                    throw new TransactionFlagException("内部出错！");
                }
            } else {
                // 服务员拒绝审核
                if (modifyCacheRows == 1 && addCheckRows == 1) {
                    // 内部流程正确
                    return ServiceMessage.success("申请已拒绝！");
                } else {
                    // 内部流程出错
                    throw new TransactionFlagException("内部出错！");
                }
            }
        } catch (TransactionFlagException e) {
            // 手动回滚事务,并返回结果
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ServiceMessage.fail("审核操作失败,内部流程出错！");
        }
    }

    /**
     * 描述：物主找到失物,服务员进行相应的处理
     *
     * @param releaseRecordUuid
     * @param waiterUuid
     * @param sysOwner
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = TransactionFlagException.class)
    @Override
    public ServiceMessage updateReleaseRecordToFound(String releaseRecordUuid, String waiterUuid, SysOwner sysOwner) throws Exception {
        // 1.保存与该记录关联的物主信息
        int addOwnerRows = sysOwnerServiceImpl.addSysOwner(sysOwner);
        // 2.修改上架发布中的招领记录的status为已找到
        LostReleaseRecord lostReleaseRecord = lostReleaseRecordService.getLostReleaseRecordByUuid(releaseRecordUuid);
        lostReleaseRecord.setStatus(LostReleaseRecordStatusEnum.STATUS_FOUND.getStatus());
        int modifyReleaseRows = lostReleaseRecordService.modifyLostReleaseRecord(lostReleaseRecord);
        // 3.修改用户提交的缓存记录的status为已找到
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

        // 5.内部流程的正确性判断,事务判断
        try {
            if (addOwnerRows == 1 && modifyReleaseRows == 1 && modifyCacheRows == 1 && addUpdateRows == 1) {
                // 内部流程正确
                return ServiceMessage.success("处理成功！失物即刻下架！");
            } else {
                // 内部流程错误,抛出异常
                throw new TransactionFlagException("内部流程出错！");
            }
        } catch (TransactionFlagException e) {
            // 手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ServiceMessage.fail("处理失败！内部流程出错！");
        }
    }

    /**
     * 描述：用户请求下架自己发布的正式招领信息,服务员同意请求
     *
     * @param releaseRecordUuid
     * @param waiterUuid
     * @return
     * @throws Exception
     */
    @Override
    public ServiceMessage updateReleaseRecordToOffline(String releaseRecordUuid, String waiterUuid) throws Exception {
        // 1.修改上架中的招领记录的status为OFFLINE
        LostReleaseRecord lostReleaseRecord = lostReleaseRecordService.getLostReleaseRecordByUuid(releaseRecordUuid);
        lostReleaseRecord.setStatus(LostReleaseRecordStatusEnum.STATUS_OFFLINE.getStatus());
        int modifyReleaseRows = lostReleaseRecordService.modifyLostReleaseRecord(lostReleaseRecord);
        // 2.修改用户提交的缓存记录的status为OFFLINE
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
        // 4.内部流程判断,事务判断
        try {
            if (modifyReleaseRows == 1 && modifyCacheRows == 1 && addUpdateRows == 1) {
                // 内部流程正确
                return ServiceMessage.success("处理成功！已同意用户的下架请求！");
            } else {
                // 内部流程错误,抛出异常
                throw new TransactionFlagException("内部流程错误！");
            }
        } catch (TransactionFlagException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ServiceMessage.fail("处理失败！内部流程错误！");
        }
    }
}
