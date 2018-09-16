package cn.sevenleave.xmlconfig.business.waiter.service;

import cn.sevenleave.xmlconfig.business.waiter.model.SysWaiter;

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

}
