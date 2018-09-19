package cn.sevenleave.xmlconfig.business.owner.service.impl;

import cn.sevenleave.xmlconfig.business.owner.mapper.SysOwnerMapper;
import cn.sevenleave.xmlconfig.business.owner.model.SysOwner;
import cn.sevenleave.xmlconfig.business.owner.service.ISysOwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-16
 */
@Service
public class SysOwnerServiceImpl implements ISysOwnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysOwnerServiceImpl.class);
    @Autowired
    private SysOwnerMapper sysOwnerMapper;

    /**
     * 描述：保存物主信息
     *
     * @param sysOwner
     * @return
     * @throws Exception
     */
    @Override
    public int addSysOwner(SysOwner sysOwner) throws Exception {
        int rows = sysOwnerMapper.insert(sysOwner);
        return rows;
    }
}
