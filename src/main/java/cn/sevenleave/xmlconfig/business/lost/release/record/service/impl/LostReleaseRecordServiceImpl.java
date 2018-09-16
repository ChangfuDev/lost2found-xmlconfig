package cn.sevenleave.xmlconfig.business.lost.release.record.service.impl;

import cn.sevenleave.xmlconfig.business.lost.release.record.dto.LostReleaseRecordDto;
import cn.sevenleave.xmlconfig.business.lost.release.record.mapper.LostReleaseRecordMapper;
import cn.sevenleave.xmlconfig.business.lost.release.record.model.LostReleaseRecord;
import cn.sevenleave.xmlconfig.business.lost.release.record.service.ILostReleaseRecordService;
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
public class LostReleaseRecordServiceImpl implements ILostReleaseRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LostReleaseRecordServiceImpl.class);

    @Autowired
    private LostReleaseRecordMapper lostReleaseRecordMapper;

    /**
     * 描述：查询正式发布的“招领”记录
     *
     * @param pageRequest
     * @return
     * @throws Exception
     */
    @Override
    public List<LostReleaseRecordDto> getLostReleaseRecordDtoList(PageRequest pageRequest) throws Exception {
        Map<String, Object> paramMap = pageRequest.getParamMap();
        // 分页过滤
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<LostReleaseRecordDto> lostReleaseRecordDtoList = lostReleaseRecordMapper.selectLostReleaseRecordDtoList(paramMap);
        return lostReleaseRecordDtoList;
    }

    /**
     * 描述：新增正式发布的“招领”记录
     *
     * @param lostReleaseRecord
     * @return
     * @throws Exception
     */
    @Override
    public int addLostReleaseRecord(LostReleaseRecord lostReleaseRecord) throws Exception {
        int rows = lostReleaseRecordMapper.insertSelective(lostReleaseRecord);
        return rows;
    }

    /**
     * 描述：修改正式发布的“招领”记录
     *
     * @param lostReleaseRecord
     * @return
     * @throws Exception
     */
    @Override
    public int modifyLostReleaseRecord(LostReleaseRecord lostReleaseRecord) throws Exception {
        // 使用Example来更新
        Example example = new Example(LostReleaseRecord.class);
        example.createCriteria().andEqualTo("uuid", lostReleaseRecord.getUuid());
        int rows = lostReleaseRecordMapper.updateByExampleSelective(lostReleaseRecord, example);
        return rows;
    }

    /**
     * 描述：根据uuid查询正式发布的招领记录
     *
     * @param uuid
     * @return
     * @throws Exception
     */
    @Override
    public LostReleaseRecord getLostReleaseRecordByUuid(String uuid) throws Exception {
        Example example = new Example(LostReleaseRecord.class);
        example.createCriteria().andEqualTo("uuid", uuid);
        LostReleaseRecord lostReleaseRecord = lostReleaseRecordMapper.selectOneByExample(example);
        return lostReleaseRecord;
    }
}
