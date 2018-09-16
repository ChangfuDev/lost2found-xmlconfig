package cn.sevenleave.xmlconfig.business.lost.release.record.service;

import cn.sevenleave.xmlconfig.business.lost.release.record.dto.LostReleaseRecordDto;
import cn.sevenleave.xmlconfig.business.lost.release.record.model.LostReleaseRecord;
import cn.sevenleave.xmlconfig.support.model.PageRequest;

import java.util.List;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-13
 */
public interface ILostReleaseRecordService {

    /**
     * 描述：获取正式发布的“招领”记录
     *
     * @param pageRequest
     * @return
     * @throws Exception
     */
    List<LostReleaseRecordDto> getLostReleaseRecordDtoList(PageRequest pageRequest) throws Exception;

    /**
     * 描述：新增正式发布的“招领”记录
     *
     * @param lostReleaseRecord
     * @return
     * @throws Exception
     */
    int addLostReleaseRecord(LostReleaseRecord lostReleaseRecord) throws Exception;

    /**
     * 描述：修改正式发布的“招领”记录
     *
     * @param lostReleaseRecord
     * @return
     * @throws Exception
     */
    int modifyLostReleaseRecord(LostReleaseRecord lostReleaseRecord) throws Exception;

    /**
     * 描述：根据uuid查询正式发布的招领记录
     *
     * @param uuid
     * @return
     * @throws Exception
     */
    LostReleaseRecord getLostReleaseRecordByUuid(String uuid) throws Exception;

}
