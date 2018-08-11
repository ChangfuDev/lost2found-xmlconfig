package cn.sevenleave.xmlconfig.cache.record.service.impl;

import cn.sevenleave.xmlconfig.cache.record.mapper.CacheRecordMapper;
import cn.sevenleave.xmlconfig.cache.record.model.CacheRecord;
import cn.sevenleave.xmlconfig.cache.record.service.ICacheRecordService;
import cn.sevenleave.xmlconfig.utils.model.PageRequest;
import cn.sevenleave.xmlconfig.utils.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author SevenLeave
 * @date 2018-07-31 14:10
 */
@Service
public class CacheRecordServiceImpl implements ICacheRecordService {

    private static final Logger logger = LoggerFactory.getLogger(CacheRecordServiceImpl.class);

    @Autowired
    private CacheRecordMapper cacheRecordMapper;

    /**
     * 描述：新增失物记录到缓存表
     *
     * @param cacheRecord
     * @return
     */
    @Override
    public int addCacheRecord(CacheRecord cacheRecord, String userUuid) {
        cacheRecord.setUuid(StringUtils.uuid());
        cacheRecord.setUserUuid(userUuid);
        // 0-未处理
        cacheRecord.setStatus("0");
        return cacheRecordMapper.insertSelective(cacheRecord);
    }


    /**
     * 描述：获取失物缓存表中的记录
     *
     * @param request
     * @return
     */
    @Override
    public List<CacheRecord> getCacheRecordList(PageRequest request) {
        Map<String, Object> paramsMap = request.getParamsMap();
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        return cacheRecordMapper.selectCacheRecordList(paramsMap);
    }
}
