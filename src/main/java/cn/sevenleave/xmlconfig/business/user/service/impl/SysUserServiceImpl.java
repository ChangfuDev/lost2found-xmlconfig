package cn.sevenleave.xmlconfig.business.user.service.impl;

import cn.sevenleave.xmlconfig.business.user.mapper.SysUserMapper;
import cn.sevenleave.xmlconfig.business.user.model.SysUser;
import cn.sevenleave.xmlconfig.business.user.service.ISysUserService;
import cn.sevenleave.xmlconfig.support.encrypt.PasswordStorage;
import cn.sevenleave.xmlconfig.support.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-13
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 描述：新增用户
     * todo 没有做信息验证,用户信息是否已经存在
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    @Override
    public int addUser(SysUser sysUser) throws Exception {
        sysUser.setUuid(StringUtils.uuid());
        // 密码加盐hash
        sysUser.setPassword(PasswordStorage.createHash(sysUser.getPassword()));
        int rows = userMapper.insert(sysUser);
        return rows;
    }

    /**
     * 描述：判断用户名与密码是否匹配
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    @Override
    public boolean isUserExisted(SysUser sysUser) throws Exception {
        // Example查询
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("username", sysUser.getUsername());
        SysUser user = userMapper.selectOneByExample(example);
        if (user == null) {
            // 用户名不存在
            return false;
        } else {
            // 用户名存在,验证密码的正确性
            return PasswordStorage.verifyPassword(sysUser.getPassword(), user.getPassword());
        }
    }
}
