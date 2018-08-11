package cn.sevenleave.xmlconfig.admin.service;

import cn.sevenleave.xmlconfig.waiter.model.WaiterInfo;

/**
 * @author SevenLeave
 * @date 2018-07-31 11:23
 */
public interface IAdminService {

    /**
     * 描述：新增waiter
     *
     * @param waiterInfo 服务员信息
     * @return 新增的行数
     * @throws Exception
     */
    int addWaiter(WaiterInfo waiterInfo) throws Exception;
}
