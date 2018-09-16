package cn.sevenleave.xmlconfig.business.waiter.service.impl;

import cn.sevenleave.xmlconfig.business.waiter.mapper.SysWaiterMapper;
import cn.sevenleave.xmlconfig.business.waiter.model.SysWaiter;
import cn.sevenleave.xmlconfig.business.waiter.service.ISysWaiterService;
import cn.sevenleave.xmlconfig.support.encrypt.PasswordStorage;
import cn.sevenleave.xmlconfig.support.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-15
 */
@Service
public class SysWaiterServiceImpl implements ISysWaiterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysWaiterServiceImpl.class);

    @Autowired
    private SysWaiterMapper sysWaiterMapper;


    /**
     * 描述：新增waiter
     *
     * @param sysWaiter
     * @return
     * @throws Exception
     */
    @Override
    public int addWaiter(SysWaiter sysWaiter) throws Exception {
        // 生成uuid
        sysWaiter.setUuid(StringUtils.uuid());
        // 密码加盐hash
        sysWaiter.setPassword(PasswordStorage.createHash(sysWaiter.getPassword()));
        int rows = sysWaiterMapper.insert(sysWaiter);
        return rows;
    }
}
