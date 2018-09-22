package cn.sevenleave.xmlconfig.business.lost.cache.record.service.impl;

import cn.sevenleave.xmlconfig.business.lost.cache.record.mapper.LostCacheRecordMapper;
import cn.sevenleave.xmlconfig.business.lost.cache.record.model.LostCacheRecord;
import cn.sevenleave.xmlconfig.business.lost.cache.record.service.ILostCacheRecordService;
import cn.sevenleave.xmlconfig.support.model.PageRequest;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-13
 */
@Service
public class LostCacheRecordServiceImpl implements ILostCacheRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LostCacheRecordServiceImpl.class);

    @Autowired
    private LostCacheRecordMapper cacheRecordMapper;

    /**
     * 描述：新增“招领”记录至缓存表中
     *
     * @param lostCacheRecord
     * @return
     * @throws Exception
     */
    @Override
    public int addLostCacheRecord(LostCacheRecord lostCacheRecord) throws Exception {
        int rows = cacheRecordMapper.insertSelective(lostCacheRecord);
        return rows;
    }

    /**
     * 描述：查询“招领”记录
     *
     * @param pageRequest
     * @return
     * @throws Exception
     */
    @Override
    public List<LostCacheRecord> getLostCacheRecordList(PageRequest pageRequest) throws Exception {
        Map<String, Object> paramMap = pageRequest.getParamMap();
        // 分页过滤
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        // 如果没有匹配的数据,mapper查询会返回size=0的list
        List<LostCacheRecord> lostCacheRecordList = cacheRecordMapper.selectLostCacheRecordList(paramMap);
        return lostCacheRecordList;
    }

    /**
     * 描述：修改用户提交的“招领”记录的缓存
     *
     * @param lostCacheRecord
     * @return
     * @throws Exception
     */
    @Override
    public int modifyLostCacheRecord(LostCacheRecord lostCacheRecord) throws Exception {
        // Example修改
        Example example = new Example(LostCacheRecord.class);
        example.createCriteria().andEqualTo("uuid", lostCacheRecord.getUuid());
        int rows = cacheRecordMapper.updateByExampleSelective(lostCacheRecord, example);
        return rows;
    }

    /**
     * 描述：根据uuid查询“招领”记录
     *
     * @param uuid
     * @return LostCacheRecord对象或null
     * @throws Exception
     */
    @Override
    public LostCacheRecord getLostCacheRecordByUuid(String uuid) throws Exception {
        LostCacheRecord lostCacheRecord = cacheRecordMapper.selectByPrimaryKey(uuid);
        return lostCacheRecord;
    }
}
