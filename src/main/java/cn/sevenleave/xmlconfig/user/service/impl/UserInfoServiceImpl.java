package cn.sevenleave.xmlconfig.user.service.impl;

import cn.sevenleave.xmlconfig.user.mapper.UserInfoMapper;
import cn.sevenleave.xmlconfig.user.model.UserInfo;
import cn.sevenleave.xmlconfig.user.service.IUserInfoService;
import cn.sevenleave.xmlconfig.utils.encrypt.PasswordStorage;
import cn.sevenleave.xmlconfig.utils.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author SevenLeave
 * @date 2018-07-29 14:27
 */
@Service
@CacheConfig(cacheNames = "redisCache")
public class UserInfoServiceImpl implements IUserInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 描述：添加用户
     *
     * @param userInfo
     * @return
     */
    @Override
    @CacheEvict(key = "#root.target.redisUserInfoServiceImplKeyName()", allEntries = true)
    public int addUser(UserInfo userInfo) throws Exception {
        userInfo.setUuid(StringUtils.uuid());
        // 密码加盐hash
        userInfo.setUserPsd(PasswordStorage.createHash(userInfo.getUserPsd()));
        int rows = userInfoMapper.insert(userInfo);
        return rows;
    }

    /**
     * 描述：判断用户信息是否匹配
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    @Override
    @Cacheable(key = "#root.target.redisUserInfoServiceImplKeyName()")
    public boolean isUserExisted(UserInfo userInfo) throws Exception {
        // example查询
        Example example = new Example(UserInfo.class);
        example.createCriteria().andEqualTo("userName", userInfo.getUserName());
        UserInfo user = userInfoMapper.selectOneByExample(example);
        if (user == null) {
            // 用户名不存在
            return false;
        } else {
            // 判断密码是否正确
            return PasswordStorage.verifyPassword(userInfo.getUserPsd(), user.getUserPsd());
        }
    }

    /**
     * 描述：这个方法用于给redis的缓存提供自定义的key值
     *
     * @return
     */
    public String redisUserInfoServiceImplKeyName() {
        return "UserInfoServiceImpl-key";
    }

}
