package cn.sevenleave.xmlconfig.business.lost.cache.record.mapper;

import cn.sevenleave.xmlconfig.business.lost.cache.record.model.LostCacheRecord;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface LostCacheRecordMapper extends Mapper<LostCacheRecord> {

    /**
     * 描述：查询“招领”记录
     *
     * @param paramMap
     * @return
     */
    List<LostCacheRecord> selectLostCacheRecordList(Map<String, Object> paramMap);

}