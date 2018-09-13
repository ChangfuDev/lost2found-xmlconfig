package cn.sevenleave.xmlconfig.waiter.controller;

import cn.sevenleave.xmlconfig.cache.record.model.CacheRecord;
import cn.sevenleave.xmlconfig.cache.record.service.ICacheRecordService;
import cn.sevenleave.xmlconfig.utils.model.PageRequest;
import cn.sevenleave.xmlconfig.utils.model.PageResponse;
import cn.sevenleave.xmlconfig.waiter.service.IWaiterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 描述： wait_info 对应的controller类
 *
 * @author SevenLeave
 * @date 2018-07-31 10:55
 */
@Controller
@RequestMapping("/waiter")
public class WaiterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaiterController.class);

    @Autowired
    private IWaiterService waiterService;

    @Autowired
    private ICacheRecordService cacheRecordService;

    /**
     * 描述：获取失物缓存表的记录
     *
     * @param pageRequest
     * @return
     */
    @RequestMapping(value = "/cacherecords", method = RequestMethod.GET)
    @ResponseBody
    public PageResponse<CacheRecord> getCacheRecords(PageRequest pageRequest) {
        List<CacheRecord> cacheRecordList = cacheRecordService.getCacheRecordList(pageRequest);
        return new PageResponse<>(cacheRecordList);
    }


}
