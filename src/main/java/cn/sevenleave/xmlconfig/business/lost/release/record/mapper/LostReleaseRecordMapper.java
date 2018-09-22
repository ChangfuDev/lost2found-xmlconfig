package cn.sevenleave.xmlconfig.business.lost.release.record.mapper;

import cn.sevenleave.xmlconfig.business.lost.release.record.dto.LostReleaseRecordDto;
import cn.sevenleave.xmlconfig.business.lost.release.record.model.LostReleaseRecord;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface LostReleaseRecordMapper extends Mapper<LostReleaseRecord> {

    /**
     * 描述：获取正式发布的“招领记录”
     *
     * @param paramMap
     * @return
     */
    List<LostReleaseRecordDto> selectLostReleaseRecordDtoList(Map<String, Object> paramMap);


}