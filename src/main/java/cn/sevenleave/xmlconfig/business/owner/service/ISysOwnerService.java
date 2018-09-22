package cn.sevenleave.xmlconfig.business.owner.service;

import cn.sevenleave.xmlconfig.business.owner.model.SysOwner;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-16
 */
public interface ISysOwnerService {

    /**
     * 描述：保存物主信息
     *
     * @param sysOwner
     * @return
     * @throws Exception
     */
    int addSysOwner(SysOwner sysOwner) throws Exception;
}
