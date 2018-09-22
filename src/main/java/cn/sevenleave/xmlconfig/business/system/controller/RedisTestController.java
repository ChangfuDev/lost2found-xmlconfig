package cn.sevenleave.xmlconfig.business.system.controller;

import cn.sevenleave.xmlconfig.business.lost.cache.record.model.LostCacheRecord;
import cn.sevenleave.xmlconfig.business.lost.cache.record.service.ILostCacheRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：description
 *
 * @author SevenLeave
 * @date 2018-09-17
 */
@Controller
@RequestMapping(value = "/redis/test")
public class RedisTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTestController.class);

    @Autowired
    private ILostCacheRecordService lostCacheRecordService;

    /**
     * 描述：根据uuid来查询lostCacheRecord
     *
     * @param cacheRecordUuid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/record/cache/uuid/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public LostCacheRecord getByUuid(@PathVariable("uuid") String cacheRecordUuid) throws Exception {
        LostCacheRecord lostCacheRecord = lostCacheRecordService.getLostCacheRecordByUuid(cacheRecordUuid);
        return lostCacheRecord;
    }

}
