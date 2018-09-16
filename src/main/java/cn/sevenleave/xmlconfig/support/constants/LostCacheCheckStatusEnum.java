package cn.sevenleave.xmlconfig.support.constants;

/**
 * 描述：表 lost_cache_check 的status字段的值
 * 1."1" : 通过
 * 2."-1" : 拒绝
 *
 * @author SevenLeave
 * @date 2018-09-15
 */
public enum LostCacheCheckStatusEnum {
    STATUS_PASS("1"), STATUS_REJECT("-1");

    private String status;

    LostCacheCheckStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
