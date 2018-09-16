package cn.sevenleave.xmlconfig.business.lost.cache.check.service;

import cn.sevenleave.xmlconfig.business.lost.cache.check.model.LostCacheCheck;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-15
 */
public interface ILostCacheCheckService {


    /**
     * 描述：新增对“招领”信息的审核操作记录
     *
     * @param lostCacheCheck
     * @return
     * @throws Exception
     */
    int addLostCacheCheckRecord(LostCacheCheck lostCacheCheck) throws Exception;

}
