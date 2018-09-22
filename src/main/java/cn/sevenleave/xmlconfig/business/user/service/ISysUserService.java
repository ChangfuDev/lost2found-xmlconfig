package cn.sevenleave.xmlconfig.business.user.service;

import cn.sevenleave.xmlconfig.business.user.model.SysUser;
import cn.sevenleave.xmlconfig.support.model.ServiceMessage;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-13
 */
public interface ISysUserService {

    /**
     * 描述：新增用户
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    int addUser(SysUser sysUser) throws Exception;

    /**
     * 描述：查询用户是否存在
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    boolean isUserExisted(SysUser sysUser) throws Exception;

    /**
     * 描述：用户主动请求下架自己发布的正式招领记录
     *
     * @param releaseRecordUuid
     * @return
     * @throws Exception
     */
    ServiceMessage requestReleaseRecordOffline(String releaseRecordUuid) throws Exception;

}
