package cn.sevenleave.xmlconfig.cache.record.service;

import cn.sevenleave.xmlconfig.cache.record.model.CacheRecord;
import cn.sevenleave.xmlconfig.utils.model.PageRequest;

import java.util.List;

/**
 * @author SevenLeave
 * @date 2018-07-31 14:10
 */
public interface ICacheRecordService {

    /**
     * 描述：新增失物记录到缓存表
     * @param cacheRecord
     * @return
     */
    int addCacheRecord(CacheRecord cacheRecord, String userUUid);

    /**
     * 描述：获取失物缓存表中记录
     *
     * @param request
     * @return
     */
    List<CacheRecord> getCacheRecordList(PageRequest request);
}
