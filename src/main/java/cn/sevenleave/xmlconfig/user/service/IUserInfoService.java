package cn.sevenleave.xmlconfig.user.service;

import cn.sevenleave.xmlconfig.user.model.UserInfo;

/**
 * @author: SevenLeave
 * @date: 2018-07-29 14:24
 */
public interface IUserInfoService {

    /**
     * 描述：添加用户
     * @param userInfo
     * @return
     */
    int addUser(UserInfo userInfo) throws Exception;
    
    /**
     * 描述：判断用户是否存在
     * @param userInfo
     * @return
     * @throws Exception
     */
    boolean isUserExisted(UserInfo userInfo) throws Exception;
}
