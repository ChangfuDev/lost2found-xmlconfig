package cn.sevenleave.xmlconfig.business.lost.cache.record.service;

import cn.sevenleave.xmlconfig.business.lost.cache.record.model.LostCacheRecord;
import cn.sevenleave.xmlconfig.support.model.PageRequest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-13
 */
public interface ILostCacheRecordService {

    /**
     * 描述:提交“招领”记录至缓存表中
     *
     * @param lostCacheRecord
     * @return
     * @throws Exception
     * @warning 这里不能使用@CachePut来加入redis缓存,因为它的返回值不是LostCacheRecord
     */
    int addLostCacheRecord(LostCacheRecord lostCacheRecord) throws Exception;

    /**
     * 描述：查询提交的“招领”记录
     *
     * @param pageRequest
     * @return
     * @throws Exception
     */
    List<LostCacheRecord> getLostCacheRecordList(PageRequest pageRequest) throws Exception;

    /**
     * 描述：修改提交的“招领记录”
     *
     * @param lostCacheRecord
     * @return
     * @throws Exception
     */
    @CacheEvict(value = "lostCacheRecord", key = "#lostCacheRecord.uuid")
    int modifyLostCacheRecord(LostCacheRecord lostCacheRecord) throws Exception;

    /**
     * 描述：根据uuid查询“招领”记录
     *
     * @param uuid
     * @return
     * @throws Exception
     */
    @Cacheable(value = "lostCacheRecord", key = "#uuid")
    LostCacheRecord getLostCacheRecordByUuid(String uuid) throws Exception;

}
