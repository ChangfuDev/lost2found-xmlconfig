package cn.sevenleave.xmlconfig.business.lost.release.update.service.impl;

import cn.sevenleave.xmlconfig.business.lost.release.update.mapper.LostReleaseUpdateMapper;
import cn.sevenleave.xmlconfig.business.lost.release.update.model.LostReleaseUpdate;
import cn.sevenleave.xmlconfig.business.lost.release.update.service.ILostReleaseUpdateService;
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
public class LostReleaseUpdateServiceImpl implements ILostReleaseUpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LostReleaseUpdateServiceImpl.class);

    @Autowired
    private LostReleaseUpdateMapper lostReleaseUpdateMapper;

    /**
     * 描述：新增对正式“招领”记录的操作记录
     *
     * @param lostReleaseUpdate
     * @return
     * @throws Exception
     */
    @Override
    public int addLostReleaseUpdate(LostReleaseUpdate lostReleaseUpdate) throws Exception {
        int rows = lostReleaseUpdateMapper.insertSelective(lostReleaseUpdate);
        return rows;
    }
}
