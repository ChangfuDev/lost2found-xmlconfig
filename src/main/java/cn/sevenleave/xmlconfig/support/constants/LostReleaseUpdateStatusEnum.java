package cn.sevenleave.xmlconfig.support.constants;

/**
 * 描述：对正式发布的招领记录的操作
 * 1."2" : 主动下架
 * 2."3" : 已找到失主
 *
 * @author SevenLeave
 * @date 2018-09-16
 */
public enum LostReleaseUpdateStatusEnum {
    STATUS_OFFLINE("2"), STATUS_FOUND("3");

    private String status;

    LostReleaseUpdateStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
