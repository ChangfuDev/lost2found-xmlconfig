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
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 *
 * 使用redis进行缓存,用的是spring的注解缓存
 *
 * @author SevenLeave
 * @date 2018-07-31 14:10
 */
@Service
@CacheConfig(cacheNames = "redis-cache")
public class CacheRecordServiceImpl implements ICacheRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheRecordServiceImpl.class);

    @Autowired
    private CacheRecordMapper cacheRecordMapper;


    /**
     * 描述：新增失物记录到缓存表
     *
     * @param cacheRecord
     * @return
     */
    @Override
    @CacheEvict(key = "#root.target.redisCacheRecordServiceImplKeyName()")
    public int addCacheRecord(CacheRecord cacheRecord, String userUuid) {
        cacheRecord.setUuid(StringUtils.uuid());
        cacheRecord.setUserUuid(userUuid);
        // 0表示未处理
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
    @Cacheable(key = "#root.target.redisCacheRecordServiceImplKeyName()")
    public List<CacheRecord> getCacheRecordList(PageRequest request) {
        Map<String, Object> paramsMap = request.getParamsMap();
        // 分页
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        return cacheRecordMapper.selectCacheRecordList(paramsMap);
    }

    /**
     * 描述：用于给redis缓存提供自定义的key值
     *
     * @return
     */
    public String redisCacheRecordServiceImplKeyName() {
        return "cacheRecordServiceImpl-key";
    }
}
