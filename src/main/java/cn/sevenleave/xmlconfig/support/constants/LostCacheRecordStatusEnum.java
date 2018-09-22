package cn.sevenleave.xmlconfig.support.constants;

/**
 * 描述：表 lost_cache_record 中字段status的取值
 * 1."0" : 未处理
 * 2."-1" : 拒绝,不合规的提交
 * 3."1" : 通过
 * 4."2" : 主动下架,撤回
 * 5."3" : 已找到失主
 *
 * @author SevenLeave
 * @date 2018-09-15
 */
public enum LostCacheRecordStatusEnum {
    STATUS_UNPROCESSED("0"), STATUS_REJECT("-1"), STATUS_PASS("1"), STATUS_OFFLINE("2"), STATUS_FOUND("3"), STATUS_REQUEST_OFFLINE("4");

    private String status;

    LostCacheRecordStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
