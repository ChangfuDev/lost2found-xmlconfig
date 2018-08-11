package cn.sevenleave.xmlconfig.cache.record.controller;

import cn.sevenleave.xmlconfig.cache.record.model.CacheRecord;
import cn.sevenleave.xmlconfig.cache.record.service.ICacheRecordService;
import cn.sevenleave.xmlconfig.utils.model.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author SevenLeave
 * @date 2018-07-31 14:09
 */
@Controller
@RequestMapping("/cache/record")
public class CacheRecordController {

    private static final Logger logger = LoggerFactory.getLogger(CacheRecordController.class);

    @Autowired
    private ICacheRecordService cacheRecordService;

    /**
     * 描述：到失物发布申请页
     * @return
     */
    @RequestMapping(value = "/toIssue", method = RequestMethod.GET)
    public String toIssue() {
        return "cache/addCache";
    }

    /**
     * 描述：发布失物
     * @param cacheRecord
     * @param userUuid
     * @return
     */
    @RequestMapping(value = "/issue", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public JsonResult issue(@RequestBody CacheRecord cacheRecord, String userUuid) {
        System.out.println("cacheRecord = " + cacheRecord.getKeyword());
        System.out.println("userUuid = " + userUuid);
        int rows = cacheRecordService.addCacheRecord(cacheRecord, userUuid);
        if (rows == 1) {
            return JsonResult.success("新增成功！");
        } else {
            return JsonResult.fail("新增失败！");
        }
    }
}
