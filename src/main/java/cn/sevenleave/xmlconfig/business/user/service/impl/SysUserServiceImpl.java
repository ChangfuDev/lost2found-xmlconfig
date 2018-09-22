package cn.sevenleave.xmlconfig.business.user.service.impl;

import cn.sevenleave.xmlconfig.business.lost.cache.record.model.LostCacheRecord;
import cn.sevenleave.xmlconfig.business.lost.cache.record.service.ILostCacheRecordService;
import cn.sevenleave.xmlconfig.business.lost.release.record.model.LostReleaseRecord;
import cn.sevenleave.xmlconfig.business.lost.release.record.service.ILostReleaseRecordService;
import cn.sevenleave.xmlconfig.business.user.mapper.SysUserMapper;
import cn.sevenleave.xmlconfig.business.user.model.SysUser;
import cn.sevenleave.xmlconfig.business.user.service.ISysUserService;
import cn.sevenleave.xmlconfig.support.constants.LostCacheRecordStatusEnum;
import cn.sevenleave.xmlconfig.support.constants.LostReleaseRecordStatusEnum;
import cn.sevenleave.xmlconfig.support.encrypt.PasswordStorage;
import cn.sevenleave.xmlconfig.support.exception.TransactionFlagException;
import cn.sevenleave.xmlconfig.support.model.ServiceMessage;
import cn.sevenleave.xmlconfig.support.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
    @Autowired
    private ILostCacheRecordService lostCacheRecordService;
    @Autowired
    private ILostReleaseRecordService lostReleaseRecordService;

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

    /**
     * 描述：用户主动请求下架自己发布的正式招领记录
     *
     * @param releaseRecordUuid
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = TransactionFlagException.class)
    @Override
    public ServiceMessage requestReleaseRecordOffline(String releaseRecordUuid) throws Exception {
        // 1.修改正式招领信息的status为REQUEST_OFFLINE
        LostReleaseRecord lostReleaseRecord = lostReleaseRecordService.getLostReleaseRecordByUuid(releaseRecordUuid);
        lostReleaseRecord.setStatus(LostReleaseRecordStatusEnum.STATUS_REQUEST_OFFLINE.getStatus());
        int modifyReleaseRows = lostReleaseRecordService.modifyLostReleaseRecord(lostReleaseRecord);
        // 2.修改提交记录的status为REQUEST_OFFLINE
        LostCacheRecord lostCacheRecord = lostCacheRecordService.getLostCacheRecordByUuid(lostReleaseRecord.getCacheRecordUuid());
        lostCacheRecord.setStatus(LostCacheRecordStatusEnum.STATUS_REQUEST_OFFLINE.getStatus());
        int modifyCacheRows = lostCacheRecordService.modifyLostCacheRecord(lostCacheRecord);
        // 3.流程正确性判断,事务判断
        try {
            if (modifyReleaseRows == 1 && modifyCacheRows == 1) {
                return ServiceMessage.success("执行成功！已提交下架请求！");
            } else {
                throw new TransactionFlagException("内部出错！");
            }
        } catch (TransactionFlagException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ServiceMessage.fail("执行失败！内部流程出错！");
        }
    }
}
