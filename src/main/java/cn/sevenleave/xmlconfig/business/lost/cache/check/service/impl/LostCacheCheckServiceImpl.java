package cn.sevenleave.xmlconfig.business.lost.cache.check.service.impl;

import cn.sevenleave.xmlconfig.business.lost.cache.check.mapper.LostCacheCheckMapper;
import cn.sevenleave.xmlconfig.business.lost.cache.check.model.LostCacheCheck;
import cn.sevenleave.xmlconfig.business.lost.cache.check.service.ILostCacheCheckService;
import cn.sevenleave.xmlconfig.business.lost.cache.record.service.ILostCacheRecordService;
import cn.sevenleave.xmlconfig.business.lost.release.record.service.ILostReleaseRecordService;
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
public class LostCacheCheckServiceImpl implements ILostCacheCheckService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LostCacheCheckServiceImpl.class);

    @Autowired
    private LostCacheCheckMapper cacheCheckMapper;
    @Autowired
    private ILostCacheRecordService lostCacheRecordService;
    @Autowired
    private ILostReleaseRecordService lostReleaseRecordService;


    /**
     * 描述：新增对“招领”信息的审核操作记录
     *
     * @param lostCacheCheck
     * @return
     * @throws Exception
     */
    @Override
    public int addLostCacheCheckRecord(LostCacheCheck lostCacheCheck) throws Exception {
        int rows = cacheCheckMapper.insertSelective(lostCacheCheck);
        return rows;
    }
}
