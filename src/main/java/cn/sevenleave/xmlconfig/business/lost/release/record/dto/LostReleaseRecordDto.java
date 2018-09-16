package cn.sevenleave.xmlconfig.business.lost.release.record.dto;

import cn.sevenleave.xmlconfig.business.lost.cache.record.model.LostCacheRecord;
import cn.sevenleave.xmlconfig.business.lost.release.record.model.LostReleaseRecord;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-15
 */
public class LostReleaseRecordDto extends LostReleaseRecord {

    /**
     * 包含了表 lost_cache_record 的大部分信息
     */
    private LostCacheRecord lostCacheRecord;

    public LostCacheRecord getLostCacheRecord() {
        return lostCacheRecord;
    }

    public void setLostCacheRecord(LostCacheRecord lostCacheRecord) {
        this.lostCacheRecord = lostCacheRecord;
    }
}
