package cn.sevenleave.xmlconfig.business.lost.release.update.service;

import cn.sevenleave.xmlconfig.business.lost.release.update.model.LostReleaseUpdate;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-16
 */
public interface ILostReleaseUpdateService {

    /**
     * 描述：新增对正式“招领”记录的操作记录
     *
     * @param lostReleaseUpdate
     * @return
     * @throws Exception
     */
    int addLostReleaseUpdate(LostReleaseUpdate lostReleaseUpdate) throws Exception;
}
