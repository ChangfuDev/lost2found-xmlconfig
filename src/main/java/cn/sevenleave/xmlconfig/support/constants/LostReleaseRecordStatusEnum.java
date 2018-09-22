package cn.sevenleave.xmlconfig.support.constants;

/**
 * 描述：表 lost_release_record 的status字段的值
 * 1."1" : 正在上架中
 * 2."2" : 主动下架
 * 3."3" : 已找到失主
 * 4."4" : 用户申请下架中
 *
 * @author SevenLeave
 * @date 2018-09-15
 */
public enum LostReleaseRecordStatusEnum {
    STATUS_PUBLISHING("1"), STATUS_OFFLINE("2"), STATUS_FOUND("3"), STATUS_REQUEST_OFFLINE("4");

    private String status;

    LostReleaseRecordStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
