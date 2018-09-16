package cn.sevenleave.xmlconfig.business.admin.service.impl;

import cn.sevenleave.xmlconfig.business.admin.mapper.SysAdminMapper;
import cn.sevenleave.xmlconfig.business.admin.service.ISysAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-13
 */
@Service
public class SysAdminServiceImpl implements ISysAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysAdminServiceImpl.class);

    @Autowired
    private SysAdminMapper adminMapper;

}
