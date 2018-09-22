package cn.sevenleave.xmlconfig.business.waiter.service;

import cn.sevenleave.xmlconfig.business.owner.model.SysOwner;
import cn.sevenleave.xmlconfig.business.waiter.model.SysWaiter;
import cn.sevenleave.xmlconfig.support.model.ServiceMessage;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-15
 */
public interface ISysWaiterService {


    /**
     * 描述：新增waiter
     *
     * @param sysWaiter
     * @return
     * @throws Exception
     */
    int addWaiter(SysWaiter sysWaiter) throws Exception;

    /**
     * 描述：服务员审核用户提交的招领申请的缓存记录
     *
     * @param cacheRecordUuid
     * @param checkCommand
     * @param waiterUuid
     * @return
     * @throws Exception
     */
    ServiceMessage checkCacheRecord(String cacheRecordUuid, String checkCommand, String waiterUuid) throws Exception;

    /**
     * 描述：物主找到失物,服务员进行相应的处理
     *
     * @param releaseRecordUuid
     * @param waiterUuid
     * @param sysOwner
     * @return
     * @throws Exception
     */
    ServiceMessage updateReleaseRecordToFound(String releaseRecordUuid, String waiterUuid, SysOwner sysOwner) throws Exception;

    /**
     * 描述：用户请求下架自己发布的正式招领信息,服务员同意请求
     *
     * @param releaseRecordUuid
     * @param waiterUuid
     * @return
     * @throws Exception
     */
    ServiceMessage updateReleaseRecordToOffline(String releaseRecordUuid, String waiterUuid) throws Exception;


}
