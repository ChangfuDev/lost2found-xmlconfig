package cn.sevenleave.xmlconfig.cache.record.mapper;

import cn.sevenleave.xmlconfig.cache.record.model.CacheRecord;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface CacheRecordMapper extends Mapper<CacheRecord> {

    /**
     * 描述：查询失物缓存表中的记录
     *
     * @param paramsMap
     * @return
     */
    List<CacheRecord> selectCacheRecordList(Map<String, Object> paramsMap);
}