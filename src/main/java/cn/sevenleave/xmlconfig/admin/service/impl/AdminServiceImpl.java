package cn.sevenleave.xmlconfig.admin.service.impl;

import cn.sevenleave.xmlconfig.admin.mapper.AdminInfoMapper;
import cn.sevenleave.xmlconfig.admin.service.IAdminService;
import cn.sevenleave.xmlconfig.utils.encrypt.PasswordStorage;
import cn.sevenleave.xmlconfig.utils.util.StringUtils;
import cn.sevenleave.xmlconfig.waiter.mapper.WaiterInfoMapper;
import cn.sevenleave.xmlconfig.waiter.model.WaiterInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SevenLeave
 * @date 2018-07-31 11:23
 */
@Service
public class AdminServiceImpl implements IAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private WaiterInfoMapper waiterInfoMapper;

    @Autowired
    private AdminInfoMapper adminInfoMapper;

    /**
     * 描述：新增waiter
     *
     * @param waiterInfo 服务员信息
     * @return 新增的行数
     * @throws Exception
     */
    @Override
    public int addWaiter(WaiterInfo waiterInfo) throws Exception {
        waiterInfo.setUuid(StringUtils.uuid());
        // 密码加盐hash
        waiterInfo.setWaiterPsd(PasswordStorage.createHash(waiterInfo.getWaiterPsd()));
        int rows = waiterInfoMapper.insert(waiterInfo);
        return rows;
    }
}
