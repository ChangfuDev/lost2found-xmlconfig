package cn.sevenleave.xmlconfig.waiter.service.impl;

import cn.sevenleave.xmlconfig.cache.record.mapper.CacheRecordMapper;
import cn.sevenleave.xmlconfig.waiter.mapper.WaiterInfoMapper;
import cn.sevenleave.xmlconfig.waiter.service.IWaiterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SevenLeave
 * @date 2018-07-31 10:54
 */
@Service
public class WaiterServiceImpl implements IWaiterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaiterServiceImpl.class);

    @Autowired
    private WaiterInfoMapper waiterInfoMapper;

    @Autowired
    private CacheRecordMapper cacheRecordMapper;

}
